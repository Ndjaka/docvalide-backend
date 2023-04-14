package com.ghosttech.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Data
@AllArgsConstructor
@Slf4j
@Component
public class FileManager {

    private final static String FILE_DIRECTORY = "documents";

    /**
     * Converts a base64 string to a file and saves it in a specified directory.
     *
     * @param fileToBase64 The base64 string to convert to a file.
     * @param fileName     The name of the file to be saved.
     * @throws IOException If an error occurs during file creation or writing.
     */
    public static void base64ToFileAndSaveToDirectory(String fileToBase64, String fileName) {


        try {
            byte[] data = Base64.getDecoder().decode(fileToBase64);

            Path file = Paths.get("/" + FILE_DIRECTORY + "/", fileName);
            Files.write(file, data);
        } catch (Exception e) {
            e.printStackTrace();
            throw  new RuntimeException(String.format("This error [%s] during the creation of file",e.getMessage()));
        }
    }

    public static String getFile(String fileName) {

        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + "documents" + "/")
                .path(fileName)
                .toUriString();
    }
}
