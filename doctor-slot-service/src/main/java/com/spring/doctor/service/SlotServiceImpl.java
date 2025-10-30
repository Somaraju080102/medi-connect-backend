package com.spring.doctor.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.doctor.dto.BookSlotRequest;
import com.spring.doctor.dto.CreateSlotRequest;
import com.spring.doctor.dto.PatientEmailService;
import com.spring.doctor.dto.SlotCreationNotification;
import com.spring.doctor.dto.SlotResponse;
import com.spring.doctor.entity.Doctor;
import com.spring.doctor.entity.Patient;
import com.spring.doctor.entity.Slot;
import com.spring.doctor.model.SlotStatus;
import com.spring.doctor.publisher.SlotCreationNotificationPublisher;
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
    
    @Autowired
    SlotCreationNotificationPublisher creationNotificationPublisher;
    
    

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
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a");
        String formattedTime = slotTime.format(formatter);
        String doctorName=doctor.getName();
        
        String subject = "🩺 Doctor " + doctorName + ", your new consultation slot is live!";

        String htmlBody = """
            <html>
            <head>
                <style>
                    body {
                        font-family: 'Segoe UI', sans-serif;
                        background-color: #f9fafb;
                        color: #333;
                        margin: 0;
                        padding: 0;
                    }
                    .container {
                        max-width: 600px;
                        margin: 40px auto;
                        background-color: #ffffff;
                        border-radius: 10px;
                        padding: 30px;
                        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
                    }
                    h2 { color: #007BFF; }
                    p { font-size: 16px; line-height: 1.5; }
                    .footer { margin-top: 30px; font-size: 13px; color: #666; }
                </style>
            </head>
            <body>
                <div class="container">
                    <h2>New Consultation Slot Created ✅</h2>
                    <p>Hey <strong>Dr. %s</strong>,</p>
                    <p>A new appointment slot has been successfully created for <strong>%s</strong>.</p>
                    <p>Get ready to help more patients and make a difference 💙</p>
                    <div class="footer">
                        <p>Best Regards,<br><strong>MediConnect Team</strong></p>
                    </div>
                </div>
            </body>
            </html>
        """.formatted(doctorName, formattedTime);
        
        SlotCreationNotification notification=new SlotCreationNotification();
        notification.setBody(htmlBody);
        notification.setSubject(subject);
        notification.setTo("nanivarma071@gmail.com");
        
        creationNotificationPublisher.SendNotificationToDoctor(notification);
        
        System.out.println("Yes this method is calling ");
        
        
//        emailService.sendSlotNotificationToDoctor("somaraju.0801@gmail.com", doctor.getName(),slotTime);
//        Slot saved = slotRepository.save(slot);
//        return mapToResponse(saved);
        return null;
        
    }

    @Override
    public List<SlotResponse> getSlotsByDoctor(Long doctorId) {
    	System.out.println("method is calling");
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
        
        
        callPatientEmailService();
        
//        emailService.sendNotificationToPatient(patientData.getEmail(), patientData.getName(),slot.getDoctor().getName(), slot.getStartTime(), slot.getEndTime());
        Slot updated = slotRepository.save(slot);
        
        return mapToResponse(updated);
    }

    private void callPatientEmailService() {
    	
    	WebClient web=WebClient.create();
    	
		
	}

	private SlotResponse mapToResponse(Slot slot) {
		
        SlotResponse res = new SlotResponse();
        res.setId(slot.getId());
        res.setDate(slot.getDate());
        res.setStartTime(slot.getStartTime());
        res.setEndTime(slot.getEndTime());
        res.setDoctorName(slot.getDoctor().getName());
        res.setStatus(slot.getStatus());
        res.setDoctorId(slot.getDoctor().getId());
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

	@Override
	public SlotResponse bookSlotForPatient(Long slotId,Long patientId) {
		
		Optional<Slot> byId = slotRepository.findById(slotId);
		
		if(byId.isPresent()) {
			
			return mapToResponse(byId.get());
		}
		
		return null;
	}
	
	@Override
	public SlotResponse bookSlotForPatient(PatientEmailService patientEmailService,Long slotId) {
		
		Optional<Slot> byId = slotRepository.findById(slotId);
		
		
		
		if(byId.isPresent()) {
			
			String name=patientEmailService.getPatientName();
			Slot slot = byId.get();
//	        emailService.sendBookinNotificationToDoctor("somaraju.0801@gmail.com", slot.getDoctor().getName(), name, slot.getStartTime(), slot.getEndTime());
			
			return mapToResponse(byId.get());
		}
		
		return null;
	}

	@Override
	public SlotResponse bookSlotForPatient(Long slotId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
