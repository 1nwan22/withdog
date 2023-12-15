package com.withdog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스케쥴링
@SpringBootApplication
public class WithDogApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WithDogApplication.class, args);
	}

}
