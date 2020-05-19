package com.wologic.domainnew;

public class WaveCustomerStore {

	 /** 主键id */
    private Long id; 
    
    /** 仓库名称 */
    private String warehouseName; 
    /** 仓库编码 */
    private String warehouseCode; 
    
    /** 波次编码 */
    private String waveCode; 
     
    /** 波次名称 */
    private String waveName; 
    
    
    /** 货主编码 */
    private String customerCode;
    /** 货主 */
    private String customerName;
    /** 门店编码 */
    private String storedCode; 
    /** 门店名称 */
    private String storedName;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWarehouseName() {
		return warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getWaveCode() {
		return waveCode;
	}
	public void setWaveCode(String waveCode) {
		this.waveCode = waveCode;
	}
	public String getWaveName() {
		return waveName;
	}
	public void setWaveName(String waveName) {
		this.waveName = waveName;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
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
