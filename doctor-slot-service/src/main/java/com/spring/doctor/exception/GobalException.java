package com.spring.doctor.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GobalException {
	
	
	public String handleGlobalException(Exception msg) {
		
		return "Sorry you have face following Exception"+msg;
		
	}

}
