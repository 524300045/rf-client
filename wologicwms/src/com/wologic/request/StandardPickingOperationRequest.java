package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StandardPickingOperationRequest锛氭爣鍝佹嫞璐ф搷浣滆〃璇锋眰鍙傛暟
 * 
 * @author jinsicao
 * 
 */
public class StandardPickingOperationRequest  {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** 涓婚敭 */
    private Long id; 
    /** 鎷ｈ揣浠诲姟鍗曞彿 */
    private String pickingTaskCode; 
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
    /** 鐢熶骇鏃ユ湡 */
    private Date productionDate; 
    /** 瀹瑰櫒缂栫爜 */
    private String containerCode; 
    /** 瀹為檯鏁伴噺 */
    private BigDecimal realityNum; 
    /** 瀹為檯鎷ｈ揣鏃堕棿 */
    private Date realityTime; 
    /** 瀹為檯鎷ｈ揣浜� */
    private String realityUser; 
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
    
    public Date getProductionDate(){
        return productionDate;
    }
        
    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
    
    public String getContainerCode(){
        return containerCode;
    }
        
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
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
