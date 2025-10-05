package com.spring.patient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.patient.dto.AppointmentDto;
import com.spring.patient.dto.PatientEmailService;
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
	public ResponseEntity<Mono<AppointmentDto>> getSlots(){
		
		Mono<AppointmentDto> slotInfo = dcotorMicroService.getSlotInfo(453, 1);
		
		return ResponseEntity.ok(slotInfo);
	}
	
	@PostMapping("/doctoslots")
	public ResponseEntity<Mono<AppointmentDto>> getSlots(@RequestBody PatientEmailService patientEmailService){
		
		Mono<AppointmentDto> slotInfo = dcotorMicroService.getSlotInfo(patientEmailService);
		
		return ResponseEntity.ok(slotInfo);
	}
	
	
	
	
	
	 

}
