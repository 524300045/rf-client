package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GoodsBarcodeRequest锛氬晢鍝佹潯鐮佷俊鎭姹傚弬鏁�
 * 
 * @author jinsicao
 * 
 */
public class GoodsBarcodeRequest {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** 涓婚敭id */
    private Long id; 
    /** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鏉＄爜 */
    private String barCode; 
    /** 鐘舵�侊紙1锛氭湁鏁堬紱0锛氭棤鏁堬級 */
    private Integer status; 
    /** 鍒涘缓鍚嶇О */
    private String createUser; 
    /** 鍒涘缓鏃堕棿 */
    private Date createTime; 
    /** 淇敼鍚嶇О */
    private String updateUser; 
    /** 淇敼鏃堕棿 */
    private Date updateTime; 
    /** 鏈夋晥鏍囩ず锛�1锛氭湁鏁堬紱0锛氭棤鏁堬級 */
    private Integer yn; 
    
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
    
    public String getBarCode(){
        return barCode;
    }
        
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }
    
    public Integer getStatus(){
        return status;
    }
        
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public String getCreateUser(){
        return createUser;
    }
        
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    
    public Date getCreateTime(){
        return createTime;
    }
        
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateUser(){
        return updateUser;
    }
        
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
    
    public Date getUpdateTime(){
        return updateTime;
    }
        
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    public Integer getYn(){
        return yn;
    }
        
    public void setYn(Integer yn) {
        this.yn = yn;
    }
}
