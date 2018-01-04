package com.wologic.domainnew;

public class CustomerInfo {
  
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return customerName;
	}

	private String customerCode;
	
	private String customerName;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
