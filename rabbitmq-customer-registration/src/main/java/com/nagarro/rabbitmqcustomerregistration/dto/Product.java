package com.nagarro.rabbitmqcustomerregistration.dto;


public class Product {
	private String contractId;
    private String rateplanId;
    private String rateplan;
    private String MSISDN;
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getRateplanId() {
		return rateplanId;
	}
	public void setRateplanId(String rateplanId) {
		this.rateplanId = rateplanId;
	}
	public String getRateplan() {
		return rateplan;
	}
	public void setRateplan(String rateplan) {
		this.rateplan = rateplan;
	}
	public String getMSISDN() {
		return MSISDN;
	}
	public void setMSISDN(String mSISDN) {
		MSISDN = mSISDN;
	}
	@Override
	public String toString() {
		return "Product [contractId=" + contractId + ", rateplanId=" + rateplanId + ", rateplan=" + rateplan
				+ ", MSISDN=" + MSISDN + "]";
	}
	
}