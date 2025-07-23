package com.spring.doctor.dto;

public class DoctorResponse {
	
	 private Long id;
	 private String name;
	 private String specialization;
	 private String hosipital;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getHosipital() {
		return hosipital;
	}
	public void setHosipital(String hosipital) {
		this.hosipital = hosipital;
	}
	 

}
