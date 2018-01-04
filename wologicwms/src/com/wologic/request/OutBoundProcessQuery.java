package com.wologic.request;

public class OutBoundProcessQuery {
private String storedCode;
	
    /** 开始时间 */
    private String startTime; 
    /** 结束时间 */
    private String endTime;
	public String getStoredCode() {
		return storedCode;
	}
	public void setStoredCode(String storedCode) {
		this.storedCode = storedCode;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	} 
}
