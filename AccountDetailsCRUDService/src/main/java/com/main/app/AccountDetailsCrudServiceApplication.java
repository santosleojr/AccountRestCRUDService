package com.main.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.main.security","com.main.services","com.main.controller"})
@EntityScan("com.main.model")
@EnableJpaRepositories("com.main.dao")
public class AccountDetailsCrudServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountDetailsCrudServiceApplication.class, args);
	}
}
