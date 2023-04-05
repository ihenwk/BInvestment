package com.ihe.BrightInvestment.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


/*
By design, Spring Boot auto-configuration tries to configure the beans automatically
based on the dependencies added to the classpath.
Since the JPA dependency is on the classpath, Spring Boot tries to automatically
configure a JPA DataSource. The problem is that Spring does not have the information it
needs to perform the auto-configuration.

Use the exclude attribute: exclude={DataSourceAutoConfiguration.class} to exclude dataSource Autoconfiguration.
 */
@ComponentScan(basePackages = "com.ihe.BrightInvestment")
@EntityScan(basePackages = "com.ihe.BrightInvestment.entity")
@SpringBootApplication(scanBasePackages = "com.ihe.BrightInvestment", exclude={DataSourceAutoConfiguration.class})
public class BrightInvestmentApplication {


	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BrightInvestmentApplication.class, args);
	}

}
