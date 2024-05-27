package com.nagarro.rabbitmqcustomerregistration.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.rabbitmqcustomerregistration.service.RabbitMQConsumer;


@Configuration
public class RabbitMQConfig {

	@Value("${nagarro.rabbitmq.queue}")
	String queueName;

	@Value("${nagarro.rabbitmq.exchange}")
	String exchange;

	@Value("${nagarro.rabbitmq.routingkey}")
	private String routingkey;
	
	@Value("${nagarro.rabbitmq.response-queue1}")
	String responseQueue1;
	
	@Value("${nagarro.rabbitmq.response-queue2}")
	String responseQueue2;
	
	@Value("${nagarro.rabbitmq.response-exchange}")
	String responseExchange;
	
	@Value("${nagarro.rabbitmq.response-routingkey1}")
	private String routingkey1;
	
	@Value("${nagarro.rabbitmq.response-routingkey2}")
	private String routingkey2;

	@Bean
	Queue responseQueue1() {
	    return new Queue(responseQueue1, true);
	}

	@Bean
	Queue responseQueue2() {
	    return new Queue(responseQueue2, true);
	}

	@Bean
	DirectExchange responseExchange() {
		return new DirectExchange(responseExchange);
	}

	@Bean
	Binding binding1(Queue responseQueue1, DirectExchange responseExchange) {
	    return BindingBuilder.bind(responseQueue1).to(responseExchange).with(routingkey1);
	}


    @Bean
    public SimpleMessageListenerContainer listenerContainer1(ConnectionFactory connectionFactory, RabbitMQConsumer rabbitMQConsumer) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(responseQueue1);
        container.setMessageListener(rabbitMQConsumer);
        return container;
    }

	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	@Bean
	public AmqpTemplate customRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
