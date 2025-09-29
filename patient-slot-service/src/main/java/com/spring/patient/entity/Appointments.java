package com.spring.patient.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.spring.patient.entity.model.AppointmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appointments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long appointmentId;
	
	private Long patientId;
	
	private Long doctorId;
	
	private Long slotId;
	
	private AppointmentStatus appointmentStatus;
	
	@ManyToOne
	@JoinColumn(name = "Patient_fk", nullable = false)
	private Patient patient;
	
	@CreationTimestamp
	private LocalDateTime creatDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updDateTime;
	
	

}
