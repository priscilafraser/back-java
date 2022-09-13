package com.pet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PetfriendJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetfriendJavaApplication.class, args);
		logger.log("Hi");
	}

}
