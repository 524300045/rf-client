package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;



/**
 * ContainerQuery锛氬鍣ㄤ俊鎭煡璇㈢被
 * 
 * @author jinsicao
 * 
 */
public class ContainerQuery  {
    
    /** 涓婚敭id */
	private Long id; 
    /** 瀹瑰櫒缂栫爜 */
	private String containerCode; 
    /** 瀹瑰櫒鍚嶇О */
	private String containerName; 
    /** 浠撳簱缂栫爜 */
	private String warehouseCode; 
    /** 浠撳簱鍚嶇О */
	private String warehouseName; 
    /** 搴撳尯缂栫爜 */
	private String areaCode; 
    /** 搴撳尯鍚嶇О */
	private String areaName; 
    /** 鐘舵�� */
	private Integer status; 
    /** 瀹瑰櫒鏉＄爜 */
	private String barCode; 
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
    
    /** 客户编码 */
    private String customerCode; 
	
    public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	
    public Long getId(){
		return id;
	}
	    
    public void setId(Long id) {
		this.id = id;
	}
	
    public String getContainerCode(){
		return containerCode;
	}
	    
    public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	
    public String getContainerName(){
		return containerName;
	}
	    
    public void setContainerName(String containerName) {
		this.containerName = containerName;
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
	
    public Integer getStatus(){
		return status;
	}
	    
    public void setStatus(Integer status) {
		this.status = status;
	}
	
    public String getBarCode(){
		return barCode;
	}
	    
    public void setBarCode(String barCode) {
		this.barCode = barCode;
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
