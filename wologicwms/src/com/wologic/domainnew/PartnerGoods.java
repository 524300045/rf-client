package com.wologic.domainnew;

import java.math.BigDecimal;

public class PartnerGoods {
	
	private String partnerCode;

	/**��Ӧ������*/
	private String partnerName;
	
	/** ��Ʒ���� */
	private String skuCode;
	
	/** ��Ʒ���� */
	private String goodsName;
	
	/** �Ƽ۵�λ������� */
	private String goodsUnit;
	
	 /*�ƻ���*/
    private BigDecimal planNum;
    
    /**
     * �ּ���
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
