package com.spring.doctor.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.spring.doctor.dto.ApiErrorResponse;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	 @ExceptionHandler(EntityNotFoundException.class)
	    public ResponseEntity<ApiErrorResponse> handleEntityNotFound(EntityNotFoundException ex, WebRequest request) {
		 
		 System.out.println("Method 1 calling");
	        ApiErrorResponse response = new ApiErrorResponse(
	                HttpStatus.NOT_FOUND.value(),
	                "Entity Not Found",
	                ex.getMessage(),
	                request.getDescription(false)
	                
	        );
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
	    	
			 System.out.println("Method 2 calling");

	        String errors = ex.getBindingResult()
	                          .getFieldErrors()
	                          .stream()
	                          .map(err -> err.getField() + ": " + err.getDefaultMessage())
	                          .collect(Collectors.joining(", "));

	        ApiErrorResponse response = new ApiErrorResponse(
	                HttpStatus.BAD_REQUEST.value(),
	                "Validation Failed",
	                errors,
	                request.getDescription(false)
	        );
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<ApiErrorResponse> handleGeneral(Exception ex, WebRequest request) {
	    	
			 System.out.println("Method 3 calling");

	        ApiErrorResponse response = new ApiErrorResponse(
	                HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                "Server Error",
	                ex.getMessage(),
	                request.getDescription(false)
	        );
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }

}
