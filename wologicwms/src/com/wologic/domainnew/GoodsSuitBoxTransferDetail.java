package com.wologic.domainnew;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GoodsSuitBoxTransferDetail implements Serializable {

	
	/** 主键id */
    private Long id; 
    
    private String customerCode; 
    private String customerName;
    private String warehouseCode;
    private String warehouseName;
    
	
  
	private String skuCode;
	

	private String boxCode;
	
	
	private String productionDate;
	

	private BigDecimal weight;
	
	
	private String childSkuCode;
	
	private String childBarCode;
	
	private BigDecimal childWeight;
	
	/**
	 * 0:正常 1:已使用
	 */
	private Integer status;
	
	/**
	 * 来源来源0:康安 1:供应商
	 */
	private Integer source;
	
	
 
    
    /** 创建人 */
    private String createUser; 
    
  
    
    /** 修改人 */
    private String updateUser;



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



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



	public String getChildSkuCode() {
		return childSkuCode;
	}



	public void setChildSkuCode(String childSkuCode) {
		this.childSkuCode = childSkuCode;
	}



	public String getChildBarCode() {
		return childBarCode;
	}



	public void setChildBarCode(String childBarCode) {
		this.childBarCode = childBarCode;
	}



	public BigDecimal getChildWeight() {
		return childWeight;
	}



	public void setChildWeight(BigDecimal childWeight) {
		this.childWeight = childWeight;
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



	public String getUpdateUser() {
		return updateUser;
	}



	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	} 
    
    
    
    
}
