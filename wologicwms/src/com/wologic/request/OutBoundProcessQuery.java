package com.wologic.request;

public class OutBoundProcessQuery {
private String storedCode;
	
    /** ��ʼʱ�� */
    private String startTime; 
    /** ����ʱ�� */
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
