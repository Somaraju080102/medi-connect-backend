package com.spring.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.doctor.dto.DoctorInfo;
import com.spring.doctor.dto.DoctorResponse;
import com.spring.doctor.dto.DoctorSummary;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.service.DoctorService;

@RestController
public class DoctorController {
	
     
	@Autowired
	DoctorService doctorService;
	
	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorSummary>> getBySepcilization(@RequestParam String specialization){
		
		return ResponseEntity.ok(doctorService.getBySpec(specialization));
		
	}
	
	@GetMapping("/allDoctors")
	public ResponseEntity<List<DoctorInfo>> getAllDoctors(){
		
		return ResponseEntity.ok(doctorService.getAllDoctor());
	}

}
