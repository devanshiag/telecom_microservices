package com.nagarro.rabbitmqcustomerregistration.service;


import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.rabbitmqcustomerregistration.model.CustomerRegistration;
import com.nagarro.rabbitmqcustomerregistration.repo.CustomerRegistrationRepository;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer implements MessageListener {

    private final CustomerRegistrationRepository customerRepo;

    @Autowired
    public RabbitMQConsumer(CustomerRegistrationRepository customerRepo) {
        this.customerRepo = customerRepo;
    }

    public void onMessage(Message message) {
        try {
            String messageBody = new String(message.getBody());
            System.out.println("Consuming Message from service 2 - " + messageBody);


            updateCustomerStatus(messageBody, "completed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCustomerStatus(String customerId, String newStatus) {
        try {
            CustomerRegistration customer = customerRepo.findByCustomerId(customerId);

            if (customer != null) {
                customer.setStatus(newStatus);
                customerRepo.save(customer);
                System.out.println("Customer status updated to " + newStatus + " for ID: " + customerId);
            } else {
                System.out.println("Customer with ID " + customerId + " not found in the database.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
