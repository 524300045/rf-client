package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseArea锛氬簱鍖轰俊鎭疄浣撶被
 * 
 * @author jinsicao
 * 
 */
public class WarehouseAreaPickProcess implements java.io.Serializable  {

 
	
	private String areaCode; 
    /** 搴撳尯鍚嶇�? */
    private String areaName;
    
    private Integer totalNum;  //������
    
    private Integer  pickNum;  //�������
    
    
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getPickNum() {
		return pickNum;
	}
	public void setPickNum(Integer pickNum) {
		this.pickNum = pickNum;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	} 
    
}
