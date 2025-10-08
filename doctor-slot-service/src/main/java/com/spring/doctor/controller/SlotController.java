package com.spring.doctor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.doctor.dto.BookSlotRequest;
import com.spring.doctor.dto.CreateSlotRequest;
import com.spring.doctor.dto.PatientEmailService;
import com.spring.doctor.dto.SlotResponse;
import com.spring.doctor.entity.Slot;
import com.spring.doctor.service.SlotService;

@RestController
@RequestMapping("/slots")
public class SlotController {
	
	 @Autowired
	 private SlotService slotService;
	 
	 @GetMapping("/hello")
	 public String greetMessage() {
		 return "hello Wolrd";
			}

	    @PostMapping("/create")
	    public ResponseEntity<SlotResponse> createSlot(@RequestBody CreateSlotRequest request) {
	        return ResponseEntity.ok(slotService.createSlot(request));
	    }

	    @GetMapping("/{doctorId}")
	    public ResponseEntity<List<SlotResponse>> getSlots(@PathVariable Long doctorId) {
	        return ResponseEntity.ok(slotService.getSlotsByDoctor(doctorId));
	    }

	    @PostMapping("/book")
	    public ResponseEntity<SlotResponse> bookSlot(@RequestBody BookSlotRequest request) {
	        return ResponseEntity.ok(slotService.bookSlot(request));
	    }
	    
	    @GetMapping("/book/{slotId}/{patientId}")
	    public ResponseEntity<SlotResponse> bookSlot(@PathVariable Long slotId,Long patientId) {
	        return ResponseEntity.ok(slotService.bookSlotForPatient(slotId,patientId));
	    }
	    @PostMapping("/book/{slotId}")
	    public ResponseEntity<SlotResponse> bookSlot(@RequestBody PatientEmailService patientEmailService,@PathVariable Long slotId ) {
	        return ResponseEntity.ok(slotService.bookSlotForPatient(patientEmailService,slotId));
	    }
	    
	    @PatchMapping("/{slotId}/cancel")
	    public ResponseEntity<String> cancelSlot(@PathVariable Long slotId){
	    	
	    	return slotService.cancelSlotWithId(slotId);
	    	
	    }
	    
	    @GetMapping("/available/{doctorId}")
	    public ResponseEntity<List<SlotResponse>> getSlotsWithDoctor(@PathVariable Long doctorId){
	    	
	    	return ResponseEntity.ok(slotService.getAvailableSlotsWithDoctor(doctorId));
	    }
}
