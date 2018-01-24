package com.wologic.request;

import java.util.List;

public class StandSkuTaskRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> skuCodes;
	
	 private String customerCode; 
	 
	 private String partnerCode; 
	 
	 private String warehouseCode;

	public List<String> getSkuCodes() {
		return skuCodes;
	}

	public void setSkuCodes(List<String> skuCodes) {
		this.skuCodes = skuCodes;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	} 
}
