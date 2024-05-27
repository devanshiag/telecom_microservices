package com.nagarro.rabbitmqcustomerverification.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@Column(unique = true)
	private String customerId;
	private String customerCode;
	private String firstName;
	private String lastName;
	private String contactNumber;
	@Embedded
	private GeoLocation geolocation;
	

	public Customer() {
		
	}
	public Customer(String customerId, String customerCode, String firstName, String lastName, String contactNumber,
			GeoLocation geolocation) {
		super();
		this.customerId = customerId;
		this.customerCode = customerCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contactNumber = contactNumber;
		this.geolocation = geolocation;
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
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerCode=" + customerCode + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", contactNumber=" + contactNumber + ", geolocation=" + geolocation + "]";
	}

}
