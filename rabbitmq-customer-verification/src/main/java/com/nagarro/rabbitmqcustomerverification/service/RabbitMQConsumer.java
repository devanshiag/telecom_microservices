package com.nagarro.rabbitmqcustomerverification.service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.rabbitmqcustomerverification.entity.Customer;
import com.nagarro.rabbitmqcustomerverification.entity.GeoLocation;
import com.nagarro.rabbitmqcustomerverification.repository.CustomerRepository;

@Service
public class RabbitMQConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    private final CustomerRepository customerRepo;
    private final CustomerVerificationSender CVSender;

    @Autowired
    public RabbitMQConsumer(CustomerRepository customerRepo, CustomerVerificationSender CVSender) {
        this.customerRepo = customerRepo;
        this.CVSender = CVSender;
    }

    public void onMessage(Message message) {
        try {
            String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
            logger.info("Consuming Message - {}", messageBody);

            Customer customer = parseJsonToCustomer(messageBody);
            logger.info("Customer Details: {}", customer.toString());
            verifyAndSendResponse(customer);
        } catch (Exception e) {
            logger.error("Error processing message", e);
        }
    }

    public void verifyAndSendResponse(Customer customer) {
        try {
            Optional<Customer> existingCustomer = customerRepo.findByCustomerId(customer.getCustomerId());

            String responseMessage;
            if (existingCustomer.isPresent()) {
                responseMessage = customer.getCustomerId();
                logger.info("matched");
            } else {
                customerRepo.save(customer);
                responseMessage = customer.getCustomerId();
                logger.info("added");
            }

            CVSender.sendResponse(responseMessage);
        } catch (Exception e) {
            logger.error("Error verifying and sending response", e);
        }
    }

    private Customer parseJsonToCustomer(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode customerNode = objectMapper.readTree(json);
        return createCustomerFromJsonNode(customerNode);
    }

    private Customer createCustomerFromJsonNode(JsonNode customerNode) {
        Customer customer = new Customer();
        customer.setCustomerId(customerNode.get("customerId").asText());
        customer.setCustomerCode(customerNode.get("customerCode").asText());
        customer.setFirstName(customerNode.get("firstName").asText());
        customer.setLastName(customerNode.get("lastName").asText());
        customer.setContactNumber(customerNode.get("contactNumber").asText());

        JsonNode geolocationNode = customerNode.get("geolocation");
        if (geolocationNode != null) {
            GeoLocation geolocation = new GeoLocation();
            geolocation.setLatitude(geolocationNode.get("latitude").asText());
            geolocation.setLongitude(geolocationNode.get("longitude").asText());
            customer.setGeolocation(geolocation);
        }

        return customer;
    }
}