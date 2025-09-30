package com.spring.patient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class DoctorMicroService {
	
	
	public Mono<String>getSlotInfo(int i,int j){
		
		String slotUrl="http://localhost:8080/slots/book/453/1";
		
		WebClient webClient=WebClient.create();
		
		 Mono<String> bodyToMono = webClient.get().uri(slotUrl).retrieve().bodyToMono(String.class);
		 
		 bodyToMono.subscribe(
		            value -> System.out.println("Received: " + value), // Consumer for the emitted value
		            error -> System.err.println("Error: " + error.getMessage()), // Consumer for errors
		            () -> System.out.println("Mono completed.") // Runnable for completion
		        );
		 
		 return bodyToMono;
		 
	}
	
	
	
	

}
