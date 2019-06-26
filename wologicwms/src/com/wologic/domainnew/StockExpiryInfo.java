package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

public class StockExpiryInfo {
	
	  /**  */
    private Long id; 
    
  
    /** 客户编码 */
    private String customerCode; 
    /** 仓库编码 */
    private String warehouseCode; 
    /** 仓库名称 */
    private String warehouseName; 
    /** 库区编码 */
    private String areaCode; 
    /** 库区名称 */
    private String areaName; 
    /** 商品编码 */
    private String skuCode; 
    /** 商品名称 */
    private String goodsName; 
    /** 一级分类编码 */
    private String categoryCode; 
    /** 一级分类名称 */
    private String categoryName; 
    /** 二级分类编码 */
    private String twoCategoryCode; 
    /** 二级分类名称 */
    private String twoCategoryName; 
    /** 三级分类编码 */
    private String threeCategoryCode; 
    /** 三级分类名称 */
    private String threeCategoryName; 
    /** 规格型号 */
    private String goodsModel; 
    /** 等级 */
    private String goodsGrade; 
    /** 总库存（实物总库存；盘点、报损使用） */
    private BigDecimal totalStock; 
    /** 有效库存（正品库存；可用库存=正品库存-预占库存） */
    private BigDecimal realStock; 
    /** 预占库存（销售拣货占用，出库扣减） */
    private BigDecimal occupyStock; 
    /** 残品库存 */
    private BigDecimal unqualifiedStock; 
    /** 锁定库存 */
    private BigDecimal lockStock; 
    /** 生产日期 */
    private Date productionDate; 
    /** 需要修改后的目标生产日期 */
    private Date descProductionDate; 
    /** 效期 */
    private Double expiryDate; 
    /** 有效期至 */
    private Date periodDate;
    /** 距失效期天数 */
    private long toPeriodDate;
    /** 备注 */
    private String description; 
    /** 是否锁定 （1 锁定； 0 未锁定） */
    private Integer isLock; 
    /** 锁定时间 */
    private Date lockTime; 
    /** 锁定原因 */
    private String lockDesc; 
    /** 锁定人 */
    private String lockUser; 
    /** 创建时间 */
    private Date createTime; 
    /** 创建人 */
    private String createUser; 
    /** 更新时间 */
    private Date updateTime; 
    /** 更新人 */
    private String updateUser; 
    /** 是否有效 （0 无效 ；1 有效 ） */
    private Integer yn;
    
	/**
     * 计价单位
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
