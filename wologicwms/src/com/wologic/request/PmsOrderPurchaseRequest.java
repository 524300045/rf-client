package com.wologic.request;

public class PmsOrderPurchaseRequest {

	private String orderNo; 
	  /** �ͻ����� */
	private String customerCode;
    /** ��Ӧ�̱��� */
    private String partnerCode;
    
    
    /** �ֿ���� */
	private String warehouseCode; 
    
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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
}
