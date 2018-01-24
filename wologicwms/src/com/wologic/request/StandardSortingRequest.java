package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StandardPackTaskDetailRequest锛氭爣鍝佸寘瑁呬换鍔″崟鏄庣粏璇锋眰鍙傛暟
 * 
 * @author jinsicao
 * 
 */
public class StandardSortingRequest  {


    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    
    private String barCode;
    
  
   private BigDecimal sortingNum;
    
   private String skuCode; 
    
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
