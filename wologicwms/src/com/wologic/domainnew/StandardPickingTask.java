package  com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StandardPickingTask锛氭爣鍝佹嫞璐т换鍔″崟瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class StandardPickingTask implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** id */
    private Long id; 
    /** 鎷ｈ揣浠诲姟鍗曞彿 */
    private String pickingTaskCode; 
    /** 鐘舵�� */
    private Integer status; 
    /** 浠撳簱缂栫爜 */
    private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
    private String warehouseName; 
    /** 鍩庡競缂栫爜 */
    private String regionCode; 
    /** 瀹㈡埛缂栫爜 */
    private String customerCode; 
    /** 搴撳尯缂栫爜 */
    private String areaCode; 
    /** 搴撳尯鍚嶇О */
    private String areaName; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 瀹㈡埛鍟嗗搧缂栫爜 */
    private String customerSkuCode; 
    /** 瀹㈡埛鍟嗗搧鍚嶇О */
    private String customerGoodsName; 
    /** sap缂栫爜 */
    private String sap; 
    /** sap鍚嶇О */
    private String sapName; 
    /** 涓�绾у垎绫荤紪鐮� */
    private String categoryCode; 
    /** 涓�绾у垎绫诲悕绉� */
    private String categoryName; 
    /** 鍖呰锛堟暟閲忥級 */
    private BigDecimal modelNum; 
    /** 璁′环鍗曚綅锛堟枻銆佷袱锛� */
    private String goodsUnit; 
    /** 鐗╃悊鍗曚綅锛堝寘銆佺銆佺摱锛� */
    private String physicsUnit; 
    /** 璁″垝鏁伴噺 */
    private BigDecimal planNum; 
    /** 瀹為檯鏁伴噺 */
    private BigDecimal realityNum; 
    /** 瀹為檯鎷ｈ揣鏃堕棿 */
    private Date realityTime; 
    /** 瀹為檯鎷ｈ揣浜� */
    private String realityUser; 
    /** 鍙戣繍閲� */
    private BigDecimal deliveryNum; 
    /** 鍙戣繍鏃堕棿 */
    private Date deliveryTime; 
    /** 鍙戣繍浜� */
    private String deliveryUser; 
    /** 璁″垝涓婇檺鏁伴噺 */
    private BigDecimal upPlanNum; 
    /** 璁″垝涓嬮檺鏁伴噺 */
    private BigDecimal downPlanNum; 
    /** 鐢熶骇鏃ユ湡 */
    private Date productionDate; 
    /** 鏁堟湡 */
    private Double expiryDate; 
    /** 鏈夋晥鏈熻嚦 */
    private Date periodDate; 
    /** 鍒涘缓鏃堕棿 */
    private Date operateTime; 
    /** 閰嶉�佹棩鏈� */
    private Date deliveryDate; 
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
