package com.spring.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.spring.auth.service.JwtService;

import io.jsonwebtoken.Claims;

@RestController
public class TokenValidationController {
	
	
	@Autowired
	JwtService jwtService;
	
	@GetMapping("/auth/validate")
	public ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String token) {
		
	    try {
	        String jwt = token.replace("Bearer ", "");

	        Claims claims = jwtService.extractAllClaims(jwt); // Parse token


	        String username = claims.getSubject();

	        String role = claims.get("role", String.class);
	        

	        String userId = claims.get("userId", String.class);
	        
	        System.out.println("Username "+username+" "+"Role "+role+" "+"UserId "+userId);

	        
			System.out.println("Error 3");

	        
			System.out.println("Error 3");


	        // Nginx can pick these from headers
	        
	        System.out.println(claims.getId()+" "+claims.getSubject());
	        
	        return ResponseEntity.ok()
	                .header("X-User-Id", userId)
	                .header("X-User-Name", username)
	                .header("X-User-Role", role)
	                .build();
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	}

//	
//	 @GetMapping("/auth/validate")
//	    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
//	        try {
//	            if (token == null || !token.startsWith("Bearer ")) {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header");
//	            }
//
//	            String jwt = token.substring(7);
//	            String username = jwtService.extractUsername(jwt);
//
//	            if (username == null || jwtService.isTokenExpired(jwt)) {
//	                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
//	            }
//
//	            Map<String, Object> response = new HashMap<>();
//	            response.put("userId", jwtService.extractUserId(jwt));
//	            response.put("role", jwtService.extractRole(jwt));
//
//	            return ResponseEntity.ok(response);
//
//	        } catch (Exception e) {
//	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token validation failed");
//	        }
//	    
//	 }
}
