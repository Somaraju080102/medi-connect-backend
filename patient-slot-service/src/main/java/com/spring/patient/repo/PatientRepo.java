package com.spring.patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.patient.entity.Patient;


@Repository
public interface PatientRepo extends JpaRepository<Patient,Long>{

}
