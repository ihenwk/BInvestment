package com.ihe.UserInvestmentsService.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@ComponentScan(basePackages = "com.ihe.UserInvestmentsService")
@EntityScan(basePackages = "com.ihe.UserInvestmentsService.entity")
@EnableJpaRepositories ( basePackages ="com.ihe.UserInvestmentsService.persistence")
@SpringBootApplication (scanBasePackages = "com.ihe.UserInvestmentsService")
public class UserInvestmentsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserInvestmentsServiceApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate(){
		return new RestTemplate();
	}
}
