package com.nagarro.rabbitmqcustomerregistration.dto;

import java.util.List;

public class CustomerDetails {

	private String customerId;
	private String customerCode;
	private boolean emailVerificationStatus;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private String preferredLanguage;
	private boolean paymentResponsible;
	private boolean emailSent;
	private GeoLocation geolocation;
	private String code;
	private String statusCode;
	private MessageResource messageResource;
	private List<String> contracts;
	private String msisdn;

	public String getMSISDN() {
		return msisdn;
	}

	public void setMSISDN(String msisdn) {
		this.msisdn = msisdn;
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

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
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

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public boolean isPaymentResponsible() {
		return paymentResponsible;
	}

	public void setPaymentResponsible(boolean paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public GeoLocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(GeoLocation geolocation) {
		this.geolocation = geolocation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public MessageResource getMessageResource() {
		return messageResource;
	}

	public void setMessageResource(MessageResource messageResource) {
		this.messageResource = messageResource;
	}
	public List<String> getContracts() {
        return contracts;
    }

    public void setContracts(List<String> contracts) {
        this.contracts = contracts;
    }

	@Override
	public String toString() {
		return "CustomerDetails [customerId=" + customerId + ", customerCode=" + customerCode
				+ ", emailVerificationStatus=" + emailVerificationStatus + ", firstName=" + firstName + ", lastName="
				+ lastName + ", contactNumber=" + contactNumber + ", preferredLanguage=" + preferredLanguage
				+ ", paymentResponsible=" + paymentResponsible + ", emailSent=" + emailSent + ", geolocation="
				+ geolocation + ", contracts=" + contracts + ", MSISDN=" + msisdn + "]";
	}
    

}
