package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

public class StockExpiryInfo {
	
	  /**  */
    private Long id; 
    
  
    /** �ͻ����� */
    private String customerCode; 
    /** �ֿ���� */
    private String warehouseCode; 
    /** �ֿ����� */
    private String warehouseName; 
    /** �������� */
    private String areaCode; 
    /** �������� */
    private String areaName; 
    /** ��Ʒ���� */
    private String skuCode; 
    /** ��Ʒ���� */
    private String goodsName; 
    /** һ��������� */
    private String categoryCode; 
    /** һ���������� */
    private String categoryName; 
    /** ����������� */
    private String twoCategoryCode; 
    /** ������������ */
    private String twoCategoryName; 
    /** ����������� */
    private String threeCategoryCode; 
    /** ������������ */
    private String threeCategoryName; 
    /** ����ͺ� */
    private String goodsModel; 
    /** �ȼ� */
    private String goodsGrade; 
    /** �ܿ�棨ʵ���ܿ�棻�̵㡢����ʹ�ã� */
    private BigDecimal totalStock; 
    /** ��Ч��棨��Ʒ��棻���ÿ��=��Ʒ���-Ԥռ��棩 */
    private BigDecimal realStock; 
    /** Ԥռ��棨���ۼ��ռ�ã�����ۼ��� */
    private BigDecimal occupyStock; 
    /** ��Ʒ��� */
    private BigDecimal unqualifiedStock; 
    /** ������� */
    private BigDecimal lockStock; 
    /** �������� */
    private Date productionDate; 
    /** ��Ҫ�޸ĺ��Ŀ���������� */
    private Date descProductionDate; 
    /** Ч�� */
    private Double expiryDate; 
    /** ��Ч���� */
    private Date periodDate;
    /** ��ʧЧ������ */
    private long toPeriodDate;
    /** ��ע */
    private String description; 
    /** �Ƿ����� ��1 ������ 0 δ������ */
    private Integer isLock; 
    /** ����ʱ�� */
    private Date lockTime; 
    /** ����ԭ�� */
    private String lockDesc; 
    /** ������ */
    private String lockUser; 
    /** ����ʱ�� */
    private Date createTime; 
    /** ������ */
    private String createUser; 
    /** ����ʱ�� */
    private Date updateTime; 
    /** ������ */
    private String updateUser; 
    /** �Ƿ���Ч ��0 ��Ч ��1 ��Ч �� */
    private Integer yn;
    
	/**
     * �Ƽ۵�λ
     */
    private String goodsUnit;
    
    
    
	public String getGoodsUnit() {
		return goodsUnit;
	}
	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTwoCategoryCode() {
		return twoCategoryCode;
	}
	public void setTwoCategoryCode(String twoCategoryCode) {
		this.twoCategoryCode = twoCategoryCode;
	}
	public String getTwoCategoryName() {
		return twoCategoryName;
	}
	public void setTwoCategoryName(String twoCategoryName) {
		this.twoCategoryName = twoCategoryName;
	}
	public String getThreeCategoryCode() {
		return threeCategoryCode;
	}
	public void setThreeCategoryCode(String threeCategoryCode) {
		this.threeCategoryCode = threeCategoryCode;
	}
	public String getThreeCategoryName() {
		return threeCategoryName;
	}
	public void setThreeCategoryName(String threeCategoryName) {
		this.threeCategoryName = threeCategoryName;
	}
	public String getGoodsModel() {
		return goodsModel;
	}
	public void setGoodsModel(String goodsModel) {
		this.goodsModel = goodsModel;
	}
	public String getGoodsGrade() {
		return goodsGrade;
	}
	public void setGoodsGrade(String goodsGrade) {
		this.goodsGrade = goodsGrade;
	}
	public BigDecimal getTotalStock() {
		return totalStock;
	}
	public void setTotalStock(BigDecimal totalStock) {
		this.totalStock = totalStock;
	}
	public BigDecimal getRealStock() {
		return realStock;
	}
	public void setRealStock(BigDecimal realStock) {
		this.realStock = realStock;
	}
	public BigDecimal getOccupyStock() {
		return occupyStock;
	}
	public void setOccupyStock(BigDecimal occupyStock) {
		this.occupyStock = occupyStock;
	}
	public BigDecimal getUnqualifiedStock() {
		return unqualifiedStock;
	}
	public void setUnqualifiedStock(BigDecimal unqualifiedStock) {
		this.unqualifiedStock = unqualifiedStock;
	}
	public BigDecimal getLockStock() {
		return lockStock;
	}
	public void setLockStock(BigDecimal lockStock) {
		this.lockStock = lockStock;
	}
	public Date getProductionDate() {
		return productionDate;
	}
	public void setProductionDate(Date productionDate) {
		this.productionDate = productionDate;
	}
	public Date getDescProductionDate() {
		return descProductionDate;
	}
	public void setDescProductionDate(Date descProductionDate) {
		this.descProductionDate = descProductionDate;
	}
	public Double getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Double expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getPeriodDate() {
		return periodDate;
	}
	public void setPeriodDate(Date periodDate) {
		this.periodDate = periodDate;
	}
	public long getToPeriodDate() {
		return toPeriodDate;
	}
	public void setToPeriodDate(long toPeriodDate) {
		this.toPeriodDate = toPeriodDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getIsLock() {
		return isLock;
	}
	public void setIsLock(Integer isLock) {
		this.isLock = isLock;
	}
	public Date getLockTime() {
		return lockTime;
	}
	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	public String getLockDesc() {
		return lockDesc;
	}
	public void setLockDesc(String lockDesc) {
		this.lockDesc = lockDesc;
	}
	public String getLockUser() {
		return lockUser;
	}
	public void setLockUser(String lockUser) {
		this.lockUser = lockUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Integer getYn() {
		return yn;
	}
	public void setYn(Integer yn) {
		this.yn = yn;
	} 
    
    
    
    

}
