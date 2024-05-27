package com.nagarro.shoppingcart.model;


public class Product {
    private String rateplanId;
    private String rateplan;
    private String MSISDN;
    
	public Product(String rateplanId, String rateplan, String MSISDN) {
		super();
		this.rateplanId = rateplanId;
		this.rateplan = rateplan;
		this.MSISDN = MSISDN;
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


	public void setMSISDN(String MSISDN) {
	    this.MSISDN = MSISDN;
	}



	@Override
	public String toString() {
		return "Product [rateplanId=" + rateplanId + ", rateplan=" + rateplan + ", MSISDN=" + MSISDN + "]";
	}

    
}