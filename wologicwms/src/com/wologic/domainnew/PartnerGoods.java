package com.wologic.domainnew;

import java.math.BigDecimal;

public class PartnerGoods {
	
	private String partnerCode;

	/**供应商名称*/
	private String partnerName;
	
	/** 商品编码 */
	private String skuCode;
	
	/** 商品名称 */
	private String goodsName;
	
	/** 计价单位（斤、两） */
	private String goodsUnit;
	
	 /*计划量*/
    private BigDecimal planNum;
    
    /**
     * 分拣量
     */
    private BigDecimal sortingNum;
    
    

	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}

	public BigDecimal getSortingNum() {
		return sortingNum;
	}

	public void setSortingNum(BigDecimal sortingNum) {
		this.sortingNum = sortingNum;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
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

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	
	
	
	
}
