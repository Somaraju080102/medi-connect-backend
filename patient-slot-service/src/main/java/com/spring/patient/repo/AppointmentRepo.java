package com.spring.patient.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.patient.entity.Appointments;

public interface AppointmentRepo extends JpaRepository<Appointments, Long>{

}
