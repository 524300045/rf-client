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
   

}
