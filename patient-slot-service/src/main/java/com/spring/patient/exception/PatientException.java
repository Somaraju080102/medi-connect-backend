package com.spring.patient.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PatientException {
	
	@ExceptionHandler(exception = Exception.class)
	public ResponseEntity<String> handleExceptions(Exception exception){
		
		return ResponseEntity.ok("Sorry For the Exception Please check the exact cause - > "+exception);
		
	}

}
