package com.spring.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.auth.entity.Users;
import com.spring.auth.repo.UserRepo;

@Service
public class AuthService  implements UserDetailsService{
	
	
	@Autowired
	UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("This method Calling");
		
		Users byEmail = userRepo.findByEmail(email);
		
		return new User(
		        byEmail.getEmail(),
		        byEmail.getPassword(),
		        List.of(new SimpleGrantedAuthority("ROLE_" + byEmail.getRole().name()))
		);
	}
	

}
