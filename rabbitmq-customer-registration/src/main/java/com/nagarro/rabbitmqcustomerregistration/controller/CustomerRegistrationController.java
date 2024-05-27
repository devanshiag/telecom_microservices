package com.nagarro.rabbitmqcustomerregistration.controller;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.rabbitmqcustomerregistration.dto.CustomerDetails;
import com.nagarro.rabbitmqcustomerregistration.dto.Product;
import com.nagarro.rabbitmqcustomerregistration.model.CustomerRegistration;
import com.nagarro.rabbitmqcustomerregistration.repo.CustomerRegistrationRepository;
import com.nagarro.rabbitmqcustomerregistration.service.CustomerRegistrationSender;
import com.nagarro.rabbitmqcustomerregistration.service.CustomerRegistrationService;

@RestController
@RequestMapping("/api/v1/store")
public class CustomerRegistrationController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRegistrationController.class);

    @Autowired
    CustomerRegistrationSender CRSender;

    @Autowired
    private CustomerRegistrationService customerRegistrationService;
    
    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;

    @PostMapping("/customers")
//    @PreAuthorize("hasRole('client_user')")
    public ResponseEntity<?> registerCustomer(@RequestBody CustomerDetails customerDetails) {
        System.out.println("Received CustomerDetails: " + customerDetails.toString());

        CustomerRegistration registeredCustomer = customerRegistrationService.registerCustomer(customerDetails);
        CRSender.send(registeredCustomer);
        logger.info("Customer registration details sent to the queue successfully.");

        return new ResponseEntity<>(registeredCustomer, HttpStatus.CREATED);
    }

    @GetMapping
//    @PreAuthorize("hasRole('client_admin')")
    public String hello() {
        return "Hello from Springboot and Keycloak - ADMIN";
    }
    
    @PatchMapping("/update-contracts")
    public ResponseEntity<?> updateContracts(@RequestBody List<Product> products) {
        try {
            
            logger.info(products.toString());
            for (Product product : products) {
                String msisdn = product.getMSISDN();
                CustomerRegistration existingCustomer = customerRegistrationService.findByMSISDN(msisdn);

                if (existingCustomer != null) {
                    List<String> contracts = existingCustomer.getContracts();
                    if (contracts == null) {
                        contracts = new ArrayList<>();
                    }
                    String contractId = product.getContractId();
                    if (!contracts.contains(contractId)) {
                        contracts.add(contractId);
                        existingCustomer.setContracts(contracts);
                    }

                    System.out.println(existingCustomer.toString());
                    customerRegistrationRepository.save(existingCustomer);
                    logger.info("Contracts updated for customer with MSISDN: {}", msisdn);
                } else {
                    logger.warn("Customer not found with MSISDN: {}", msisdn);
                }
            }

            return new ResponseEntity<>("Contracts updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error updating contracts", e);
            return new ResponseEntity<>("Error updating contracts", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

