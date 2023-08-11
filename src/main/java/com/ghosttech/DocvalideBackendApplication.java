package com.ghosttech;

import com.ghosttech.constants.DocValidConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
public class DocvalideBackendApplication implements ApplicationRunner{

	public static void main(String[] args) {
		SpringApplication.run(DocvalideBackendApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) {
		String folderPath = DocValidConstant.DOCUMENT_FOLDER;
		File folder = new File(folderPath);

		if (!folder.exists()) {
			if (folder.mkdirs()) {
				log.info("Directory is created : " + folderPath);
			} else {
				log.error("Impossible to create folder : " + folderPath);
			}
		} else {
			log.info("Folder is already exist : {} " , folderPath);
		}
	}
}
