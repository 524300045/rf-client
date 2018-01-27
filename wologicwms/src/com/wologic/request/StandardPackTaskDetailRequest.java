package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;



/**
 * StandardPackTaskDetailQuery：标品包装任务单明细查询�?
 * 
 * @author jinsicao
 * 
 */
public class StandardPackTaskDetailRequest {
    
   
    public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getStoredCode() {
		return storedCode;
	}

	public void setStoredCode(String storedCode) {
		this.storedCode = storedCode;
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

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	private String customerCode; 
	 
	 private String partnerCode; 
	 
  
	private String storedCode; 
 

    private Date startTime; 
    /** 结束时间 */
    private Date endTime; 
    
    private String warehouseCode; 
	

   
	
   
	    
    
    
    
}
