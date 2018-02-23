package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseArea锛氬簱鍖轰俊鎭疄浣撶被
 * 
 * @author jinsicao
 * 
 */
public class WarehouseArea implements java.io.Serializable  {

 
    @Override
	public String toString() {
		// TODO Auto-generated method stub
		return areaName;
	}
	private String areaCode; 
    /** 搴撳尯鍚嶇О */
    private String areaName;
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
