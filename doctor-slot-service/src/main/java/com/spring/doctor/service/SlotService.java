package com.spring.doctor.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.spring.doctor.dto.BookSlotRequest;
import com.spring.doctor.dto.CreateSlotRequest;
import com.spring.doctor.dto.SlotResponse;


public interface SlotService {
	SlotResponse createSlot(CreateSlotRequest createSlotRequest);
	List<SlotResponse> getSlotsByDoctor(Long doctorId);
	
	SlotResponse bookSlot(BookSlotRequest bookSlotRequest);
	List<SlotResponse> getAvailableSlotsWithDoctor(Long doctorId);
	ResponseEntity<String> cancelSlotWithId(Long slotId);
	SlotResponse bookSlotForPatient(Long slotId, Long patientId);

}

