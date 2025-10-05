package com.spring.patient.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
//
//	@Autowired
//	private JavaMailSender javaMailSender;
//	
//	
	public void sendNotificationToPatient(String patientEmail,String patientName,String doctorName,LocalDateTime localDateTime,LocalDateTime localDateTime2) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		String slotRange = localDateTime.format(formatter) + " to " + localDateTime2.format(formatter);
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(patientEmail);
	    message.setSubject("Slot Bookin Confirmation");
	    message.setText("Dear " + patientName +
	    	    ",\n\nYour slot has been successfully booked with Dr. " + doctorName +
	    	    " for the time slot on " + slotRange + "." +
	    	    "\n\nPlease check your schedule for more details." +
	    	    "\n\nRegards,\nMediConnect Team");
	    
	    javaMailSender.send(message);
		
	}
	
	

}
