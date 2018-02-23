package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;


/**
 * WarehouseAreaQuery：库区信息查询类
 * 
 * @author jinsicao
 * 
 */
public class WarehouseAreaRequest  {
    
  
	private String areaCode; 
    /** 库区名称 */
	private String areaName; 
    /** 仓库编码 */
	private String warehouseCode; 
    /** 仓库名称 */
	private String warehouseName;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	} 
   
}
