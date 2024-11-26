package com.hodbenor.project.robust.access.limit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RobustAccessLimitApplication {

	public static void main(String[] args) {
		SpringApplication.run(RobustAccessLimitApplication.class, args);
	}

}
