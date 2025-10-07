package com.spring.patient.dto;

import java.time.LocalDateTime;

import com.spring.patient.entity.model.AppointmentStatus;

public class AppointmentResponse {
	
	private AppointmentStatus appointmentStatus;
	
	private LocalDateTime slotStartTime;
	
	private LocalDateTime  slotEndTime;
	
	private DoctorDto doctorDto;

	public AppointmentStatus getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

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

	public DoctorDto getDoctorDto() {
		return doctorDto;
	}

	public void setDoctorDto(DoctorDto doctorDto) {
		this.doctorDto = doctorDto;
	}
	

}
