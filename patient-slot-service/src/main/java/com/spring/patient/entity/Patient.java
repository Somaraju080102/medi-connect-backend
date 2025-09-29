package com.spring.patient.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	private String patientName;
	
	private Integer patientAge;
	
	private String patientEmail;
	
	private String patientPhone;
	
	@OneToMany(mappedBy = "patient" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Appointments> appointments;
	
	@CreationTimestamp
	private LocalDateTime creaDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updDateTime;
	
	
	

}
