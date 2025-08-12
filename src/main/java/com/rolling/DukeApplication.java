package com.rolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.rolling", "com.blob", "com.common"})
public class DukeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DukeApplication.class, args);
	}

}
