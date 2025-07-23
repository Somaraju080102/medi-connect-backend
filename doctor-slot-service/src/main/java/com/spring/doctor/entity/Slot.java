package com.spring.doctor.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.spring.doctor.model.SlotStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Slot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	LocalDate date;
	LocalDateTime  startTime;
	LocalDateTime  endTime;
	@Enumerated(EnumType.STRING)
	SlotStatus status;
	@ManyToOne
	@JoinColumn(name = "doctor_id", nullable = false)
	Doctor doctor;
	@ManyToOne
	@JoinColumn(name="patient_id")
	Patient patient;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalDateTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalDateTime localTime) {
		this.startTime = localTime;
	}
	public LocalDateTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}
	public SlotStatus getStatus() {
		return status;
	}
	public void setStatus(SlotStatus status) {
		this.status = status;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
}
