package com.wologic.request;

import java.math.BigDecimal;

public class WmsFreeInventoryDetailRequest {
	
	  /** 商品编码 */
    private String skuCode; 
   
    /** 实盘数量 */
    private BigDecimal inventoryNum; 
    
   
    private Long stockExpirInfoId;
    
    /** 创建人 */
    private String createUser;

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getInventoryNum() {
		return inventoryNum;
	}

	public void setInventoryNum(BigDecimal inventoryNum) {
		this.inventoryNum = inventoryNum;
	}

	public Long getStockExpirInfoId() {
		return stockExpirInfoId;
	}

	public void setStockExpirInfoId(Long stockExpirInfoId) {
		this.stockExpirInfoId = stockExpirInfoId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	} 
    
    

}
