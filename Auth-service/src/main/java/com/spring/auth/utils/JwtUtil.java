//package com.spring.auth.utils;
//
//import java.sql.Date;
//
//import javax.crypto.SecretKey;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import io.jsonwebtoken.JwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//
//@Component
//public class JwtUtil {
//	
//	 @Value("${jwt.secret}")
//	    private String jwtSecret;
//
//	    private SecretKey getSignKey() {
//	        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
//	    }
//
//	    public String generateToken(String username) {
//	        return Jwts.builder()
//	                .setSubject(username)
//	                .setIssuedAt(new Date(System.currentTimeMillis()))
//	                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
//	                .signWith(getSignKey(), SignatureAlgorithm.HS256)
//	                .compact();
//	    }
//
//	    public String extractUsername(String token) {
//	        return Jwts.parserBuilder()
//	                .setSigningKey(getSignKey())
//	                .build()
//	                .parseClaimsJws(token)
//	                .getBody()
//	                .getSubject();
//	    }
//
//	    public boolean validateToken(String token, String email) {
//	        try {
//	            Jwts.parserBuilder()
//	                .setSigningKey(getSignKey())
//	                .build()
//	                .parseClaimsJws(token);
//	            return true;
//	        } catch (JwtException e) {
//	            // log if you want
//	            return false;
//	        }
//	    }
//}
