package com.nhutin.electric_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ElectricProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricProjectApplication.class, args);
	}

}
