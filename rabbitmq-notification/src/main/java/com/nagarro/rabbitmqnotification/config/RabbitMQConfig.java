package com.nagarro.rabbitmqnotification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.rabbitmqnotification.service.RabbitMQConsumer;

@Configuration
public class RabbitMQConfig {
	
	@Value("${nagarro.rabbitmq.response-queue2}")
	String responseQueue2;
	
	@Value("${nagarro.rabbitmq.response-exchange}")
	String responseExchange;
	
	@Value("${nagarro.rabbitmq.response-routingkey2}")
	private String routingkey2;
	
	@Bean
	Queue responseQueue2() {
	    return new Queue(responseQueue2, true);
	}

	@Bean
	DirectExchange responseExchange() {
		return new DirectExchange(responseExchange);
	}

	@Bean
	Binding binding2(Queue responseQueue2, DirectExchange responseExchange) {
	    return BindingBuilder.bind(responseQueue2).to(responseExchange).with(routingkey2);
	}
	
	@Bean
	public SimpleMessageListenerContainer listenerContainer2(ConnectionFactory connectionFactory, RabbitMQConsumer rabbitMQConsumer) {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(responseQueue2);
	    container.setMessageListener(rabbitMQConsumer);
	    return container;
	  }
	
	  

}
