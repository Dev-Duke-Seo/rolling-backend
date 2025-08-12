package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.rolling.repository", "com.blob.repository"})
@EntityScan(basePackages = {"com.rolling.model.entity", "com.blob.entity"})
public class DukeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DukeApplication.class, args);
	}

}
