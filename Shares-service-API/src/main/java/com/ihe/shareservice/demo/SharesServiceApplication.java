package com.ihe.shareservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.ihe.shareservice")
@EntityScan(basePackages = "com.ihe.shareservice.entity")
@EnableJpaRepositories(basePackages = "com.ihe.shareservice.persistence")
public class SharesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharesServiceApplication.class, args);
	}

}
