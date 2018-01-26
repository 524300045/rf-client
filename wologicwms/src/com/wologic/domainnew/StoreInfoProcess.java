package com.wologic.domainnew;

import java.util.Date;

/**
 * StoreInfo锛氶棬搴楄〃瀹炰綋绫�
 * 
 * @author jinsicao
 * 
 */
public class StoreInfoProcess implements java.io.Serializable  {

    /** 搴忓垪鍖栨爣璇� */
	private static final long serialVersionUID = 1L;
	   private String process;

    public String getProcess() {
		return process;
	}
	public void setProcess(String process) {
		this.process = process;
	}
	/** 闂ㄥ簵缂栫爜 */
    private String storedCode; 
    /** 闂ㄥ簵鍚嶇О */
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
   
	 /**优先级*/
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
