package com.wologic.request;

public class StockExpiryInfoRequest {

	 /** 客户编码 */
    private String customerCode; 
    /** 仓库编码 */
    private String warehouseCode; 
    /** 仓库名称 */
    private String warehouseName; 
    /** 库区编码 */
    private String areaCode; 
    /** 库区名称 */
    private String areaName; 
    /** 商品编码 */
    private String skuCode; 
    /** 商品名称 */
    private String goodsName;
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
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
	public String getSkuCode() {
		return skuCode;
	}
	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	} 
    
    
}
