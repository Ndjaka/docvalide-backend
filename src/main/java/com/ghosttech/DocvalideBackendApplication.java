package com.ghosttech;

import com.ghosttech.constants.DocValidConstant;
import com.ghosttech.service.EnvironmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.File;

@SpringBootApplication
@Slf4j
public class DocvalideBackendApplication implements ApplicationRunner{



	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(DocvalideBackendApplication.class);
		Environment environment = app.run(args).getEnvironment();
		log.info("Active profile: " + environment.getProperty("spring.profiles.active"));
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
