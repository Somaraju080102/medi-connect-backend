package com.spring.doctor.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.doctor.entity.Booking;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.entity.Slot;
import com.spring.doctor.model.SlotStatus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
//	 List<Slot> findByDoctorAndDate(Doctor doctor, LocalDate date);
//	    List<Slot> findByStatus(SlotStatus status);

}
