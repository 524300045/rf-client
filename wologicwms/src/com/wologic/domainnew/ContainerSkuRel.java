package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ContainerSkuRel锛氭墭鐩樺晢鍝佹暟閲忚〃瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class ContainerSkuRel implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** 涓婚敭 */
    private Long id; 
    /** 浠撳簱缂栫爜 */
    private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
    private String warehouseName; 
    /** 瀹㈡埛缂栫爜 */
    private String customerCode; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 瀹瑰櫒缂栫爜 */
    private String containerCode; 
    /** 鎷ｈ揣鏁伴噺 */
    private BigDecimal pickingNum; 
    /** 宸插垎鎷ｆ暟閲� */
    private BigDecimal sortingNum; 
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
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
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
    
    public String getContainerCode(){
        return containerCode;
    }
        
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }
    
    public BigDecimal getPickingNum(){
        return pickingNum;
    }
        
    public void setPickingNum(BigDecimal pickingNum) {
        this.pickingNum = pickingNum;
    }
    
    public BigDecimal getSortingNum(){
        return sortingNum;
    }
        
    public void setSortingNum(BigDecimal sortingNum) {
        this.sortingNum = sortingNum;
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
