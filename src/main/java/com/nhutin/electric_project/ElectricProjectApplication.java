package com.nhutin.electric_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class ElectricProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricProjectApplication.class, args);
	}

}
