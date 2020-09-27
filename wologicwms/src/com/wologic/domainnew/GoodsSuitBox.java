package com.wologic.domainnew;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class GoodsSuitBox implements Serializable{

	
	/** ����id */
    private Long id; 
    private String customerCode; 
    private String customerName;
    private String warehouseCode;
    private String warehouseName;
	private String skuCode;
	private String boxCode;
	private String productionDate;
	private BigDecimal weight;
	
	private String goodsName;
	/**
	 * 0:���� 1:��ʹ��
	 */
	private Integer status;
	
	/**
	 * ��Դ��Դ0:���� 1:��Ӧ��
	 */
	private Integer source;
	
	

    
    /** ������ */
    private String createUser; 
    
  
    
    /** �޸��� */
    private String updateUser; 
    
    /** ��Ч��ʾ��1����Ч��0����Ч�� */
    private Integer yn;
    
    private List<GoodsSuitBoxDetail> detailList;
    
    
    

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public List<GoodsSuitBoxDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<GoodsSuitBoxDetail> detailList) {
		this.detailList = detailList;
	}

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

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Integer getYn() {
		return yn;
	}

	public void setYn(Integer yn) {
		this.yn = yn;
	}

	public String getProductionDate() {
		return productionDate;
	}

	public void setProductionDate(String productionDate) {
		this.productionDate = productionDate;
	}
    
    
    
}
