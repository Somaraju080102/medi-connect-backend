package com.spring.auth.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;



@Service
public class RabbitConfigProducer {
	
	public static final String NOTIF_EXCHANGE="notifications.exchange";
	
	@Bean
	public TopicExchange topicExchange() {
		
		return new TopicExchange(NOTIF_EXCHANGE);
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,MessageConverter messageConverter) {
		
		RabbitTemplate rabbitTemplate=new RabbitTemplate(connectionFactory);
		
		rabbitTemplate.setMessageConverter(messageConverter);
		
		return rabbitTemplate;
		
	}
	
	

}
