package com.spring.doctor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.spring.doctor.dto.DoctorInfo;
import com.spring.doctor.dto.DoctorResponse;
import com.spring.doctor.dto.DoctorSpecilizations;
import com.spring.doctor.dto.DoctorSummary;
import com.spring.doctor.dto.SlotResponse;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.repository.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;

	
	public List<DoctorSummary> getBySpec(String specialization) {
		// TODO Auto-generated method stub
		
		List<DoctorSummary> bySpecialization = doctorRepository.findBySpecialization(specialization);
		return bySpecialization;
	}


	public List<DoctorInfo> getAllDoctor() {
		
		PageRequest pageRequest=PageRequest.of(1, 2);
		
		 return  doctorRepository.findBy(pageRequest);
		
	}
	public List<Doctor> filterDoctor() {
		
		Doctor entDoctor=new Doctor();
		entDoctor.setName("Dr. Rajeev Kumar");
		
		Example<Doctor>ex=Example.of(entDoctor);
		
		  List<Doctor> all = doctorRepository.findAll(ex);
		  
		  return all;
		
		
		
	}
	
	
	public List<DoctorSummary> getDoctorBySpecilization(String specilization){
		
		return doctorRepository.findBySpecialization(specilization);
	}
	

	public List<String> doctorSpecilizations(){
		
		
		return doctorRepository.findDistinctSpecialization();
	}
	
	

}
