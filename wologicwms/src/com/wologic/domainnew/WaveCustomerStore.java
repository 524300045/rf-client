package com.wologic.domainnew;

public class WaveCustomerStore {

	 /** ����id */
    private Long id; 
    
    /** �ֿ����� */
    private String warehouseName; 
    /** �ֿ���� */
    private String warehouseCode; 
    
    /** ���α��� */
    private String waveCode; 
     
    /** �������� */
    private String waveName; 
    
    
    /** �������� */
    private String customerCode;
    /** ���� */
    private String customerName;
    /** �ŵ���� */
    private String storedCode; 
    /** �ŵ����� */
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
