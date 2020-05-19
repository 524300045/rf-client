package com.wologic.request;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * StandardPickingTaskRequest閿涙碍鐖ｉ崫浣瑰珵鐠愌傛崲閸斺�冲礋鐠囬攱鐪伴崣鍌涙殶
 * 
 * @author jinsicao
 * 
 */
public class StandardPickingTaskRequest  {

    /** 鎼村繐鍨崠鏍ㄧ垼鐠囷拷 */
    private static final long serialVersionUID = 1L;
    
    /** id */
    private Long id; 
    /** 閹凤綀鎻ｆ禒璇插閸楁洖褰� */
    private String pickingTaskCode; 
    /** 閻樿埖锟斤拷 */
    private Integer status; 
    /** 娴犳挸绨辩紓鏍垳 */
    private String warehouseCode; 
    /** 娴犳挸绨遍崥宥囆� */
    private String warehouseName; 
    /** 閸╁骸绔剁紓鏍垳 */
    private String regionCode; 
    /** 鐎广垺鍩涚紓鏍垳 */
    private String customerCode; 
    /** 鎼存挸灏紓鏍垳 */
    private String areaCode; 
    /** 鎼存挸灏崥宥囆� */
    private String areaName; 
    /** 閸熷棗鎼х紓鏍垳 */
    private String skuCode; 
    /** 閸熷棗鎼ч崥宥囆� */
    private String goodsName; 
    /** 鐎广垺鍩涢崯鍡楁惂缂傛牜鐖� */
    private String customerSkuCode; 
    /** 鐎广垺鍩涢崯鍡楁惂閸氬秶袨 */
    private String customerGoodsName; 
    /** sap缂傛牜鐖� */
    private String sap; 
    /** sap閸氬秶袨 */
    private String sapName; 
    /** 娑擄拷缁狙冨瀻缁崵绱惍锟� */
    private String categoryCode; 
    /** 娑擄拷缁狙冨瀻缁鎮曠粔锟� */
    private String categoryName; 
    /** 閸栧懓顫夐敍鍫熸殶闁插骏绱� */
    private BigDecimal modelNum; 
    /** 鐠佲�茬幆閸楁洑缍呴敍鍫熸灮閵嗕椒琚遍敍锟� */
    private String goodsUnit; 
    /** 閻椻晝鎮婇崡鏇氱秴閿涘牆瀵橀妴浣侯唸閵嗕胶鎽遍敍锟� */
    private String physicsUnit; 
    /** 鐠佲�冲灊閺佷即鍣� */
    private BigDecimal planNum; 
    /** 鐎圭偤妾弫浼村櫤 */
    private BigDecimal realityNum; 
    /** 鐎圭偤妾幏锝堟彛閺冨爼妫� */
    private Date realityTime; 
    /** 鐎圭偤妾幏锝堟彛娴滐拷 */
    private String realityUser; 
    /** 閸欐垼绻嶉柌锟� */
    private BigDecimal deliveryNum; 
    /** 閸欐垼绻嶉弮鍫曟？ */
    private Date deliveryTime; 
    /** 閸欐垼绻嶆禍锟� */
    private String deliveryUser; 
    /** 鐠佲�冲灊娑撳﹪妾洪弫浼村櫤 */
    private BigDecimal upPlanNum; 
    /** 鐠佲�冲灊娑撳妾洪弫浼村櫤 */
    private BigDecimal downPlanNum; 
    /** 閻㈢喍楠囬弮銉︽埂 */
    private Date productionDate; 
    /** 閺佸牊婀� */
    private Double expiryDate; 
    /** 閺堝鏅ラ張鐔诲殾 */
    private Date periodDate; 
    /** 閸掓稑缂撻弮鍫曟？ */
    private Date operateTime; 
    /** 闁板秹锟戒焦妫╅張锟� */
    private Date deliveryDate; 
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
    
   
    
    private List<String> waveCodeList;
    
    
    
  

	
	public List<String> getWaveCodeList() {
		return waveCodeList;
	}

	public void setWaveCodeList(List<String> waveCodeList) {
		this.waveCodeList = waveCodeList;
	}

	public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPickingTaskCode(){
        return pickingTaskCode;
    }
        
    public void setPickingTaskCode(String pickingTaskCode) {
        this.pickingTaskCode = pickingTaskCode;
    }
    
    public Integer getStatus(){
        return status;
    }
        
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getWarehouseCode(){
        return warehouseCode;
    }
        
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    
    public String getWarehouseName(){
        return warehouseName;
    }
        
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    
    public String getRegionCode(){
        return regionCode;
    }
        
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getAreaCode(){
        return areaCode;
    }
        
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
    
    public String getAreaName(){
        return areaName;
    }
        
    public void setAreaName(String areaName) {
        this.areaName = areaName;
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
    
    public String getCustomerSkuCode(){
        return customerSkuCode;
    }
        
    public void setCustomerSkuCode(String customerSkuCode) {
        this.customerSkuCode = customerSkuCode;
    }
    
    public String getCustomerGoodsName(){
        return customerGoodsName;
    }
        
    public void setCustomerGoodsName(String customerGoodsName) {
        this.customerGoodsName = customerGoodsName;
    }
    
    public String getSap(){
        return sap;
    }
        
    public void setSap(String sap) {
        this.sap = sap;
    }
    
    public String getSapName(){
        return sapName;
    }
        
    public void setSapName(String sapName) {
        this.sapName = sapName;
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
    
    public BigDecimal getRealityNum(){
        return realityNum;
    }
        
    public void setRealityNum(BigDecimal realityNum) {
        this.realityNum = realityNum;
    }
    
    public Date getRealityTime(){
        return realityTime;
    }
        
    public void setRealityTime(Date realityTime) {
        this.realityTime = realityTime;
    }
    
    public String getRealityUser(){
        return realityUser;
    }
        
    public void setRealityUser(String realityUser) {
        this.realityUser = realityUser;
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
    
    public String getDeliveryUser(){
        return deliveryUser;
    }
        
    public void setDeliveryUser(String deliveryUser) {
        this.deliveryUser = deliveryUser;
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
    
    public Date getProductionDate(){
        return productionDate;
    }
        
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    
    public Double getExpiryDate(){
        return expiryDate;
    }
        
    public void setExpiryDate(Double expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public Date getPeriodDate(){
        return periodDate;
    }
        
    public void setPeriodDate(Date periodDate) {
        this.periodDate = periodDate;
    }
    
    public Date getOperateTime(){
        return operateTime;
    }
        
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    
    public Date getDeliveryDate(){
        return deliveryDate;
    }
        
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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
