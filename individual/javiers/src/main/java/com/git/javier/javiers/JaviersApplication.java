package com.git.javier.javiers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.git.javier.javiers.model")
public class JaviersApplication {

	public static void main(String[] args) {
		SpringApplication.run(JaviersApplication.class, args);
	}

}
