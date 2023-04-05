package com.ghosttech;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
public class DocvalideBackendApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(DocvalideBackendApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args)  {

		String directoryName = "documents";
		Path directoryPath = Paths.get(directoryName);

		try {
			Files.createDirectory(directoryPath);
			log.info("Directory created");
		} catch (Exception ex) {
			log.error("Directory is already created and error is : " + ex.getMessage());
		}
	}
}
