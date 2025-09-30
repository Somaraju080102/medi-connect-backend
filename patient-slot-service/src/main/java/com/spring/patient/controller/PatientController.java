package com.spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.patient.repo.AppointmentRepo;
import com.spring.patient.service.DoctorMicroService;

import reactor.core.publisher.Mono;

@RestController
public class PatientController {
	
	@Autowired
	DoctorMicroService dcotorMicroService;
	
	
	@GetMapping("/")
	public String message() {
		
		return "Hello";
	}
	
	
	@GetMapping("/doctoslots")
	public ResponseEntity<Mono<String>> getSlots(){
		
		Mono<String> slotInfo = dcotorMicroService.getSlotInfo(453, 1);
		
		return ResponseEntity.ok(slotInfo);
	}
	
	
	
	
	
	 

}
