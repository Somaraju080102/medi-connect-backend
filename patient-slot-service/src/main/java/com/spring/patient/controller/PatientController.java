package com.spring.patient.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.patient.dto.AppointmentDto;
import com.spring.patient.dto.AppointmentResponse;
import com.spring.patient.dto.CancelDto;
import com.spring.patient.dto.DoctorDto;
import com.spring.patient.dto.PatientDto;
import com.spring.patient.dto.PatientEmailService;
import com.spring.patient.entity.Patient;
import com.spring.patient.repo.AppointmentRepo;
import com.spring.patient.repo.PatientRepo;
import com.spring.patient.service.DoctorMicroService;
import com.spring.patient.service.PatientAppointments;

import reactor.core.publisher.Mono;

@RestController
public class PatientController {
	
	@Autowired
	DoctorMicroService doctorMicroService;
	
	@Autowired
	PatientAppointments patientAppointments;
	
	@Autowired
	PatientRepo patientRepo;
	
	
	@GetMapping("/")
	public String message() {
		
		return "Hello";
		
	}
	
	@PostMapping("/patient/register")
	public ResponseEntity<String> registerPatient(@RequestBody PatientDto patientDto,
			@RequestHeader("X-User-Id") String userId,
            @RequestHeader("X-User-Role") String role,
            @RequestHeader("X-User-Name") String username){
		
		Patient patient=new Patient();
		patient.setPatientAge(patientDto.getPatientAge());
		patient.setPatientEmail(username);
		patient.setUserId(Long.valueOf(userId));
		patient.setPatientPhone(patientDto.getPatientPhone());
		patient.setPatientName(patientDto.getPatientName());
		
		
		patientRepo.save(patient);
		
		return new ResponseEntity<>("Patient Registered Succesfully ",HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/doctoslots")
	public ResponseEntity<Mono<AppointmentDto>> getSlots(){
		
		Mono<AppointmentDto> slotInfo = doctorMicroService.getSlotInfo(453, 1);
		
		return ResponseEntity.ok(slotInfo);
	}
	
	@PostMapping("/doctoslots")
	public ResponseEntity<Mono<AppointmentDto>> getSlots(@RequestBody PatientEmailService patientEmailService){
		
		Mono<AppointmentDto> slotInfo = doctorMicroService.getSlotInfo(patientEmailService);
		
		return ResponseEntity.ok(slotInfo);
	}
	
	
	@GetMapping("/patient/appointments/{pateintId}")
	public ResponseEntity<List<AppointmentResponse>> getAppointment(@PathVariable Long pateintId){
		
		
		List<AppointmentResponse> appointments = patientAppointments.getAppointments(pateintId);
		
		return ResponseEntity.ok(appointments);
	}
	
	@PostMapping("/patient/appointments/cancel")
	public ResponseEntity<String> cancelAppointment(@RequestBody CancelDto cancelDto ){
		
		patientAppointments.cancelSlot(cancelDto);
		
		return ResponseEntity.ok("Canceled Slot Sucessful");
		
	}
	
	
	
	 

}
