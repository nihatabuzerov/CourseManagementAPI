package com.CourseManagementAPI.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.CourseManagementAPI"})
@ComponentScan(basePackages = {"com.CourseManagementAPI"})
@EnableJpaRepositories(basePackages = {"com.CourseManagementAPI"})
public class CourseManagementAPI {

	public static void main(String[] args) {
		SpringApplication.run(CourseManagementAPI.class, args);
	}

}
