package com.desafio.labs.springfavs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.desafio")
public class SpringFavsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringFavsApplication.class, args);
	}
}
