package com.wologic.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * PmsOrderPurchase锛氶噰璐鍗曪紙鏍囧搧锛夊疄浣撶被
 * 
 * @author jinsicao
 * 
 */
public class PmsOrderPurchase implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** 涓婚敭 */
    private Long id; 
    /** 鍗曟嵁缂栧彿 */
    private String orderNo; 
    /** 鍗曟嵁绫诲瀷 */
    private Integer orderType; 
    /** 鍗曟嵁鏉ユ簮 */
    private Integer orderSource; 
    /** 璁㈠崟鐘舵�� */
    private Integer status; 
    /** 鏄惁鎵撳嵃 */
    private Integer isPrint; 
    /** 鎵撳嵃鏃堕棿 */
    private Date printTime; 
    /** 鎵撳嵃浜� */
    private String printMan; 
    /** 涓氬姟绫诲瀷 */
    private Integer businessType; 
    /** 浠撳簱缂栫爜 */
    private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
    private String warehouseName; 
    /** 鍩庡競缂栫爜 */
    private String regionCode; 
    /** 闂ㄥ簵缂栫爜 */
    private String storedCode; 
    /** 闂ㄥ簵鍚嶇О */
    private String storedName; 
    /** 鍘熷崟鎹紪鍙� */
    private String originOrderNo; 
    /** 瀹㈡埛缂栫爜 */
    private String customerCode; 
    /** 涓氬姟鍛� */
    private String salesman; 
    /** 浠樻鏉′欢 */
    private String paymentClause; 
    /** 甯佺 */
    private Integer currency; 
    /** 姹囩巼 */
    private BigDecimal exchangeRate; 
    /** 澶囨敞 */
    private String remark; 
    /** 璁㈠崟鏃ユ湡 */
    private Date orderDate; 
    /** 閰嶉�佹棩鏈� */
    private Date deliveryDate; 
    /** 鍒涘缓鏃堕棿 */
    private Date operateTime; 
    /** 鍒涘缓鏃堕棿 */
    private Date createTime; 
    /** 鍒涘缓浜� */
    private String createUser; 
    /** 鏇存柊鏃堕棿 */
    private Date updateTime; 
    /** 鏇存柊浜� */
    private String updateUser; 
    /** 鏄惁鏈夋晥 */
    private Integer yn; 
    
    private String customerName;
    
    public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	private String partnerCode; 
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

	/** 渚涘簲鍟嗗悕绉� */
    private String partnerName; 
    
  
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
    
    public Integer getOrderType(){
        return orderType;
    }
        
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    
    public Integer getOrderSource(){
        return orderSource;
    }
        
    public void setOrderSource(Integer orderSource) {
        this.orderSource = orderSource;
    }
    
    public Integer getStatus(){
        return status;
    }
        
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getIsPrint(){
        return isPrint;
    }
        
    public void setIsPrint(Integer isPrint) {
        this.isPrint = isPrint;
    }
    
    public Date getPrintTime(){
        return printTime;
    }
        
    public void setPrintTime(Date printTime) {
        this.printTime = printTime;
    }
    
    public String getPrintMan(){
        return printMan;
    }
        
    public void setPrintMan(String printMan) {
        this.printMan = printMan;
    }
    
    public Integer getBusinessType(){
        return businessType;
    }
        
    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
    
    public String getWarehouseCode(){
        return warehouseCode;
    }
        
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    
    public String getWarehouseName(){
        return warehouseName;
    }
        
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    
    public String getRegionCode(){
        return regionCode;
    }
        
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    
    public String getStoredCode(){
        return storedCode;
    }
        
    public void setStoredCode(String storedCode) {
        this.storedCode = storedCode;
    }
    
    public String getStoredName(){
        return storedName;
    }
        
    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }
    
    public String getOriginOrderNo(){
        return originOrderNo;
    }
        
    public void setOriginOrderNo(String originOrderNo) {
        this.originOrderNo = originOrderNo;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getSalesman(){
        return salesman;
    }
        
    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }
    
    public String getPaymentClause(){
        return paymentClause;
    }
        
    public void setPaymentClause(String paymentClause) {
        this.paymentClause = paymentClause;
    }
    
    public Integer getCurrency(){
        return currency;
    }
        
    public void setCurrency(Integer currency) {
        this.currency = currency;
    }
    
    public BigDecimal getExchangeRate(){
        return exchangeRate;
    }
        
    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
    
    public String getRemark(){
        return remark;
    }
        
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public Date getOrderDate(){
        return orderDate;
    }
        
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public Date getDeliveryDate(){
        return deliveryDate;
    }
        
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    public Date getOperateTime(){
        return operateTime;
    }
        
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
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
