package com.wologic.response;

import java.math.BigDecimal;

public class OutBoundResponse {

	private Long id; 
    /** 出库任务单号 */
    private String outboundTaskCode; 
    /** 状态 */
    private Integer status; 
    /** 是否打印 */
    private Integer isPrint; 
    /** 打印时间 */
    private String printTime; 
    /** 打印人 */
    private String printMan; 
    /** 是否分拣完成 */
    private Integer isSorting; 
    /** 分拣完成时间 */
    private String finishSortingTime; 
    /** 是否发运完成 */
    private Integer isDelivery; 
    /** 发运时间 */
    private String deliveryTime; 
    /** 发运人 */
    private String deliveryMan; 
    /** 线路编码 */
    private String lineCode; 
    /** 车辆编码 */
    private String vehicleCode; 
    /** 采购单号 */
    private String orderNo; 
    /** 单据类型 */
    private Integer orderType; 
    /** 单据来源 */
    private Integer orderSource; 
    /** 业务类型 */
    private Integer businessType; 
    /** 仓库编码 */
    private String warehouseCode; 
    /** 城市编码 */
    private String regionCode; 
    /** 门店编码 */
    private String storedCode; 
    /** 原单据编号 */
    private String originOrderNo; 
    /** 客户编码 */
    private String customerCode; 
    /** 业务员 */
    private String salesman; 
    /** 付款条件 */
    private String paymentClause; 
    /** 币种 */
    private Integer currency; 
    /** 汇率 */
    private BigDecimal exchangeRate; 
    /** 备注 */
    private String remark; 
    /** 订单日期 */
    private String orderDate; 
    /** 配送日期 */
    private String deliveryDate; 
    /** 创建时间 */
    private String operateTime; 
    /** 创建时间 */
    private String createTime; 
    /** 创建人 */
    private String createUser; 
    /** 更新时间 */
    private String updateTime; 
    /** 更新人 */
    private String updateUser; 
    /** 是否有效 */
    private Integer yn; 
    
    /** 门店编码 */
    private String storedName; 
    public String getStoredName() {
		return storedName;
	}

	public void setStoredName(String storedName) {
		this.storedName = storedName;
	}

	/**
     * 明细中已完成的数量
     */
    private Integer finishNum;
    
    /**
     * 明细中总的数量
     */
    private Integer totalNum;
    
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

	public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getOutboundTaskCode(){
        return outboundTaskCode;
    }
        
    public void setOutboundTaskCode(String outboundTaskCode) {
        this.outboundTaskCode = outboundTaskCode;
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
    
    public String getPrintTime(){
        return printTime;
    }
        
    public void setPrintTime(String printTime) {
        this.printTime = printTime;
    }
    
    public String getPrintMan(){
        return printMan;
    }
        
    public void setPrintMan(String printMan) {
        this.printMan = printMan;
    }
    
    public Integer getIsSorting(){
        return isSorting;
    }
        
    public void setIsSorting(Integer isSorting) {
        this.isSorting = isSorting;
    }
    
    public String getFinishSortingTime(){
        return finishSortingTime;
    }
        
    public void setFinishSortingTime(String finishSortingTime) {
        this.finishSortingTime = finishSortingTime;
    }
    
    public Integer getIsDelivery(){
        return isDelivery;
    }
        
    public void setIsDelivery(Integer isDelivery) {
        this.isDelivery = isDelivery;
    }
    
    public String getDeliveryTime(){
        return deliveryTime;
    }
        
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    
    public String getDeliveryMan(){
        return deliveryMan;
    }
        
    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }
    
    public String getLineCode(){
        return lineCode;
    }
        
    public void setLineCode(String lineCode) {
        this.lineCode = lineCode;
    }
    
    public String getVehicleCode(){
        return vehicleCode;
    }
        
    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
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
    
    public String getOrderDate(){
        return orderDate;
    }
        
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    public String getDeliveryDate(){
        return deliveryDate;
    }
        
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    
    public String getOperateTime(){
        return operateTime;
    }
        
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }
    
    public String getCreateTime(){
        return createTime;
    }
        
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getCreateUser(){
        return createUser;
    }
        
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public String getUpdateTime(){
        return updateTime;
    }
        
    public void setUpdateTime(String updateTime) {
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
