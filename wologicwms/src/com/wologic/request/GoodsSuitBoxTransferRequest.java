package com.wologic.request;

import java.math.BigDecimal;
import java.util.List;

import com.wologic.domainnew.GoodsSuitBoxTransferDetail;

public class GoodsSuitBoxTransferRequest {


	private String customerCode; 
    private String customerName;
    private String warehouseCode;
    private String warehouseName;
    

	private String skuCode;
	

	private String boxCode;
	
	
	private String productionDate;
	

	private BigDecimal weight;
	
	
	
	/**
	 * 0:正常 1:已使用
	 */
	private Integer status;
	
	/**
	 * 来源来源0:康安 1:供应商
	 */
	private Integer source;
	
	 private String createUser; 
	 
	 
	
	private List<GoodsSuitBoxTransferDetail>  detail;



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



	public String getWarehouseCode() {
		return warehouseCode;
	}



	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}



	public String getWarehouseName() {
		return warehouseName;
	}



	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}



	public String getSkuCode() {
		return skuCode;
	}



	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}



	public String getBoxCode() {
		return boxCode;
	}



	public void setBoxCode(String boxCode) {
		this.boxCode = boxCode;
	}



	public String getProductionDate() {
		return productionDate;
	}



	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}



	public BigDecimal getWeight() {
		return weight;
	}



	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}



	public Integer getStatus() {
		return status;
	}



	public void setStatus(Integer status) {
		this.status = status;
	}



	public Integer getSource() {
		return source;
	}



	public void setSource(Integer source) {
		this.source = source;
	}



	public String getCreateUser() {
		return createUser;
	}



	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}



	public List<GoodsSuitBoxTransferDetail> getDetail() {
		return detail;
	}



	public void setDetail(List<GoodsSuitBoxTransferDetail> detail) {
		this.detail = detail;
	}
	
	
	
	
}
