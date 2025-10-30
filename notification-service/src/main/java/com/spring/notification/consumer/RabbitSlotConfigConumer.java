package com.spring.notification.consumer;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitSlotConfigConumer {
	
	public static final String EXCHANGE="notifications.exchange";
	
	public static final String QUEUE="slot.create.notification.queue";
	
	public static final String ROUTING_KEY="notification.slot.create";
	
	public static final  String DLX="slot.create.dlx";
	
	public static final String DLQ="slot.create.deadletter.queue";
	
	
	@Bean
    public TopicExchange creationExchange() {
        return new TopicExchange(EXCHANGE, true, false);
    }

    @Bean
    public TopicExchange deadLetterCreationExchange() {
        return new TopicExchange(DLX, true, false);
    }

@Bean
public Queue emailQueue() {
	Map<String, Object>hs=new HashMap<>();
	 hs.put("x-dead-letter-exchange", DLX);
    hs.put("x-dead-letter-routing-key", "dlq.email");
    
    return new Queue(QUEUE,true,false,false,hs);
	
}

   @Bean
    public Queue deadLetterQueue() {
        return new Queue(DLQ, true);
    }
    
    

    @Bean
    public Binding binding(Queue emailQueue, TopicExchange notificationExchange) {
        return BindingBuilder.bind(emailQueue).to(notificationExchange).with(ROUTING_KEY);
    }

    @Bean
    public Binding dlxBinding(Queue deadLetterQueue, TopicExchange deadLetterExchange) {
        return BindingBuilder.bind(deadLetterQueue).to(deadLetterExchange).with("dlq.email");
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory cf, MessageConverter converter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(cf);
        factory.setMessageConverter(converter);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); // manual ack control
        factory.setPrefetchCount(10); // tune as needed
        return factory;
    }

}
