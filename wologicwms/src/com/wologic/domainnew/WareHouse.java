package com.wologic.domainnew;

public class WareHouse {

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return warehouseName;
	}

	private String warehouseCode ;
	
	private String warehouseName ;

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
