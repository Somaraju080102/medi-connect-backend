package com.spring.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.entity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Long> {
	
	
	Users  findByEmail(String email);
	

}
