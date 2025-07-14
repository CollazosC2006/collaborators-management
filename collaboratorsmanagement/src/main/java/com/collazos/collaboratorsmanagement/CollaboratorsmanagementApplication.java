package com.collazos.collaboratorsmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CollaboratorsmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollaboratorsmanagementApplication.class, args);
	}

}
