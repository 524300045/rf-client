package com.wologic.domainnew;

import java.io.Serializable;
import java.math.BigDecimal;

public class GoodsBarCode implements Serializable {

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

		/** ���л���ʶ */
	    private static final long serialVersionUID = 1L;
	    
	    /** ����id */
	    private Long id; 
	    /** ��Ʒ���� */
	    private String skuCode; 
	    /** �ͻ����� */
	    private String customerCode; 
	    /** ��Ʒ���� */
	    private String goodsName; 
	    /** ��С������� */
	    private String categoryCode; 
	    /** ��С�������� */
	    private String categoryName; 
	    /** ����ͺ� */
	    private String goodsModel; 
	    /** �ȼ� */
	    private String goodsGrade; 
	    /** Ʒ�� */
	    private String goodsBrand; 
	    /** ���� */
	    private String madeIn; 
	    /** ���� */
	    private BigDecimal weight; 
	    /** �� */
	    private BigDecimal packLong; 
	    /** �� */
	    private BigDecimal packWide; 
	    /** �� */
	    private BigDecimal packHigh; 
	    /** ����ɹ����� */
	    private BigDecimal suggestPrice; 
	    /** ����Ҫ�� */
	    private Integer specialAsk; 
	    /** ���棨������ */
	    private BigDecimal modelNum; 
	    /** �Ƽ۵�λ������� */
	    private String goodsUnit; 
	    /** ����λ�������䡢ƿ�� */
	    private String physicsUnit; 
	    /** ��װ�������� */
	    private Integer upLimit; 
	    /** ��װ�������� */
	    private Integer downLimit; 
	    /** �Ƿ����� 1���� 0���� */
	    private Integer isFresh; 
	    /** �Ƿ���� 1���� 0���� */
	    private Integer weighed; 
	    /** �Ƿ�Ԥ�ӹ� 1���� 0���� */
	    private Integer isPreprocess; 
	    /** �ο��ɱ� */
	    private String referenceCost; 
	    /** abc���� */
	    private String abcClass; 
	    /** �������� */
	    private String enableDate; 
	    /** ����ͣ�ñ�ʶ:1���� 0���� */
	    private Integer enabled; 
	    /** ������ */
	    private String gbCode; 
	    /** ����ʱ�� */
	    private String createTime; 
	    /** ������ */
	    private String createUser; 
	    /** ����ʱ�� */
	    private String updateTime; 
	    /** ������ */
	    private String updateUser; 
	    /** �Ƿ���Ч */
	    private Integer yn; 
	    
	    private String barCodeStr;
	    
	    /** ����������� */
	    private String twoCategoryCode; 
	    /** ������������ */
	    private String twoCategoryName;
	    
	    /** ����������� */
	    private String threeCategoryCode; 
	    /** ������������ */
	    private String threeCategoryName; 
	    
	    public String getBarCodeStr() {
			return barCodeStr;
		}

		public void setBarCodeStr(String barCodeStr) {
			this.barCodeStr = barCodeStr;
		}

		public Long getId(){
	        return id;
	    }
	        
	    public void setId(Long id) {
	        this.id = id;
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
	    
	    public String getEnableDate(){
	        return enableDate;
	    }
	        
	    public void setEnableDate(String enableDate) {
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
