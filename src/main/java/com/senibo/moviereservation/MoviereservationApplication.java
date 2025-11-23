package com.senibo.moviereservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MoviereservationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoviereservationApplication.class, args);
	}

}
