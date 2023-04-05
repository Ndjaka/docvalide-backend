package com.ghosttech.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Data
@AllArgsConstructor
@Slf4j
public class FileManager {
    private static final String FILE_DIRECTORY = "documents";


    public static Path convertBase64ToFile(String fileToBase64,String fileName) {
        Path path = null;
        try {
            byte[] data = Base64.getDecoder().decode(fileToBase64);
            path = Files.write(Paths.get(fileName), data);
        } catch (Exception e) {
            log.error("An error occurred: " + e.getMessage());
        }
        return path;
    }


    public static void addFileToDirectory(Path path) {
        Path targetDirectory = Paths.get("./"+FILE_DIRECTORY);
        try{
            Files.copy(path,targetDirectory.resolve(path.getFileName()));
            log.info("FileUrls added successfully to directory!");
        }catch (Exception e){
            log.error("An error occurred: " + e.getMessage());
        }
    }

    
    public static String getFile(String fileName){
        return  ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/"+FILE_DIRECTORY+"/")
                .path(fileName)
                .toUriString();
    }
}
