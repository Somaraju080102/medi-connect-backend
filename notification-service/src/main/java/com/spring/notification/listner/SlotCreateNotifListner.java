package com.spring.notification.listner;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.spring.notification.consumer.RabbitConfigRegisterConsumer;
import com.spring.notification.consumer.RabbitSlotConfigConumer;
import com.spring.notification.message.RegistrationNotification;
import com.spring.notification.message.SlotCreationNotification;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component

public class SlotCreateNotifListner {
	
	private final JavaMailSender javaMailSender;
	
	public SlotCreateNotifListner(JavaMailSender javaMailSender) {
		
		this.javaMailSender=javaMailSender;
	}
	
	 	@RabbitListener(queues = RabbitSlotConfigConumer.QUEUE)
	    public void listen(SlotCreationNotification message, Channel channel , @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
	        try {
	            sendEmail(message);
	            System.out.println("Message as sent");
	            channel.basicAck(tag, false);
	        } catch (Exception ex) {
	            try {
	                boolean requeue = false; // change to true if you want requeue
	                channel.basicNack(tag, false, requeue);
	            } catch (IOException e) {
	            }
	        }
	    }

	    private void sendEmail(SlotCreationNotification  msg) throws MessagingException {
//	        SimpleMailMessage mail = new SimpleMailMessage();
	        
	       MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	       
	       MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,true,"UTF-8");
	       
	       System.out.println("To "+msg.getTo());
	        
	        helper.setTo(msg.getTo());
	        helper.setSubject(msg.getSubject());
	        helper.setText(msg.getBody(),true);
	        javaMailSender.send(mimeMessage);
	    }

}
