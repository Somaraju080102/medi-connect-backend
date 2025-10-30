package com.spring.doctor.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.doctor.config.RabbitSlotConfigProducer;
import com.spring.doctor.dto.SlotCreationNotification;

@Component
public class SlotCreationNotificationPublisher {
	
	private final RabbitTemplate rabbitTemplate;
	
	
	public SlotCreationNotificationPublisher(RabbitTemplate rabbitTemplate) {
		
		this.rabbitTemplate=rabbitTemplate;
		
	}
	
	private final String ROUTING_KEY="notification.slot.create";
	
	public void SendNotificationToDoctor(SlotCreationNotification slotCreationNotification) {
		
		rabbitTemplate.convertAndSend(RabbitSlotConfigProducer.EXCHANGE, ROUTING_KEY, slotCreationNotification);
		
	}
	

}
