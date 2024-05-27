package com.nagarro.rabbitmqcustomerregistration.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nagarro.rabbitmqcustomerregistration.model.CustomerRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerRegistrationSender {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${nagarro.rabbitmq.exchange}")
    private String exchange;

    @Value("${nagarro.rabbitmq.routingkey}")
    private String routingkey;

    public void send(CustomerRegistration registeredCustomer) {
        try {
            rabbitTemplate.convertAndSend(exchange, routingkey, registeredCustomer);
            logger.info("Sent msg = {} from registration service", registeredCustomer.toString());
        } catch (Exception e) {
            logger.error("Error sending message to RabbitMQ", e);
        }
    }
}
