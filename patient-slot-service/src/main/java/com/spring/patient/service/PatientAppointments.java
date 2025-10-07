package com.spring.patient.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.patient.dto.AppointmentResponse;
import com.spring.patient.dto.DoctorDto;
import com.spring.patient.entity.Appointments;
import com.spring.patient.repo.AppointmentRepo;

import reactor.core.publisher.Mono;

@Service
public class PatientAppointments {
	
	@Autowired
	private AppointmentRepo appointmentRepo;

	public List<AppointmentResponse> getAppointments(Long patientId) {

	    String docUrl = "http://localhost:8080/doctor/info/{doctorId}";
	    

	    List<Appointments> appointments = appointmentRepo.findByPatientId(patientId);
	    


	    List<AppointmentResponse> appointmentResponses = appointments.stream()
	        .map(appointment -> {
	        	
	    	    Long doctorId = appointment.getDoctorId();

	            DoctorDto doctorDto = WebClient.create()
	                    .get()
	                    .uri(docUrl, doctorId)
	                    .retrieve()
	                    .bodyToMono(DoctorDto.class)
	                    .block();

	            AppointmentResponse response = new AppointmentResponse();
	            
	            response.setAppointmentStatus(appointment.getAppointmentStatus());
	            
	            response.setSlotStartTime(appointment.getSlotStartTime());
	            
	            response.setSlotEndTime(appointment.getSlotEndTime());
	            
	            response.setDoctorDto(doctorDto);
	            
	            return response;
	        })
	        .toList();

	    return appointmentResponses;
	}

		
				
		
		
//	
//		ocUrl,doctorId)
//		.retrieve()
//		.bodyToMono(DoctorDto.class);
//		
//		 bodyToMono.subscribe(
//		            value -> System.out.println("Received: " + value), // Consumer for the emitted value
//		            error -> System.err.println("Error: " + error.getMessage()), // Consumer for errors
//		            () -> System.out.println("Mono completed.") // Runnable for completion
//		        );
//		 
//		 return bodyToMono;
//		
		
		
		
		
	}
	
	

