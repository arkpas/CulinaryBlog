package arkpas.culinaryblog.service;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileService {

    private Path location;

    public FileService() {
        location = Paths.get("uploads");
    }

    public void saveFile (MultipartFile multipartFile, int recipeId) {
        if (multipartFile != null) {
            try {
                File file = new File(Paths.get("").toAbsolutePath().toString() + "/uploads/" + recipeId + multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);
            }
            catch (IOException e) {}
        }

    }

}
