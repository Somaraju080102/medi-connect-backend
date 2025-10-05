package com.spring.patient.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.spring.patient.entity.model.AppointmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	
	public Long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(Long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getSlotId() {
		return slotId;
	}

	public void setSlotId(Long slotId) {
		this.slotId = slotId;
	}

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	private Long slotId;
	
	@Enumerated(EnumType.STRING)
	private AppointmentStatus appointmentStatus;
	
	@ManyToOne
	@JoinColumn(name = "Patient_fk", nullable = false)
	private Patient patient;
	
	private LocalDateTime slotStartTime;
	
	private LocalDateTime slotEndTime;
	
	
	public LocalDateTime getSlotStartTime() {
		return slotStartTime;
	}

	public void setSlotStartTime(LocalDateTime slotStartTime) {
		this.slotStartTime = slotStartTime;
	}

	public LocalDateTime getSlotEndTime() {
		return slotEndTime;
	}

	public void setSlotEndTime(LocalDateTime slotEndTime) {
		this.slotEndTime = slotEndTime;
	}

	@CreationTimestamp
	private LocalDateTime creatDateTime;
	
	@UpdateTimestamp
	private LocalDateTime updDateTime;
	
	

}
