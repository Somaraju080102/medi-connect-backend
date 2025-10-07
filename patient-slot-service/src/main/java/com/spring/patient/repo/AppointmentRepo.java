package com.spring.patient.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.patient.entity.Appointments;

public interface AppointmentRepo extends JpaRepository<Appointments, Long>{

	List<Appointments> findByPatientId(Long id);

}
