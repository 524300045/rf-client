package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WarehouseArea閿涙艾绨遍崠杞颁繆閹垰鐤勬担鎾惰
 * 
 * @author jinsicao
 * 
 */
public class WarehouseAreaPickProcess implements java.io.Serializable  {

 
	
	private String areaCode; 
    /** 鎼存挸灏崥宥囆? */
    private String areaName;
    
    private Integer totalNum;  //总数量
    
    private Integer  pickNum;  //拣货数量
    
    
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
