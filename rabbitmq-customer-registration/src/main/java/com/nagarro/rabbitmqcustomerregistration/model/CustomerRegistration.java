package com.nagarro.rabbitmqcustomerregistration.model;


import java.util.List;

import com.nagarro.rabbitmqcustomerregistration.dto.GeoLocation;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CustomerRegistration {
	
	@Id
	@Column(unique = true)
	private String customerId;
	private String customerCode;
	private String firstName;
	private String lastName;
	private String msisdn;
	private List<String> contracts;

	@Embedded
	private GeoLocation geolocation;
	private String status;
	
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMSISDN() {
		return msisdn;
	}
	public void setMSISDN(String msisdn) {
		this.msisdn = msisdn;
	}
	public List<String> getContracts() {
		return contracts;
	}
	public void setContracts(List<String> contracts) {
		this.contracts = contracts;
	}
	public GeoLocation getGeolocation() {
		return geolocation;
	}
	public void setGeolocation(GeoLocation geolocation) {
		this.geolocation = geolocation;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerRegistration [customerId=" + customerId + ", customerCode=" + customerCode + ", firstName="
				+ firstName + ", lastName=" + lastName + ", MSISDN=" + msisdn + ", contracts=" + contracts
				+ ", geolocation=" + geolocation + ", status=" + status + "]";
	}
	
	

}
