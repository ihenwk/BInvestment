package com.ihe.Usersservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages ="com.ihe.Usersservice.entity")
@EnableJpaRepositories(basePackages ="com.ihe.Usersservice.persistence")
@SpringBootApplication(scanBasePackages = "com.ihe.Usersservice")
public class UsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersServiceApplication.class, args);
	}

}
