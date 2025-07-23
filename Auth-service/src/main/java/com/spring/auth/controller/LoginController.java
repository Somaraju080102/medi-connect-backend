package com.spring.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.auth.dto.UserEmailDto;
import com.spring.auth.service.UserOtpService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class LoginController {
	
	@Autowired
	UserOtpService otpService;
	
	@GetMapping("/welcome")
	public String message() {
		
		return "Hello World";
	}
	
	@PostMapping("/send-otp")
	public boolean verifyOtp(@RequestBody  UserEmailDto dto){
		//TODO: process POST request
		
	 otpService.storeOtp(dto.getEmail());
		
		
		
		return ;
	}
	

}
