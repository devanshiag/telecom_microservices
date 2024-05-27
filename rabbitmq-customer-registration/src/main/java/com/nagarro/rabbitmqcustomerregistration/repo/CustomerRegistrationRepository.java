package com.nagarro.rabbitmqcustomerregistration.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.rabbitmqcustomerregistration.dto.CustomerDetails;
import com.nagarro.rabbitmqcustomerregistration.model.CustomerRegistration;

@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, String> {
	
	CustomerRegistration findByCustomerId(String customerId);
	CustomerRegistration findBymsisdn(String msisdn);
}
