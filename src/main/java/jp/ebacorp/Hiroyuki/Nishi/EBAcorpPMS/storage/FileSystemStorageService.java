package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.storage;

import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.AttachFile;
import jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.CRUDAttachFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {

	private final Path rootLocation;

	@Autowired
	CRUDAttachFileRepository AttachFileRepository;
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {

		this.rootLocation = Paths.get(properties.getLocation());
	}

	//使用しない
	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			Path destinationFile = this.rootLocation.resolve(
					Paths.get(
							file.getOriginalFilename()
					)
					).normalize().toAbsolutePath();
			//動作チェック用
			System.out.println("rootLocation"+ this.rootLocation.toString());
			System.out.println("destination"+ destinationFile.toString());

			if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				Files.copy(inputStream, destinationFile,
					StandardCopyOption.REPLACE_EXISTING);

			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

	@Override
	public void store(MultipartFile file, Integer id) {

		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			Path destinationDirectoryRelative = this.rootLocation.resolve(id.toString()).normalize();//ファイルのディレクトリ(相対)
			Path destinationDirectory = destinationDirectoryRelative.toAbsolutePath();//ファイルのディレクトリ(絶対)

			Path destinationFile = this.rootLocation.resolve(id.toString()).
					resolve(
							Paths.get(
									file.getOriginalFilename()
							)
					).normalize().toAbsolutePath();

			if (!destinationFile.getParent().getParent().equals(this.rootLocation.toAbsolutePath())) {
				// This is a security check
				throw new StorageException(
						"Cannot store file outside current directory.");
			}
			try (InputStream inputStream = file.getInputStream()) {
				if(!Files.exists(destinationDirectory)){
					Files.createDirectory(destinationDirectory);
				}
				Files.copy(inputStream, destinationFile,
						StandardCopyOption.REPLACE_EXISTING);



				AttachFile attachFile = new AttachFile();
				attachFile.setAttachFile(
						id,
						file.getOriginalFilename(),
						destinationDirectoryRelative.toString()
				);
				AttachFileRepository.save(attachFile);


			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}
	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
