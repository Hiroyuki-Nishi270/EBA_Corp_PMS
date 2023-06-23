package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage;

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
public class FileSystemStorageService {

	private final Path rootLocation;

	@Autowired
	CRUDAttachFileRepository AttachFileRepository;
	@Autowired
	public FileSystemStorageService(StorageProperties properties) {

		this.rootLocation = Paths.get(properties.getLocation());
	}

	public void store(MultipartFile file, Integer ticketId) {

		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file.");
			}

			Path destinationDirectoryRelative = this.rootLocation.resolve(ticketId.toString()).normalize();//ファイルのディレクトリ(相対)
			Path destinationDirectory = destinationDirectoryRelative.toAbsolutePath();//ファイルのディレクトリ(絶対)

			Path destinationFile = this.rootLocation.resolve(ticketId.toString()).
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

				Files.createDirectories(destinationDirectory);
				Files.copy(inputStream, destinationFile,
						StandardCopyOption.REPLACE_EXISTING);

				AttachFileEntity attachFileEntity = new AttachFileEntity();
				attachFileEntity.setAttachFile(
						ticketId,
						file.getOriginalFilename(),
						destinationDirectoryRelative.toString()
				);
				if(!AttachFileRepository.existsByTicketidAndFilename(ticketId, file.getOriginalFilename())){
					AttachFileRepository.save(attachFileEntity);
				}
			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file.", e);
		}
	}

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


	public Path load(String filename, Integer ticketId) {
		return rootLocation.resolve(ticketId.toString()).resolve(filename);
	}


	public Resource loadAsResource(String filename, Integer ticketId) {
		try {
			Path file = load(filename, ticketId);
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


	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}


	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
