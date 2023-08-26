package com.test;

import javax.annotation.Resource;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import com.test.service.FilesStorageService;

@SpringBootApplication 
public class ClassifiedApplication implements CommandLineRunner {

	  @Resource
	  FilesStorageService storageService;
	
	public static void main(String[] args) {
		SpringApplication.run(ClassifiedApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    storageService.init();
		
	}

}
