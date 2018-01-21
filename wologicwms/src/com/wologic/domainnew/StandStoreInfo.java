package com.wologic.domainnew;

import java.util.Date;

/**
 * StoreInfo锛氶棬搴楄〃瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class StandStoreInfo implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	
    /** 涓婚敭id */
    private Long id; 
    /** 闂ㄥ簵缂栫爜 */
    private String storedCode; 
    /** 闂ㄥ簵鍚嶇О */
    private String storedName; 
    /** 璐熻矗浜� */
    private String manage; 
    /** 鏀惰揣浜� */
    private String receiver; 
    /** 鏀惰揣浜鸿仈绯荤數璇� */
    private String receiverPhone; 
    /** 浼犵湡 */
    private String fax; 
    /** 閭斂缂栫爜 */
    private String postCode; 
    /** 鎴愮珛鏃ユ湡 */
    private Date foundTime; 
    /** 鎾ら攢鏃ユ湡 */
    private Date revokeTime; 
    /** 涓婄骇閮ㄩ棬缂栧彿 */
    private String parentDepCode; 
    /** 澶囨敞 */
    private String remarks; 
    /** 浠撳簱鍚嶇О */
    private String warehouseName; 
    /** 浠撳簱缂栫爜 */
    private String warehouseCode; 
    /** 璐т富缂栫爜 */
    private String customerCode; 
    /** 鍦板潃 */
    private String address; 
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
    /**浼樺厛绾�*/
    private Integer priority;
    
    
    public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Long getId(){
        return id;
    }
        
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStoredCode(){
        return storedCode;
    }
        
    public void setStoredCode(String storedCode) {
        this.storedCode = storedCode;
    }
    
    public String getStoredName(){
        return storedName;
    }
        
    public void setStoredName(String storedName) {
        this.storedName = storedName;
    }
    
    public String getManage(){
        return manage;
    }
        
    public void setManage(String manage) {
        this.manage = manage;
    }
    
    public String getReceiver(){
        return receiver;
    }
        
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
    
    public String getReceiverPhone(){
        return receiverPhone;
    }
        
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
    
    public String getFax(){
        return fax;
    }
        
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    public String getPostCode(){
        return postCode;
    }
        
    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
    
    public Date getFoundTime(){
        return foundTime;
    }
        
    public void setFoundTime(Date foundTime) {
        this.foundTime = foundTime;
    }
    
    public Date getRevokeTime(){
        return revokeTime;
    }
        
    public void setRevokeTime(Date revokeTime) {
        this.revokeTime = revokeTime;
    }
    
    public String getParentDepCode(){
        return parentDepCode;
    }
        
    public void setParentDepCode(String parentDepCode) {
        this.parentDepCode = parentDepCode;
    }
    
    public String getRemarks(){
        return remarks;
    }
        
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getWarehouseName(){
        return warehouseName;
    }
        
    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }
    
    public String getWarehouseCode(){
        return warehouseCode;
    }
        
    public void setWarehouseCode(String warehouseCode) {
        this.warehouseCode = warehouseCode;
    }
    
    public String getCustomerCode(){
        return customerCode;
    }
        
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
    
    public String getAddress(){
        return address;
    }
        
    public void setAddress(String address) {
        this.address = address;
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
