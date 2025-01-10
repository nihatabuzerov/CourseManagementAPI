package com.Nihatabuzerov3.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = {"com.Nihatabuzerov3"})
@ComponentScan(basePackages = {"com.Nihatabuzerov3"})
@EnableJpaRepositories(basePackages = {"com.Nihatabuzerov3"})
public class SpringDataJpaApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

}
