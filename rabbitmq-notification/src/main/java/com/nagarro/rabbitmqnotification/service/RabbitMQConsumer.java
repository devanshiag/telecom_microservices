package com.nagarro.rabbitmqnotification.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Override
    public void onMessage(Message message) {
        try {
            String messageBody = new String(message.getBody());
            logger.info("Notification service - {} your profile is completed!", messageBody);
            System.out.println("process successful!");
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }
    }
}