package com.wologic.response;

import java.math.BigDecimal;

public class StandPickTaskResponse  {

	private BigDecimal sortingNum;
	
	 private BigDecimal realityNum; 
	 
	 public BigDecimal getRealityNum() {
		return realityNum;
	}

	public void setRealityNum(BigDecimal realityNum) {
		this.realityNum = realityNum;
	}

	public BigDecimal getSortingNum() {
		return sortingNum;
	}

	public void setSortingNum(BigDecimal sortingNum) {
		this.sortingNum = sortingNum;
	}

	private Long id; 
	 
	  public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getStoredCode() {
		return storedCode;
	}

	public void setStoredCode(String storedCode) {
		this.storedCode = storedCode;
	}

	public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}

	public Integer getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(Integer finishNum) {
		this.finishNum = finishNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/** 閸熷棗鎼х紓鏍垳 */
    private String skuCode; 
    /** 閸熷棗鎼ч崥宥囆� */
    private String goodsName; 
    
    /** 闂傘劌绨电紓鏍垳 */
    private String storedCode; 
    /** 闂傘劌绨甸崥宥囆� */
    private String storedName; 
    
    /** 鐠佲�冲灊閺佷即鍣� */
    private BigDecimal planNum;
    
    //鐎瑰本鍨氶弫浼村櫤
    private Integer finishNum;
    
    //閹粯鏆熼柌锟�
    private Integer totalNum;
    
    private String physicsUnit;

	public String getPhysicsUnit() {
		return physicsUnit;
	}

	public void setPhysicsUnit(String physicsUnit) {
		this.physicsUnit = physicsUnit;
	}
    
}
