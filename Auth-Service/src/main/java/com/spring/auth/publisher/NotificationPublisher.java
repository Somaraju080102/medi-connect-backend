package com.spring.auth.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.spring.auth.config.RabbitConfigProducer;
import com.spring.auth.notification.RegistrationNotification;

@Service
public class NotificationPublisher {
	
	
	private final RabbitTemplate  rabbitTemplate;
	
	
	private final String ROUTING_KEY="notification.register";
	
	public NotificationPublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate=rabbitTemplate;
	}
	
	public void publishEmailToExchange(RegistrationNotification registrationNotification) {
		
		rabbitTemplate.convertAndSend(RabbitConfigProducer.NOTIF_EXCHANGE,ROUTING_KEY,registrationNotification);
		
	}
	
	
	

}
