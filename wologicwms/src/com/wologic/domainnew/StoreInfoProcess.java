package com.wologic.domainnew;

import java.util.Date;

/**
 * StoreInfo閿涙岸妫惔妤勩�冪�圭偘缍嬬猾锟�
 * 
 * @author jinsicao
 * 
 */
public class StoreInfoProcess implements java.io.Serializable  {

    /** 鎼村繐鍨崠鏍ㄧ垼鐠囷拷 */
	private static final long serialVersionUID = 1L;
	   private String process;

    public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	/** 闂傘劌绨电紓鏍垳 */
    private String storedCode; 
    /** 闂傘劌绨甸崥宥囆� */
    private String storedName;
	public String getStoredCode() {
		return storedCode;
	}
	public void setStoredCode(String storedCode) {
		this.storedCode = storedCode;
	}
	public String getStoredName() {
		return storedName;
	}
	public void setStoredName(String storedName) {
		this.storedName = storedName;
	} 
	
    private String waveName;
    
    
   
	 public String getWaveName() {
		return waveName;
	}
	public void setWaveName(String waveName) {
		this.waveName = waveName;
	}
	/**浼樺厛绾�*/
    private Integer priority;

	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	  private Integer finishNum;
	    
	    private Integer totalNum;

		public Integer getFinishNum() {
			return finishNum;
		}
		public void setFinishNum(Integer finishNum) {
			this.finishNum = finishNum;
		}
		public Integer getTotalNum() {
			return totalNum;
		}
		public void setTotalNum(Integer totalNum) {
			this.totalNum = totalNum;
		}
}
