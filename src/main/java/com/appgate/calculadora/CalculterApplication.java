package com.appgate.calculadora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class CalculterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculterApplication.class, args);
	}

}
