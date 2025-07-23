package com.spring.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.entity.OtpEntry;

@Repository
public interface OtpEntryRepo extends JpaRepository<OtpEntry,String>{
	
	

}
