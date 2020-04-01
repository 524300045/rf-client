package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * StandardPackTaskDetailRequest閿涙碍鐖ｉ崫浣稿瘶鐟佸懍鎹㈤崝鈥冲礋閺勫海绮忕拠閿嬬湴閸欏倹鏆�
 * 
 * @author jinsicao
 * 
 */
public class StandardSortingRequest  {


    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    
    private String barCode;
    
    /*id集合*/
    private List<Long> ids;
  
   private BigDecimal sortingNum;
    
   private String skuCode; 
   
   private String containerCode; 
   
   /** 客户名称 */
private String customerName;


private String customerCode; 




 
   public String getCustomerCode() {
	return customerCode;
}

public void setCustomerCode(String customerCode) {
	this.customerCode = customerCode;
}

public List<Long> getIds() {
	return ids;
}

public void setIds(List<Long> ids) {
	this.ids = ids;
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

public String getUpdateUser() {
	return updateUser;
}

public void setUpdateUser(String updateUser) {
	this.updateUser = updateUser;
}

private String warehouseCode; 
   
   private String updateUser; 
    
    public String getContainerCode() {
	return containerCode;
}

public void setContainerCode(String containerCode) {
	this.containerCode = containerCode;
}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getSortingNum() {
		return sortingNum;
	}

	public void setSortingNum(BigDecimal sortingNum) {
		this.sortingNum = sortingNum;
	}
	
	

}
