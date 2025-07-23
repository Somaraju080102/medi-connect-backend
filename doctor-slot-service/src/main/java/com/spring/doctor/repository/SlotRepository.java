package com.spring.doctor.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.doctor.entity.Slot;
import com.spring.doctor.model.SlotStatus;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long>{
	 
	 List<Slot> findByDoctorId(Long doctorId); 
	    List<Slot> findByStatus(SlotStatus status); 
	    List<Slot> findByDoctorIdAndDate(Long doctorId, LocalDate date); 
	   
	    @Query("SELECT s FROM Slot s WHERE s.doctor.id = :doctorId AND s.status = :status")
	    List<Slot> findByDoctorAndStatus(@Param("doctorId") Long doctorId,
	                                     @Param("status") SlotStatus status);
		List<Slot> findAllByEndTimeBeforeAndStatusNot(LocalDateTime now, SlotStatus expired);
	    
//	    @Query("SELECT s FROM Slot s WHERE s.doctor.id = :doctorId " +
//	    	       "AND s.date = :date " +
//	    	       "AND ((:startTime < s.endTime AND :endTime > s.startTime))")
//	    	List<Slot> findOverlappingSlots(
//	    	    @Param("doctorId") Long doctorId,
//	    	    @Param("date") LocalDate date,
//	    	    @Param("startTime") LocalTime startTime,
//	    	    @Param("endTime") LocalTime endTime
//	    	);

}
