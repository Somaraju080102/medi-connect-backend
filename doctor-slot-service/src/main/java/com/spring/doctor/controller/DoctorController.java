package com.spring.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.doctor.dto.DoctorDto;
import com.spring.doctor.dto.DoctorInfo;
import com.spring.doctor.dto.DoctorSummary;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.repository.DoctorRepository;
import com.spring.doctor.service.DoctorService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
	
     
	@Autowired
	DoctorService doctorService;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@GetMapping("/doctors")
	public ResponseEntity<List<DoctorSummary>> getBySepcilization(@RequestParam String specialization){
		return ResponseEntity.ok(doctorService.getBySpec(specialization));
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerDoctor(@RequestBody DoctorDto doctorDto,
			@RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @RequestHeader("X-User-Name") String username){
		
		
		Doctor doctor=new Doctor();
		doctor.setName(doctorDto.getName());
		doctor.setUserId(Long.valueOf(userId));
		doctor.setEmail(username);
		doctor.setPhone(doctorDto.getPhone());
		doctor.setSpecialization(doctorDto.getSpecialization());
		doctor.setHospital(doctorDto.getHospital());
		
		doctorRepository.save(doctor);
		
		
		return new ResponseEntity<String>("Succesfully Registered",HttpStatus.CREATED);
		
		
		
	}
	
	 	@GetMapping("/profile")
	    public ResponseEntity<String> getDoctorProfile(
	            @RequestHeader("X-User-Id") String userId,
	            @RequestHeader("X-User-Role") String role,
	            @RequestHeader("X-User-Name") String username) {
	 		
	 		System.out.println("Is this method calling ");

	        String response = String.format("Doctor Profile → ID: %s | Role: %s | Username: %s", 
	                                         userId, role, username);
	        return ResponseEntity.ok(response);
	    }
	
	@GetMapping("/info")
	public ResponseEntity<DoctorSummary> getDoctorInfoo(@RequestHeader("X-User-Id") String userId){
		
		System.out.println("Nethod is calling");
		
		DoctorSummary docInfo = doctorRepository.findByUserId(Long.valueOf(userId));
		
//		DoctorDto doctorInfo = doctorService.getDoctorInfo(Long.va);
		
		return ResponseEntity.ok(docInfo);
		
	}
	
	@GetMapping("/specilizations")
	public ResponseEntity<List<String>> getDoctorsSpecilization(){
		
		return ResponseEntity.ok(doctorService.doctorSpecilizations());
		
	}
	
	@GetMapping("/doctor/{specilization}")
	public ResponseEntity<List<DoctorSummary>> getDoctorsWithSpecilization(@PathVariable String specilization){
		System.out.println("specializatio : "+" "+specilization);
		
		List<DoctorSummary> doctorBySpecilization = doctorService.getDoctorBySpecilization(specilization);
		
		return ResponseEntity.ok(doctorBySpecilization);
		
	}
	
	@GetMapping("/allDoctors")
	public ResponseEntity<List<DoctorInfo>> getAllDoctors(){
		
		return ResponseEntity.ok(doctorService.getAllDoctor());
	}
	@GetMapping("/doc")
	public ResponseEntity<List<Doctor>> getDoc(){
		
		return ResponseEntity.ok(doctorService.filterDoctor());
	}

}
