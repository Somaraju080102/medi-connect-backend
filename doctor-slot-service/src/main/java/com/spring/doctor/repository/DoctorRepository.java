package com.spring.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.doctor.dto.DoctorSummary;
import com.spring.doctor.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long>{
//    Optional<Doctor> findByEmail(String email); // Example
	
	List<DoctorSummary> findBySpecialization(String specialization);


}
