package com.spring.patient.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.patient.dto.AppointmentDto;
import com.spring.patient.entity.Appointments;
import com.spring.patient.entity.Patient;
import com.spring.patient.repo.AppointmentRepo;
import com.spring.patient.repo.PatientRepo;

import reactor.core.publisher.Mono;

@Service
public class DoctorMicroService {
	
	@Autowired
	 AppointmentRepo appointmentRepo;
	
	@Autowired
	PatientRepo patientRepo;

	
	public Mono<AppointmentDto> getSlotInfo(int i,int j){
		
		String slotUrl="http://localhost:8080/slots/book/453/1";
		
		WebClient webClient=WebClient.create();
		
		 Mono<AppointmentDto> bodyToMono = webClient.get().uri(slotUrl).retrieve().bodyToMono(AppointmentDto.class);
		 
		 AppointmentDto appointmentDto = bodyToMono.block();
		 
		 Long patientId = appointmentDto.getPatientId();
		 
		 Optional<Patient> byId = patientRepo.findById(patientId);
		 if(byId.isPresent()) {
			 
		 
		 
		 Appointments appointments=new Appointments();
		 appointments.setAppointmentStatus(appointmentDto.getStatus());
		 appointments.setDoctorId(appointmentDto.getDoctorId());
		 appointments.setPatientId(appointmentDto.getPatientId());
		 appointments.setSlotStartTime(appointmentDto.getStartTime());
		 appointments.setSlotEndTime(appointmentDto.getEndTime());
		 appointments.setSlotId(appointmentDto.getId());
		 appointments.setPatient(byId.get());
		 
		 appointmentRepo.save(appointments);
		 
		 System.out.println("Saved into appointemt db");

		 }
		 
		 
		 
		 bodyToMono.subscribe(
		            value -> System.out.println("Received: " + value), // Consumer for the emitted value
		            error -> System.err.println("Error: " + error.getMessage()), // Consumer for errors
		            () -> System.out.println("Mono completed.") // Runnable for completion
		        );
		 
		 return bodyToMono;
	}
	
	
	
	

}
