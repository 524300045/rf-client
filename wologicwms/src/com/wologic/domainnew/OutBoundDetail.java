package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OutBoundDetail implements java.io.Serializable  {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 出库任务单号 */
    private String outboundTaskCode; 
    /** 入库任务单号 */
    private String inboundTaskCode; 
	/** 状态 */
    private Integer status; 
    /** 商品编码 */
    private String skuCode; 
    /** 商品名称 */
    private String goodsName; 
    /** 供应商编码 */
    private String partnerCode; 
    /** 供应商名称 */
    private String partnerName; 
    /** 是否生鲜 1：是 0：否 */
    private Integer isFresh; 
    private Integer isSyncDiff;
    /** 包规（数量） */
    private BigDecimal modelNum; 
    /** 计价单位（斤、两） */
    private String goodsUnit; 
    /** 物理单位（包、箱、瓶） */
    private String physicsUnit; 
    /** 计划数量 */
    private BigDecimal planNum; 
    /** 计划上限数量 */
    private BigDecimal upPlanNum; 
    /** 计划下限数量 */
    private BigDecimal downPlanNum; 
    /** 实际数量 */
    private BigDecimal receiveNum; 
    /** 创建时间 */
    private Date receiveTime; 
    /** 上批次出库量 */
    private BigDecimal previousNum; 
    /** 分拣数量 */
    private BigDecimal sortingNum; 
    /** 分拣时间 */
    private Date sortingTime; 
    /** 发运数量*/
    private BigDecimal deliveryNum; 
    /** 发运时间 */
    private Date deliveryTime; 
    /** 可退量 */
    private BigDecimal allowReturnNum; 
    /** 退货量 */
    private BigDecimal returnNum; 
    /** 退货时间 */
    private Date returnTime; 
    /** 税率 */
    private BigDecimal taxRate; 
    /** 计划到货日期 */
    private Date planArrivalDate; 
    /** 含税单价 */
    private BigDecimal taxPrice; 
    /** 无税单价 */
    private BigDecimal taxNoPrice; 
    /** 金额 */
    private BigDecimal amount; 
    /** 税额 */
    private BigDecimal tax; 
    /** 价税合计 */
    private BigDecimal taxCount; 
    /** 创建时间 */
    private Date operateTime; 
    /** 创建时间 */
    private Date createTime; 
    /** 创建人 */
    private String createUser; 
    /** 更新时间 */
    private Date updateTime; 
    /** 更新人 */
    private String updateUser; 
    /** 是否有效 */
    private Integer yn; 
    
	/** 规格型号 */
    private String goodsModel; 
    
    
    /** 出库任务单号集合 */
    private List<String> outboundTaskCodes; 
    
    /**本次发运量*/
    private BigDecimal currentDeliverNum;
    
    /**采购单号*/
    private String orderNo;
    /** 门店编码 */
    private String storedCode;
    
    
    public String getStoredCode() {
		return storedCode;
	}

	public void setStoredCode(String storedCode) {
		this.storedCode = storedCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getCurrentDeliverNum() {
		return currentDeliverNum;
	}

	public void setCurrentDeliverNum(BigDecimal currentDeliverNum) {
		this.currentDeliverNum = currentDeliverNum;
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
    
    public String getInboundTaskCode() {
		return inboundTaskCode;
	}

	public void setInboundTaskCode(String inboundTaskCode) {
		this.inboundTaskCode = inboundTaskCode;
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
    
    public Integer getIsSyncDiff() {
		return isSyncDiff;
	}

	public void setIsSyncDiff(Integer isSyncDiff) {
		this.isSyncDiff = isSyncDiff;
	}

	public BigDecimal getModelNum(){
        return modelNum;
    }
        
    public void setModelNum(BigDecimal modelNum) {
        this.modelNum = modelNum;
    }
    
    public String getGoodsUnit(){
        return goodsUnit;
    }
        
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
    
    public String getPhysicsUnit(){
        return physicsUnit;
    }
        
    public void setPhysicsUnit(String physicsUnit) {
        this.physicsUnit = physicsUnit;
    }
    
    public BigDecimal getPlanNum(){
        return planNum;
    }
        
    public void setPlanNum(BigDecimal planNum) {
        this.planNum = planNum;
    }
    
    public BigDecimal getUpPlanNum(){
        return upPlanNum;
    }
        
    public void setUpPlanNum(BigDecimal upPlanNum) {
        this.upPlanNum = upPlanNum;
    }
    
    public BigDecimal getDownPlanNum(){
        return downPlanNum;
    }
        
    public void setDownPlanNum(BigDecimal downPlanNum) {
        this.downPlanNum = downPlanNum;
    }
    
    public BigDecimal getReceiveNum(){
        return receiveNum;
    }
        
    public void setReceiveNum(BigDecimal receiveNum) {
        this.receiveNum = receiveNum;
    }
    
    public Date getReceiveTime(){
        return receiveTime;
    }
        
    public void setReceiveTime(Date receiveTime) {
        this.receiveTime = receiveTime;
    }
    
    public BigDecimal getPreviousNum(){
        return previousNum;
    }
        
    public void setPreviousNum(BigDecimal previousNum) {
        this.previousNum = previousNum;
    }
    
    public BigDecimal getSortingNum(){
        return sortingNum;
    }
        
    public void setSortingNum(BigDecimal sortingNum) {
        this.sortingNum = sortingNum;
    }
    
    public Date getSortingTime(){
        return sortingTime;
    }
        
    public void setSortingTime(Date sortingTime) {
        this.sortingTime = sortingTime;
    }
    
    public BigDecimal getDeliveryNum(){
        return deliveryNum;
    }
        
    public void setDeliveryNum(BigDecimal deliveryNum) {
        this.deliveryNum = deliveryNum;
    }
    
    public Date getDeliveryTime(){
        return deliveryTime;
    }
        
    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    
    public BigDecimal getAllowReturnNum(){
        return allowReturnNum;
    }
        
    public void setAllowReturnNum(BigDecimal allowReturnNum) {
        this.allowReturnNum = allowReturnNum;
    }
    
    public BigDecimal getReturnNum(){
        return returnNum;
    }
        
    public void setReturnNum(BigDecimal returnNum) {
        this.returnNum = returnNum;
    }
    
    public Date getReturnTime(){
        return returnTime;
    }
        
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
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

	public String getGoodsModel() {
		return goodsModel;
	}

	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}

	public List<String> getOutboundTaskCodes() {
		return outboundTaskCodes;
	}

	public void setOutboundTaskCodes(List<String> outboundTaskCodes) {
		this.outboundTaskCodes = outboundTaskCodes;
	}
    
}