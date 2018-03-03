package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PmsOrderPurchaseDetail閿涙岸鍣扮拹顓☆吂閸楁洘妲戠紒锟�(閺嶅洤鎼�)鐎圭偘缍嬬猾锟�
 * 
 * @author jinsicao
 * 
 */
public class PmsOrderPurchaseDetail implements java.io.Serializable  {

    /** 鎼村繐鍨崠鏍ㄧ垼鐠囷拷 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 鐠併垹宕焛d */
    private String orderNo; 
    /** 閸熷棗鎼х紓鏍垳 */
    private String skuCode; 
    /** 閸熷棗鎼ч崥宥囆� */
    private String goodsName; 
    /** 鐎广垺鍩涢崯鍡楁惂缂傛牜鐖� */
    private String customerSkuCode; 
    /** 鐎广垺鍩涢崯鍡楁惂閸氬秶袨 */
    private String customerGoodsName; 
    /** 娓氭稑绨查崯鍡欑椽閻拷 */
    private String partnerCode; 
    /** 娓氭稑绨查崯鍡楁倳缁夛拷 */
    private String partnerName; 
    /** 閺勵垰鎯侀悽鐔肩煘 1閿涙碍妲� 0閿涙艾鎯� */
    private Integer isFresh; 
    /** 閺勵垰鎯佺挧鐘叉惂 1閿涙碍妲� 0閿涙艾鎯� */
    private Integer isGift; 
    /** 鐠佲�冲灊閺佷即鍣� */
    private BigDecimal planNum; 
    /** 鐎圭偤妾弫浼村櫤 */
    private BigDecimal realityNum; 
    /** 缁嬪海宸� */
    private BigDecimal taxRate; 
    /** 鐠佲�冲灊閸掓媽鎻ｉ弮銉︽埂 */
    private Date planArrivalDate; 
    /** 閸氼偆鈼ｉ崡鏇氱幆 */
    private BigDecimal taxPrice; 
    /** 閺冪姷鈼ｉ崡鏇氱幆 */
    private BigDecimal taxNoPrice; 
    /** 闁叉垿顤� */
    private BigDecimal amount; 
    /** 缁嬪酣顤� */
    private BigDecimal tax; 
    /** 娴犻鈼ｉ崥鍫ｎ吀 */
    private BigDecimal taxCount; 
    /** 鐠併垹宕熼悩鑸碉拷锟� */
    private Integer orderState; 
    /** 閸掓稑缂撻弮鍫曟？ */
    private Date createTime; 
    /** 閸掓稑缂撴禍锟� */
    private String createUser; 
    /** 閺囧瓨鏌婇弮鍫曟？ */
    private Date updateTime; 
    /** 閺囧瓨鏌婃禍锟� */
    private String updateUser; 
    /** 閺勵垰鎯侀張澶嬫櫏 */
    private Integer yn; 
    
    private String warehouseName;
    
	private String customerName;
	
	private String goodsModel;
	private String goodsUnit;
    
    public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


    
    public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOrderNo(){
        return orderNo;
    }
        
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
    
    public String getSkuCode(){
        return skuCode;
    }
        
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    
    public String getGoodsName(){
        return goodsName;
    }
        
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getCustomerSkuCode(){
        return customerSkuCode;
    }
        
    public void setCustomerSkuCode(String customerSkuCode) {
        this.customerSkuCode = customerSkuCode;
    }
    
    public String getCustomerGoodsName(){
        return customerGoodsName;
    }
        
    public void setCustomerGoodsName(String customerGoodsName) {
        this.customerGoodsName = customerGoodsName;
    }
    
    public String getPartnerCode(){
        return partnerCode;
    }
        
    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }
    
    public String getPartnerName(){
        return partnerName;
    }
        
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    
    public Integer getIsFresh(){
        return isFresh;
    }
        
    public void setIsFresh(Integer isFresh) {
        this.isFresh = isFresh;
    }
    
    public Integer getIsGift(){
        return isGift;
    }
        
    public void setIsGift(Integer isGift) {
        this.isGift = isGift;
    }
    
    public BigDecimal getPlanNum(){
        return planNum;
    }
        
    public void setPlanNum(BigDecimal planNum) {
        this.planNum = planNum;
    }
    
    public BigDecimal getRealityNum(){
        return realityNum;
    }
        
    public void setRealityNum(BigDecimal realityNum) {
        this.realityNum = realityNum;
    }
    
    public BigDecimal getTaxRate(){
        return taxRate;
    }
        
    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }
    
    public Date getPlanArrivalDate(){
        return planArrivalDate;
    }
        
    public void setPlanArrivalDate(Date planArrivalDate) {
        this.planArrivalDate = planArrivalDate;
    }
    
    public BigDecimal getTaxPrice(){
        return taxPrice;
    }
        
    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }
    
    public BigDecimal getTaxNoPrice(){
        return taxNoPrice;
    }
        
    public void setTaxNoPrice(BigDecimal taxNoPrice) {
        this.taxNoPrice = taxNoPrice;
    }
    
    public BigDecimal getAmount(){
        return amount;
    }
        
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public BigDecimal getTax(){
        return tax;
    }
        
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public BigDecimal getTaxCount(){
        return taxCount;
    }
        
    public void setTaxCount(BigDecimal taxCount) {
        this.taxCount = taxCount;
    }
    
    public Integer getOrderState(){
        return orderState;
    }
        
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
    
    public Date getCreateTime(){
        return createTime;
    }
        
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateUser(){
        return createUser;
    }
        
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getUpdateUser(){
        return updateUser;
    }
        
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Integer getYn(){
        return yn;
    }
        
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}
