package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Goods锛氬晢鍝佽〃瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class Goods implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** 涓婚敭id */
    private Long id; 
    private String sap;
    private String sapName;
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 瀹㈡埛缂栫爜 */
    private String customerCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 涓�绾у垎绫荤紪鐮� */
    private String categoryCode; 
    /** 涓�绾у垎绫诲悕绉� */
    private String categoryName;
    /** 浜岀骇鍒嗙被缂栫爜 */
    private String twoCategoryCode; 
    /** 浜岀骇鍒嗙被鍚嶇О */
    private String twoCategoryName;
    /** 瑙勬牸鍨嬪彿 */
    private String goodsModel; 
    /** 绛夌骇 */
    private String goodsGrade; 
    /** 鍝佺墝 */
    private String goodsBrand; 
    /** 浜у湴 */
    private String madeIn; 
    /** 閲嶉噺 */
    private BigDecimal weight; 
    /** 闀� */
    private BigDecimal packLong; 
    /** 瀹� */
    private BigDecimal packWide; 
    /** 楂� */
    private BigDecimal packHigh; 
    /** 寤鸿閲囪喘鍗曚环 */
    private BigDecimal suggestPrice; 
    /** 鐗规畩瑕佹眰 */
    private Integer specialAsk; 
    /** 鍖呰锛堟暟閲忥級 */
    private BigDecimal modelNum; 
    /** 璁′环鍗曚綅锛堟枻銆佷袱锛� */
    private String goodsUnit; 
    /** 鐗╃悊鍗曚綅锛堝寘銆佺銆佺摱锛� */
    private String physicsUnit; 
    /** 鍖呰鍐椾綑涓婄嚎 */
    private Integer upLimit; 
    /** 鍖呰鍐椾綑涓嬬嚎 */
    private Integer downLimit; 
    /** 鏄惁鐢熼矞 1锛氭槸 0锛氬惁 */
    private Integer isFresh; 
    /** 鏄惁绉伴噸 1锛氭槸 0锛氬惁 */
    private Integer weighed; 
    /** 鏄惁棰勫姞宸� 1锛氭槸 0锛氬惁 */
    private Integer isPreprocess; 
    /** 鍙傝�冩垚鏈� */
    private String referenceCost; 
    /** abc鍒嗙被 */
    private String abcClass; 
    /** 鍚敤鏃ユ湡 */
    private Date enableDate; 
    /** 鍚敤鍋滅敤鏍囪瘑:1锛氭槸 0锛氬惁 */
    private Integer enabled; 
    /** 鍥芥爣鐮� */
    private String gbCode; 
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
    /** 涓夌骇鍒嗙被缂栫爜 */
    private String threeCategoryCode; 
    /** 涓夌骇鍒嗙被鍚嶇О */
    private String threeCategoryName; 
    /** 绋庣巼 */
    private BigDecimal taxRate; 
    /** 鏁堟湡 */
    private Double expiryDate; 
    
    /**
     * 绠�绉�
     */
    private String shortName;
    
    /**
     * 鍒悕
     */
    private String aliasName; 
    
    /**
     * 鍔犲伐灞炴��
     */
    private String preprocessAttr;
    
    /**
     * 鐢熶骇灞炴��
     */
    private String productionAttr;
    
    private String barCodeStr;
    
    
    public String getBarCodeStr() {
		return barCodeStr;
	}

	public void setBarCodeStr(String barCodeStr) {
		this.barCodeStr = barCodeStr;
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

	public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getSap() {
		return sap;
	}

	public void setSap(String sap) {
		this.sap = sap;
	}

	public String getSapName() {
		return sapName;
	}

	public void setSapName(String sapName) {
		this.sapName = sapName;
	}

	public String getSkuCode(){
        return skuCode;
    }
        
    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getGoodsName(){
        return goodsName;
    }
        
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getCategoryCode(){
        return categoryCode;
    }
        
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
    
    public String getCategoryName(){
        return categoryName;
    }
        
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getGoodsModel(){
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

	public String getMadeIn(){
        return madeIn;
    }
        
    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }
    
    public BigDecimal getWeight(){
        return weight;
    }
        
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }
    
    public BigDecimal getPackLong(){
        return packLong;
    }
        
    public void setPackLong(BigDecimal packLong) {
        this.packLong = packLong;
    }
    
    public BigDecimal getPackWide(){
        return packWide;
    }
        
    public void setPackWide(BigDecimal packWide) {
        this.packWide = packWide;
    }
    
    public BigDecimal getPackHigh(){
        return packHigh;
    }
        
    public void setPackHigh(BigDecimal packHigh) {
        this.packHigh = packHigh;
    }
    
    public BigDecimal getSuggestPrice(){
        return suggestPrice;
    }
        
    public void setSuggestPrice(BigDecimal suggestPrice) {
        this.suggestPrice = suggestPrice;
    }
    
    public Integer getSpecialAsk(){
        return specialAsk;
    }
        
    public void setSpecialAsk(Integer specialAsk) {
        this.specialAsk = specialAsk;
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
    
    public Integer getUpLimit(){
        return upLimit;
    }
        
    public void setUpLimit(Integer upLimit) {
        this.upLimit = upLimit;
    }
    
    public Integer getDownLimit(){
        return downLimit;
    }
        
    public void setDownLimit(Integer downLimit) {
        this.downLimit = downLimit;
    }
    
    public Integer getIsFresh(){
        return isFresh;
    }
        
    public void setIsFresh(Integer isFresh) {
        this.isFresh = isFresh;
    }
    
    public Integer getWeighed(){
        return weighed;
    }
        
    public void setWeighed(Integer weighed) {
        this.weighed = weighed;
    }
    
    public Integer getIsPreprocess(){
        return isPreprocess;
    }
        
    public void setIsPreprocess(Integer isPreprocess) {
        this.isPreprocess = isPreprocess;
    }
    
    public String getReferenceCost(){
        return referenceCost;
    }
        
    public void setReferenceCost(String referenceCost) {
        this.referenceCost = referenceCost;
    }
    
    public String getAbcClass(){
        return abcClass;
    }
        
    public void setAbcClass(String abcClass) {
        this.abcClass = abcClass;
    }
    
    public Date getEnableDate(){
        return enableDate;
    }
        
    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }
    
    public Integer getEnabled(){
        return enabled;
    }
        
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
    
    public String getGbCode(){
        return gbCode;
    }
        
    public void setGbCode(String gbCode) {
        this.gbCode = gbCode;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getPreprocessAttr() {
		return preprocessAttr;
	}

	public void setPreprocessAttr(String preprocessAttr) {
		this.preprocessAttr = preprocessAttr;
	}

	public String getProductionAttr() {
		return productionAttr;
	}

	public void setProductionAttr(String productionAttr) {
		this.productionAttr = productionAttr;
	}
}
