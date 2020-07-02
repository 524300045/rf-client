package com.wologic.domainnew;

import java.math.BigDecimal;

public class CustomerGoods {

	
	 /** 主键id */
    private Long id; 
    /** 客户编码 */
    private String customerCode; 
    private String customerName;
    private String warehouseCode;
    private String warehouseName;
    /** 客户商品编码 */
    private String customerSkuCode; 
    /** 客户商品名称 */
    private String customerGoodsName; 
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
    /** 品牌 */
    private String goodsBrand; 
    /** 产地 */
    private String madeIn; 
    /** 重量 */
    private BigDecimal weight; 
    /** 长 */
    private BigDecimal packLong; 
    /** 宽 */
    private BigDecimal packWide; 
    /** 高 */
    private BigDecimal packHigh; 
    /** 建议采购单价 */
    private BigDecimal suggestPrice; 
    /** 特殊要求 */
    private Integer specialAsk; 
    /** 包规（数量） */
    private BigDecimal modelNum; 
    /** 计价单位（斤、两） */
    private String goodsUnit; 
    /** 物理单位（包、箱、瓶） */
    private String physicsUnit; 
    /** 包装冗余上线 */
    private Integer upLimit; 
    /** 包装冗余下线 */
    private Integer downLimit; 
    /** 是否生鲜 1：是 0：否 */
    private Integer isFresh; 
    /** 是否称重 1：是 0：否 */
    private Integer weighed; 
    /** 是否预加工 1：是 0：否 */
    private Integer isPreprocess; 
    /** 参考成本 */
    private String referenceCost; 
    /** abc分类 */
    private String abcClass; 
    /** 启用日期 */
   
    /** 启用停用标识:1：是 0：否 */
    private Integer enabled; 
    /** 国标码 */
    private String gbCode; 
    /** 税率 */
    private BigDecimal taxRate; 
    /** 效期 */
    private Double expiryDate; 
    /** 创建时间 */
    
    /** 创建人 */
    private String createUser; 
    /** 更新时间 */
    
    /** 更新人 */
    private String updateUser; 
    /** 是否有效 */
    private Integer yn;



    /** 库区编码 */
    private String areaCode;
    /** 库区名称 */
    private String areaName;
    
    private String skuCode;
    
    //保鲜温区属性
    private Integer freshAttr; 
    
     private String areaAttr;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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

	public String getCustomerSkuCode() {
		return customerSkuCode;
	}

	public void setCustomerSkuCode(String customerSkuCode) {
		this.customerSkuCode = customerSkuCode;
	}

	public String getCustomerGoodsName() {
		return customerGoodsName;
	}

	public void setCustomerGoodsName(String customerGoodsName) {
		this.customerGoodsName = customerGoodsName;
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

	public String getGoodsBrand() {
		return goodsBrand;
	}

	public void setGoodsBrand(String goodsBrand) {
		this.goodsBrand = goodsBrand;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getPackLong() {
		return packLong;
	}

	public void setPackLong(BigDecimal packLong) {
		this.packLong = packLong;
	}

	public BigDecimal getPackWide() {
		return packWide;
	}

	public void setPackWide(BigDecimal packWide) {
		this.packWide = packWide;
	}

	public BigDecimal getPackHigh() {
		return packHigh;
	}

	public void setPackHigh(BigDecimal packHigh) {
		this.packHigh = packHigh;
	}

	public BigDecimal getSuggestPrice() {
		return suggestPrice;
	}

	public void setSuggestPrice(BigDecimal suggestPrice) {
		this.suggestPrice = suggestPrice;
	}

	public Integer getSpecialAsk() {
		return specialAsk;
	}

	public void setSpecialAsk(Integer specialAsk) {
		this.specialAsk = specialAsk;
	}

	public BigDecimal getModelNum() {
		return modelNum;
	}

	public void setModelNum(BigDecimal modelNum) {
		this.modelNum = modelNum;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public String getPhysicsUnit() {
		return physicsUnit;
	}

	public void setPhysicsUnit(String physicsUnit) {
		this.physicsUnit = physicsUnit;
	}

	public Integer getUpLimit() {
		return upLimit;
	}

	public void setUpLimit(Integer upLimit) {
		this.upLimit = upLimit;
	}

	public Integer getDownLimit() {
		return downLimit;
	}

	public void setDownLimit(Integer downLimit) {
		this.downLimit = downLimit;
	}

	public Integer getIsFresh() {
		return isFresh;
	}

	public void setIsFresh(Integer isFresh) {
		this.isFresh = isFresh;
	}

	public Integer getWeighed() {
		return weighed;
	}

	public void setWeighed(Integer weighed) {
		this.weighed = weighed;
	}

	public Integer getIsPreprocess() {
		return isPreprocess;
	}

	public void setIsPreprocess(Integer isPreprocess) {
		this.isPreprocess = isPreprocess;
	}

	public String getReferenceCost() {
		return referenceCost;
	}

	public void setReferenceCost(String referenceCost) {
		this.referenceCost = referenceCost;
	}

	public String getAbcClass() {
		return abcClass;
	}

	public void setAbcClass(String abcClass) {
		this.abcClass = abcClass;
	}

	

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getGbCode() {
		return gbCode;
	}

	public void setGbCode(String gbCode) {
		this.gbCode = gbCode;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public Double getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Double expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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

	public Integer getFreshAttr() {
		return freshAttr;
	}

	public void setFreshAttr(Integer freshAttr) {
		this.freshAttr = freshAttr;
	}

	public String getAreaAttr() {
		return areaAttr;
	}

	public void setAreaAttr(String areaAttr) {
		this.areaAttr = areaAttr;
	}
     
     
     
}
