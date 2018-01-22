package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



/**
 * PackTaskQuery：包装任务单查询�?
 * 
 * @author jinsicao
 * 
 */
public class StandPackTaskCodesRequest  {
    
    /** 主键 */
	private Long id; 
    /** 包装任务单号 */
	private String packTaskCode; 
    /** 包装任务类型 */
	private Integer packTaskType; 
    /** 状�?? */
	private Integer status; 
    /** 商品编码 */
	private String skuCode; 
    /** 商品名称 */
	private String goodsName; 
    /** 订单总数 */
	private Integer orderNum; 
    /** 进度 */
	private Integer progress; 
    /** 订单总量 */
	private BigDecimal orderCount; 
    /** 供应商编�? */
	private String partnerCode; 
    /** 供应商名�? */
	private String partnerName; 
    /** 仓库编码 */
	private String warehouseCode; 
    /** 城市编码 */
	private String regionCode; 
    /** 原单据编�? */
	private String originOrderNo; 
    /** 客户编码 */
	private String customerCode; 
    /** 订单日期 */
	private Date orderDate; 
    /** 配�?�日�? */
	private Date deliveryDate; 
    /** 创建时间 */
	private Date operateTime; 
    /** 创建时间 */
	private Date createTime; 
    /** 创建�? */
	private String createUser; 
    /** 更新时间 */
	private Date updateTime; 
    /** 更新�? */
	private String updateUser; 
    /** 是否有效 */
	private Integer yn; 
    /** �?始时�? */
    private String startTime; 
    /** 结束时间 */
    private String endTime; 
    
    private List<String> skuCodes;
	
    public Long getId(){
		return id;
	}
	    
   

	public List<String> getSkuCodes() {
		return skuCodes;
	}



	public void setSkuCodes(List<String> skuCodes) {
		this.skuCodes = skuCodes;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
    public String getPackTaskCode(){
		return packTaskCode;
	}
	    
    public void setPackTaskCode(String packTaskCode) {
		this.packTaskCode = packTaskCode;
	}
	
    public Integer getPackTaskType(){
		return packTaskType;
	}
	    
    public void setPackTaskType(Integer packTaskType) {
		this.packTaskType = packTaskType;
	}
	
    public Integer getStatus(){
		return status;
	}
	    
    public void setStatus(Integer status) {
		this.status = status;
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
	
    public Integer getOrderNum(){
		return orderNum;
	}
	    
    public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	
    public Integer getProgress(){
		return progress;
	}
	    
    public void setProgress(Integer progress) {
		this.progress = progress;
	}
	
    public BigDecimal getOrderCount(){
		return orderCount;
	}
	    
    public void setOrderCount(BigDecimal orderCount) {
		this.orderCount = orderCount;
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
	
    public String getWarehouseCode(){
		return warehouseCode;
	}
	    
    public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
    public String getRegionCode(){
		return regionCode;
	}
	    
    public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
