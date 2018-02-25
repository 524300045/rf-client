package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;



/**
 * WmsInventoryQuery锛歐MS鐩樼偣鍗曟煡璇㈢被
 * 
 * @author jinsicao
 * 
 */
public class WmsInventoryQuery  {
    
    /** 涓婚敭id */
	private Long id; 
    /** 鐩樼偣鍗曞彿 */
	private String wmsInventoryOrderNo; 
    /** 涓�绾у垎绫荤紪鐮� */
	private String categoryCode; 
    /** 涓�绾у垎绫诲悕绉� */
	private String categoryName; 
    /** 浜岀骇鍒嗙被缂栫爜 */
	private String twoCategoryCode; 
    /** 浜岀骇鍒嗙被鍚嶇О */
	private String twoCategoryName; 
    /** 搴撳尯缂栫爜 */
	private String areaCode; 
    /** 搴撳尯鍚嶇О */
	private String areaName; 
    /** 浠撳簱缂栫爜 */
	private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
	private String warehouseName; 
    /** 璐т富缂栫爜 */
	private String customerCode; 
    /** 鐘舵�� */
	private Integer status; 
    /** 鐩樼偣鏃ユ湡 */
	private Date inventoryDate; 
    /** 瀹℃壒鏃堕棿 */
	private Date approveTime; 
    /** 瀹℃壒浜� */
	private String approver; 
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
    /** 寮�濮嬫椂闂� */
    private Date startTime; 
    /** 缁撴潫鏃堕棿 */
    private Date endTime; 
	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getWmsInventoryOrderNo(){
		return wmsInventoryOrderNo;
	}
	    
    public void setWmsInventoryOrderNo(String wmsInventoryOrderNo) {
		this.wmsInventoryOrderNo = wmsInventoryOrderNo;
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
	
    public String getTwoCategoryCode(){
		return twoCategoryCode;
	}
	    
    public void setTwoCategoryCode(String twoCategoryCode) {
		this.twoCategoryCode = twoCategoryCode;
	}
	
    public String getTwoCategoryName(){
		return twoCategoryName;
	}
	    
    public void setTwoCategoryName(String twoCategoryName) {
		this.twoCategoryName = twoCategoryName;
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
	
    public Integer getStatus(){
		return status;
	}
	    
    public void setStatus(Integer status) {
		this.status = status;
	}
	
    public Date getInventoryDate(){
		return inventoryDate;
	}
	    
    public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	
    public Date getApproveTime(){
		return approveTime;
	}
	    
    public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	
    public String getApprover(){
		return approver;
	}
	    
    public void setApprover(String approver) {
		this.approver = approver;
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

    public Date getStartTime() {
        return startTime;
    }
    
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    public Date getEndTime() {
        return endTime;
    }
    
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
