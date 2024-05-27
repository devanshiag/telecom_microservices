package com.nagarro.rabbitmqcustomerverification.dto;

import com.nagarro.rabbitmqcustomerverification.entity.GeoLocation;

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
	private String contactNumber;
	@Embedded
	private GeoLocation geolocation;
	private String status;
	
	public CustomerRegistration() {
    }
	
	public CustomerRegistration(String customerId, String customerCode, String firstName, String lastName,
			String contactNumber, GeoLocation geolocation, String status) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.geolocation = geolocation;
		this.status = status;
	}
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
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
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
				+ firstName + ", lastName=" + lastName + ", contactNumber=" + contactNumber + ", geolocation="
				+ geolocation + ", status=" + status + "]";
	}
	
	

}
