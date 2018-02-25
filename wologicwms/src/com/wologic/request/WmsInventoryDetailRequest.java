package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WmsInventoryDetailRequest锛歐MS鐩樼偣鍗曠粏琛ㄨ姹傚弬鏁�
 * 
 * @author jinsicao
 * 
 */
public class WmsInventoryDetailRequest  {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** 涓婚敭id */
    private Long id; 
    /** 鐩樼偣鍗曞彿 */
    private String inventoryOrderNo; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    /** 瑙勬牸鍨嬪彿 */
    private String goodsModel; 
    /** 璁′环鍗曚綅锛堟枻銆佷袱锛� */
    private String goodsUnit; 
    /** 鍗曚环 */
    private BigDecimal price; 
    /** 褰撳墠搴撳瓨 */
    private Integer currentStock; 
    /** 鐢熶骇鏃ユ湡 */
    private Date productionDate; 
    /** 鏁堟湡 */
    private Double expiryDate; 
    /** 鏈夋晥鏈熻嚦 */
    private Date periodDate; 
    /** 瀹炵洏鏁伴噺 */
    private BigDecimal inventoryNum; 
    /** 瀹炵洏鐢熶骇鏃ユ湡 */
    private Date inventoryProductionDate; 
    /** 瀹炵洏浜� */
    private String inventoryUser; 
    /** 瀹炵洏鏃堕棿 */
    private Date inventoryTime; 
    /** 鐘舵�� */
    private Integer status; 
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
    
    public String getInventoryOrderNo(){
        return inventoryOrderNo;
    }
        
    public void setInventoryOrderNo(String inventoryOrderNo) {
        this.inventoryOrderNo = inventoryOrderNo;
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
    
    public String getGoodsModel(){
        return goodsModel;
    }
        
    public void setGoodsModel(String goodsModel) {
        this.goodsModel = goodsModel;
    }
    
    public String getGoodsUnit(){
        return goodsUnit;
    }
        
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
    
    public BigDecimal getPrice(){
        return price;
    }
        
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    public Integer getCurrentStock(){
        return currentStock;
    }
        
    public void setCurrentStock(Integer currentStock) {
        this.currentStock = currentStock;
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
    
    public BigDecimal getInventoryNum(){
        return inventoryNum;
    }
        
    public void setInventoryNum(BigDecimal inventoryNum) {
        this.inventoryNum = inventoryNum;
    }
    
    public Date getInventoryProductionDate(){
        return inventoryProductionDate;
    }
        
    public void setInventoryProductionDate(Date inventoryProductionDate) {
        this.inventoryProductionDate = inventoryProductionDate;
    }
    
    public String getInventoryUser(){
        return inventoryUser;
    }
        
    public void setInventoryUser(String inventoryUser) {
        this.inventoryUser = inventoryUser;
    }
    
    public Date getInventoryTime(){
        return inventoryTime;
    }
        
    public void setInventoryTime(Date inventoryTime) {
        this.inventoryTime = inventoryTime;
    }
    
    public Integer getStatus(){
        return status;
    }
        
    public void setStatus(Integer status) {
        this.status = status;
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
