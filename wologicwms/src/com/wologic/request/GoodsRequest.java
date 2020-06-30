package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GoodsRequest閿涙艾鏅㈤崫浣姐�冪拠閿嬬湴閸欏倹鏆�
 * 
 * @author jinsicao
 * 
 */
public class GoodsRequest  {

    /** 鎼村繐鍨崠鏍ㄧ垼鐠囷拷 */
    private static final long serialVersionUID = 1L;
    
    /** 娑撳鏁璱d */
    private Long id; 
    /** 閸熷棗鎼х紓鏍垳 */
    private String skuCode; 
    /** 鐎广垺鍩涚紓鏍垳 */
    private String customerCode; 
    /** 閸熷棗鎼ч崥宥囆� */
    private String goodsName; 
    /** 閺堬拷鐏忓繐鍨庣猾鑽ょ椽閻拷 */
    private String categoryCode; 
    /** 閺堬拷鐏忓繐鍨庣猾璇叉倳缁夛拷 */
    private String categoryName; 
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
    
    /**
     * 是否是套装商品
     */
    private Integer goodsType;
    
    
    
    public Integer getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(Integer goodsType) {
		this.goodsType = goodsType;
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
}
