package com.spring.auth.exception;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Service
public class AuthException {
	
	@ExceptionHandler
	public String getExceptionMsg(Exception e) {
		return "Hey Something went wrong due to "+e.getMessage();
		
	}

}
