package com.spring.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DoctorSlotServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorSlotServiceApplication.class, args);
	}

}
