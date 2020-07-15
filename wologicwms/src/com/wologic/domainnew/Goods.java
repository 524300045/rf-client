package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Goods閿涙艾鏅㈤崫浣姐�冪�圭偘缍嬬猾锟�
 * 
 * @author jinsicao
 * 
 */
public class Goods implements java.io.Serializable  {

    /** 鎼村繐鍨崠鏍ㄧ垼鐠囷拷 */
	private static final long serialVersionUID = 1L;
	
    /** 娑撳鏁璱d */
    private Long id; 
    private String sap;
    private String sapName;
    /** 閸熷棗鎼х紓鏍垳 */
    private String skuCode; 
    /** 鐎广垺鍩涚紓鏍垳 */
    private String customerCode; 
    /** 閸熷棗鎼ч崥宥囆� */
    private String goodsName; 
    /** 娑擄拷缁狙冨瀻缁崵绱惍锟� */
    private String categoryCode; 
    /** 娑擄拷缁狙冨瀻缁鎮曠粔锟� */
    private String categoryName;
    /** 娴滃瞼楠囬崚鍡欒缂傛牜鐖� */
    private String twoCategoryCode; 
    /** 娴滃瞼楠囬崚鍡欒閸氬秶袨 */
    private String twoCategoryName;
    /** 鐟欏嫭鐗搁崹瀣娇 */
    private String goodsModel; 
    /** 缁涘楠� */
    private String goodsGrade; 
    /** 閸濅胶澧� */
    private String goodsBrand; 
    /** 娴溠冩勾 */
    private String madeIn; 
    /** 闁插秹鍣� */
    private BigDecimal weight; 
    /** 闂�锟� */
    private BigDecimal packLong; 
    /** 鐎癸拷 */
    private BigDecimal packWide; 
    /** 妤傦拷 */
    private BigDecimal packHigh; 
    /** 瀵ら缚顔呴柌鍥枠閸楁洑鐜� */
    private BigDecimal suggestPrice; 
    /** 閻楄鐣╃憰浣圭湴 */
    private Integer specialAsk; 
    /** 閸栧懓顫夐敍鍫熸殶闁插骏绱� */
    private BigDecimal modelNum; 
    /** 鐠佲�茬幆閸楁洑缍呴敍鍫熸灮閵嗕椒琚遍敍锟� */
    private String goodsUnit; 
    /** 閻椻晝鎮婇崡鏇氱秴閿涘牆瀵橀妴浣侯唸閵嗕胶鎽遍敍锟� */
    private String physicsUnit; 
    /** 閸栧懓顥婇崘妞剧稇娑撳﹦鍤� */
    private Integer upLimit; 
    /** 閸栧懓顥婇崘妞剧稇娑撳鍤� */
    private Integer downLimit; 
    /** 閺勵垰鎯侀悽鐔肩煘 1閿涙碍妲� 0閿涙艾鎯� */
    private Integer isFresh; 
    /** 閺勵垰鎯佺粔浼村櫢 1閿涙碍妲� 0閿涙艾鎯� */
    private Integer weighed; 
    /** 閺勵垰鎯佹０鍕瀹革拷 1閿涙碍妲� 0閿涙艾鎯� */
    private Integer isPreprocess; 
    /** 閸欏倽锟藉啯鍨氶張锟� */
    private String referenceCost; 
    /** abc閸掑棛琚� */
    private String abcClass; 
    /** 閸氼垳鏁ら弮銉︽埂 */
    private Date enableDate; 
    /** 閸氼垳鏁ら崑婊呮暏閺嶅洩鐦�:1閿涙碍妲� 0閿涙艾鎯� */
    private Integer enabled; 
    /** 閸ヨ姤鐖ｉ惍锟� */
    private String gbCode; 
    /** 閸掓稑缂撻弮鍫曟？ */
    private Date createTime; 
    /** 閸掓稑缂撴禍锟� */
    private String createUser; 
    /** 閺囧瓨鏌婇弮鍫曟？ */
    private Date updateTime; 
    /** 閺囧瓨鏌婃禍锟� */
    private String updateUser; 
    /** 閺勵垰鎯侀張澶嬫櫏 */
    private Integer yn; 
    /** 娑撳楠囬崚鍡欒缂傛牜鐖� */
    private String threeCategoryCode; 
    /** 娑撳楠囬崚鍡欒閸氬秶袨 */
    private String threeCategoryName; 
    /** 缁嬪海宸� */
    private BigDecimal taxRate; 
    /** 閺佸牊婀� */
    private Double expiryDate; 
    
    /**
     * 缁狅拷缁夛拷
     */
    private String shortName;
    
    /**
     * 閸掝偄鎮�
     */
    private String aliasName; 
    
    /**
     * 閸旂姴浼愮仦鐐达拷锟�
     */
    private String preprocessAttr;
    
    /** 搴撳尯缂栫爜 */
    private String areaCode;
    
   private String areaName;
   
   
    
    public String getAreaName() {
	return areaName;
}

public void setAreaName(String areaName) {
	this.areaName = areaName;
}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	/**
     * 閻㈢喍楠囩仦鐐达拷锟�
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
