package com.exercicio.apolice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ApoliceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApoliceApplication.class, args);
	}

}
