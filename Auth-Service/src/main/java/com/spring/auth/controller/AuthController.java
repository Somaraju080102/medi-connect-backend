package com.spring.auth.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.auth.dto.LoginDto;
import com.spring.auth.dto.RegisterDto;
import com.spring.auth.entity.Users;
import com.spring.auth.model.Role;
import com.spring.auth.repo.UserRepo;
import com.spring.auth.service.JwtService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
	
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserRepo userRepo;
	
	@GetMapping("/auth/greet")
	public String greetMsg(HttpServletRequest request) {
		
		Long  attribute = (Long) request.getAttribute("userId");
	
		
		
		return "Welcome to the Auth Service "+attribute+" "+"is the Current userId of the session "+(String)request.getAttribute("role");
		
	}
	
	@PostMapping("/auth/register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto){
		
		
		Users byEmail = userRepo.findByEmail(registerDto.getEmail());
		
		if(byEmail!=null) {
			return new ResponseEntity<String>("User already Registered Please Login",HttpStatus.valueOf("Already Exists"));
		}
		
		Users users=new Users();
		
		String encode=passwordEncoder.encode(registerDto.getPassword());
		
		users.setName(registerDto.getName());
		users.setPassword(encode);
		users.setEmail(registerDto.getEmail());
		users.setRole(registerDto.getRole());
		
		userRepo.save(users);
		
		
		return new ResponseEntity<String>("Registration Sucessful",HttpStatus.ACCEPTED);
		
		
	}
	
	@PostMapping("/auth/login")
	public ResponseEntity<Map<String, String>> loginUser(@RequestBody LoginDto loginDto){
		
//		System.out.println("Password "+loginDto.getPassword());
		
		
//		System.out.println("Email "+loginDto.getEmail());
		
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword());
		
		
//		System.out.println("Email 2 "+loginDto.getEmail());

		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		
//		System.out.println("Email 3"+loginDto.getEmail());
		
		Users users=userRepo.findByEmail(loginDto.getEmail());
		
		
		Map<String,String>hs=new HashMap<>();

		if(authenticate.isAuthenticated()) {
			
			String jwtToken=jwtService.generateToken(users);
			
			hs.put("userId", String.valueOf(users.getId()));
			
			hs.put("token", jwtToken);
			hs.put("role", users.getRole().name());
			
			return ResponseEntity.ok(hs);
			
		}
		hs.put("Credentials", "Invalid Credentials");
		
		return ResponseEntity.ok(hs);
		
		
	}
	
	

}
