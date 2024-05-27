package com.nagarro.rabbitmqcustomerverification.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerVerificationSender {

    private static final Logger logger = LoggerFactory.getLogger(CustomerVerificationSender.class);

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${nagarro.rabbitmq.response-exchange}")
    private String responseExchange;

    @Value("${nagarro.rabbitmq.response-routingkey1}")
    private String routingkey1;

    @Value("${nagarro.rabbitmq.response-routingkey2}")
    private String routingkey2;

    public void sendResponse(String responseMessage) {
        try {
            rabbitTemplate.convertAndSend(responseExchange, routingkey1, responseMessage);
            rabbitTemplate.convertAndSend(responseExchange, routingkey2, responseMessage);
            logger.info("Sending response = {} back to registration service and notification service", responseMessage);
        } catch (AmqpException e) {
            logger.error("Error sending response to RabbitMQ", e);
        }
    }
}


