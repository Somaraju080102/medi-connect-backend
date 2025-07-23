package com.spring.doctor.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.doctor.dto.BookSlotRequest;
import com.spring.doctor.dto.CreateSlotRequest;
import com.spring.doctor.dto.SlotResponse;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.entity.Patient;
import com.spring.doctor.entity.Slot;
import com.spring.doctor.model.SlotStatus;
import com.spring.doctor.repository.DoctorRepository;
import com.spring.doctor.repository.PatientRepository;
import com.spring.doctor.repository.SlotRepository;


@Service
public class SlotServiceImpl implements SlotService {

    @Autowired
    private SlotRepository slotRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private EmailService emailService;
    
    

    @Override
    @Transactional
    public SlotResponse createSlot(CreateSlotRequest request) {
    	
    	if (request.getStartTime().compareTo(request.getEndTime()) >= 0) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
//    	List<Slot> overlapping = slotRepository.findOverlappingSlots(
//    		    doctorId, date, startTime, endTime
//    		);
//
//    		if (!overlapping.isEmpty()) {
//    		    throw new IllegalArgumentException("This slot overlaps with an existing one.");
//    		}
    	
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
            .orElseThrow(() -> new RuntimeException("Doctor not found"));
        

        Slot slot = new Slot();
        slot.setDoctor(doctor);
        slot.setDate(request.getDate());
        slot.setStartTime(request.getStartTime());
        slot.setEndTime(request.getEndTime());
        slot.setStatus(SlotStatus.AVAILABLE);
        
        doctor.getSlots().add(slot);  
        LocalDateTime slotTime = slot.getStartTime();
        emailService.sendSlotNotificationToDoctor("somaraju.0801@gmail.com", doctor.getName(),slotTime);
        Slot saved = slotRepository.save(slot);
        return mapToResponse(saved);
        
    }

    @Override
    public List<SlotResponse> getSlotsByDoctor(Long doctorId) {
        List<Slot> slots = slotRepository.findByDoctorId(doctorId);
        return slots.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    

    @Override
    @Transactional
    public SlotResponse bookSlot(BookSlotRequest pateint) {
    	
    	Patient patientData = patientRepository.findById(pateint.getPatientId())
    		    .orElseThrow(() -> new RuntimeException("Patient not found"));
    	
        Slot slot = slotRepository.findById(pateint.getSlotId())
            .orElseThrow(() -> new RuntimeException("Slot not found"));

        if (slot.getStatus() == SlotStatus.BOOKED) {
            throw new RuntimeException("Slot already booked");
        }
        slot.setPatient(patientData);
        slot.setStatus(SlotStatus.BOOKED);
        emailService.sendBookinNotificationToDoctor("somaraju.0801@gmail.com", slot.getDoctor().getName(), patientData.getName(), slot.getStartTime(), slot.getEndTime());
        emailService.sendNotificationToPatient(patientData.getEmail(), patientData.getName(),slot.getDoctor().getName(), slot.getStartTime(), slot.getEndTime());
        Slot updated = slotRepository.save(slot);
        
        return mapToResponse(updated);
    }

    private SlotResponse mapToResponse(Slot slot) {
        SlotResponse res = new SlotResponse();
        res.setId(slot.getId());
        res.setDate(slot.getDate());
        res.setStartTime(slot.getStartTime());
        res.setEndTime(slot.getEndTime());
        res.setDoctorName(slot.getDoctor().getName());
        res.setStatus(slot.getStatus());
        return res;
    }

	@Override
	public List<SlotResponse> getAvailableSlotsWithDoctor(Long doctorId) {
		
		List<Slot> slots = slotRepository.findByDoctorAndStatus(doctorId,SlotStatus.AVAILABLE);
		
		return slots.stream().map(this::mapToResponse).collect(Collectors.toList());
		
		
	}

	@Override
	public ResponseEntity<String> cancelSlotWithId(Long slotId) {
		
		Slot slot = slotRepository.findById(slotId)
				.orElseThrow(()->new RuntimeException("No slot Avaialble to cancel"));
		
		if(!slot.getStatus().equals(SlotStatus.BOOKED)) {
			return ResponseEntity.badRequest().body("Slot is not Booked or already Cancelled");
		}
		slot.setStatus(SlotStatus.AVAILABLE);
		
		slotRepository.save(slot);
		
		return ResponseEntity.ok("Slot successfully cancelled and marked as available");
		
	}
}
