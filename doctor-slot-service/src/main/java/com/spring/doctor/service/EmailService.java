package com.spring.doctor.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	public void sendSlotNotificationToDoctor(String toEmail,String doctorName,LocalDateTime slotTime) {
		
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a");
		 
		 String formattedTime=slotTime.format(formatter);

		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(toEmail);
		message.setSubject("Slot Creation Confirmation");
		 message.setText("Dear Doctor " + doctorName +
                 ",\n\nA new Slot has been created for: " + formattedTime +
                 "\n\nRegards,\nMediConnect Team");
		 
		 javaMailSender.send(message);

	}
	public void sendBookinNotificationToDoctor(String doctotEmail,String doctorName,String patientName,LocalDateTime localDateTime,LocalDateTime localDateTime2) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
		String slotRange = localDateTime.format(formatter) + " to " + localDateTime2.format(formatter);
	    
	    SimpleMailMessage message=new SimpleMailMessage();
	    
	    message.setTo(doctotEmail);
	    message.setSubject("Slot Bookin Confirmation");
	    message.setText("Dear Dr. " + doctorName +
	    	    ",\n\nA new appointment has been booked by patient " + patientName +
	    	    " for the slot on " + slotRange + "." +
	    	    "\n\nPlease check your schedule for more details." +
	    	    "\n\nRegards,\nMediConnect Team");
	    javaMailSender.send(message);
	}
	
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
