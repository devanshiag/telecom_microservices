package com.nagarro.rabbitmqcustomerverification.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.nagarro.rabbitmqcustomerverification.repository.CustomerRepository;
import com.nagarro.rabbitmqcustomerverification.service.CustomerVerificationSender;
import com.nagarro.rabbitmqcustomerverification.service.RabbitMQConsumer;


@Configuration
public class RabbitMQConfig {
	
	
	@Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerVerificationSender CVSender;
   

	@Value("${nagarro.rabbitmq.queue}")
	String queueName;

	@Value("${spring.rabbitmq.username}")
	String username;

	@Value("${spring.rabbitmq.password}")
	private String password;
	

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
	DirectExchange exchange() {
		return new DirectExchange(responseExchange);
	}

	@Bean
	Binding binding1(Queue responseQueue1, DirectExchange responseExchange) {
	    return BindingBuilder.bind(responseQueue1).to(responseExchange).with(routingkey1);
	}

	@Bean
	Binding binding(Queue responseQueue2, DirectExchange responseExchange) {
		return BindingBuilder.bind(responseQueue2).to(responseExchange).with(routingkey2);
	}

	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}
	
	@Bean
	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, RabbitMQConsumer rabbitMQConsumer ) {
		SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
		simpleMessageListenerContainer.setQueues(queue());
		simpleMessageListenerContainer.setMessageListener(rabbitMQConsumer());
		return simpleMessageListenerContainer;

	}
	
	@Bean
    public RabbitMQConsumer rabbitMQConsumer() {
        return new RabbitMQConsumer(customerRepository, CVSender);
    }
    
}