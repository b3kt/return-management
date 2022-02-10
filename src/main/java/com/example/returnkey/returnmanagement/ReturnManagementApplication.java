package com.example.returnkey.returnmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableAutoConfiguration
@SpringBootApplication
public class ReturnManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReturnManagementApplication.class, args);
	}

}
