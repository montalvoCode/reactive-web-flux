package com.example.webFlux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebFluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebFluxApplication.class, args);
	}

}
