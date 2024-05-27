package com.nagarro.rabbitmqcustomerregistration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.rabbitmqcustomerregistration.dto.CustomerDetails;
import com.nagarro.rabbitmqcustomerregistration.model.CustomerRegistration;
import com.nagarro.rabbitmqcustomerregistration.repo.CustomerRegistrationRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerRegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationService.class);

    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;

    @Transactional
    public CustomerRegistration registerCustomer(CustomerDetails customerDetails) {
        try {

            String customerId = customerDetails.getCustomerId();
            CustomerRegistration customerRegistration = convertToCustomerRegistration(customerDetails, customerId);

            customerRegistration.setStatus("in progress");

            CustomerRegistration savedCustomer = customerRegistrationRepository.save(customerRegistration);

            return savedCustomer;
        } catch (Exception e) {
            logger.error("Error registering customer", e);
            throw new RuntimeException("Error registering customer", e);
        }
    }

    private CustomerRegistration convertToCustomerRegistration(CustomerDetails customerDetails, String customerId) {
        CustomerRegistration customerRegistration = new CustomerRegistration();
        customerRegistration.setCustomerId(customerId);
        customerRegistration.setCustomerCode(customerDetails.getCustomerCode());
        customerRegistration.setFirstName(customerDetails.getFirstName());
        customerRegistration.setLastName(customerDetails.getLastName());
        customerRegistration.setMSISDN(customerDetails.getContactNumber());
        customerRegistration.setGeolocation(customerDetails.getGeolocation());
        customerRegistration.setMSISDN(customerDetails.getMSISDN());
        return customerRegistration;
    }
    
    public CustomerRegistration findByMSISDN(String msisdn) {
        try {
            return customerRegistrationRepository.findBymsisdn(msisdn);
        } catch (Exception e) {
            logger.error("Error finding customer by MSISDN", e);
            throw new RuntimeException("Error finding customer by MSISDN", e);
        }
    }
}
