package com.spring.doctor.repository;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.doctor.dto.DoctorInfo;
import com.spring.doctor.dto.DoctorSpecilizations;
import com.spring.doctor.dto.DoctorSummary;
import com.spring.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
//    Optional<Doctor> findByEmail(String email); // Example
	
	List<DoctorSummary> findBySpecialization(String specialization);
	
	List<DoctorInfo> findBy(PageRequest pageRequest);
	
	@Query("SELECT DISTINCT d.specialization AS specialization FROM Doctor d")
	List<String> findDistinctSpecialization();



}
