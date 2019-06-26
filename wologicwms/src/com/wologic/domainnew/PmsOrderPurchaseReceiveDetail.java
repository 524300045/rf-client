package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * PmsOrderPurchaseReceiveDetail锛氶噰璐鍗曟敹璐ф槑缁�(鏍囧搧)瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class PmsOrderPurchaseReceiveDetail implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 閲囪喘鍗曟槑缁咺D */
    private Long detailId; 
    /** 璁㈠崟id */
    private String orderNo; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 鏀惰揣鏁伴噺 */
    private BigDecimal receiveNum; 
    /** 鐢熶骇鏃ユ湡 */
    private Date productionDate; 
    /** 鏁堟湡 */
    private Double expiryDate; 
    /** 瀹㈡埛缂栫爜 */
    private String customerCode; 
    /** 浠撳簱缂栫爜 */
    private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
    private String warehouseName; 
    /** 搴撳尯缂栫爜 */
    private String areaCode; 
    /** 搴撳尯鍚嶇О */
    private String areaName; 
    /** 璁㈠崟鐘舵�� */
    private Integer orderState; 
    /** 鏀惰揣鏃堕棿 */
    private Date receiveTime; 
    /** 鏀惰揣浜� */
    private String receiveUser; 
    /** 纭鏀惰揣鏃堕棿 */
    private Date confirmReceiveTime; 
    /** 纭鏀惰揣浜� */
    private String confirmReceiveUser; 
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
    
    private String goodsUnit;
    
    public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getDetailId(){
        return detailId;
    }
        
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
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
    
    public BigDecimal getReceiveNum(){
        return receiveNum;
    }
        
    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }
    
    public Date getProductionDate(){
        return productionDate;
    }
        
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    
    public Double getExpiryDate(){
        return expiryDate;
    }
        
    public void setExpiryDate(Double expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
    
    public String getAreaCode(){
        return areaCode;
    }
        
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    public String getAreaName(){
        return areaName;
    }
        
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public Integer getOrderState(){
        return orderState;
    }
        
    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }
    
    public Date getReceiveTime(){
        return receiveTime;
    }
        
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
    
    public String getReceiveUser(){
        return receiveUser;
    }
        
    public void setReceiveUser(String receiveUser) {
        this.receiveUser = receiveUser;
    }
    
    public Date getConfirmReceiveTime(){
        return confirmReceiveTime;
    }
        
    public void setConfirmReceiveTime(Date confirmReceiveTime) {
        this.confirmReceiveTime = confirmReceiveTime;
    }
    
    public String getConfirmReceiveUser(){
        return confirmReceiveUser;
    }
        
    public void setConfirmReceiveUser(String confirmReceiveUser) {
        this.confirmReceiveUser = confirmReceiveUser;
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
