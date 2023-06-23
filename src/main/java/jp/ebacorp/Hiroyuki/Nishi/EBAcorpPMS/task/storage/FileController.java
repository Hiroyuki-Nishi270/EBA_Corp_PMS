package jp.ebacorp.Hiroyuki.Nishi.EBAcorpPMS.task.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/file")
public class FileController {
    private final FileSystemStorageService storageService;

    @Autowired
    public FileController(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }
    @PostMapping("/upload/{id}")
    public String postFileUpload(@PathVariable Integer id,
                                 @RequestParam("attachFileEntity") MultipartFile file,
                                 RedirectAttributes redirectAttributes){
        storageService.store(file, id);

        redirectAttributes.addFlashAttribute("message",
                "ファイル " + file.getOriginalFilename() + "のアップロードが完了しました。");
        return "redirect:/ticket/" + id;
    }

    @GetMapping("/{id}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename,
                                              @PathVariable Integer id) {

        Resource file = storageService.loadAsResource(filename, id);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
