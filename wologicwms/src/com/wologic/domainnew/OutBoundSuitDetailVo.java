package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

public class OutBoundSuitDetailVo {

    /** ���л���ʶ */
	private static final long serialVersionUID = 1L;
	
    //���ⵥ��ϸ������
    /** �������񵥺� */
    private String outboundTaskCode;
    /** ��Ʒ���� */
    private String skuCode; 
    /** ��Ʒ���� */
    private String goodsName; 
    /** �ͻ���Ʒ���� */
    private String customerSkuCode; 
    /** �ͻ���Ʒ���� */
    private String customerGoodsName; 
    /** ��Ӧ�̱��� */
    private String partnerCode; 
    /** ��Ӧ������ */
    private String partnerName; 
    /** �ƻ����� */
    private BigDecimal planNum; 
    /** �ּ����� */
    private BigDecimal sortingNum; 
    /** ��������*/
    private BigDecimal deliveryNum; 
    /** ��˰���� */
    private BigDecimal taxPrice;
    /** ��� */
    private BigDecimal amount;
    //���ⵥ��������
    /**�ɹ�����*/
    private String orderNo;
    /** ״̬ */
    private Integer status;
    /** �ֿ���� */
    private String warehouseCode;
    /** �ֿ����� */
    private String warehouseName;
    /** ���б��� */
    private String regionCode;
    /** �ŵ���� */
    private String storedCode;
    /** �ŵ�����*/
    private String storedName;
    /** �ͻ����� */
    private String customerCode;
    /** �������� */
    private Date orderDate;
    /** �������� */
    private String deliveryDate;

    public String getOutboundTaskCode() {
        return outboundTaskCode;
    }

    public void setOutboundTaskCode(String outboundTaskCode) {
        this.outboundTaskCode = outboundTaskCode;
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

    public String getCustomerSkuCode() {
        return customerSkuCode;
    }

    public void setCustomerSkuCode(String customerSkuCode) {
        this.customerSkuCode = customerSkuCode;
    }

    public String getCustomerGoodsName() {
        return customerGoodsName;
    }

    public void setCustomerGoodsName(String customerGoodsName) {
        this.customerGoodsName = customerGoodsName;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

   
    
    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

  
   
    
	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}

	public BigDecimal getSortingNum() {
		return sortingNum;
	}

	public void setSortingNum(BigDecimal sortingNum) {
		this.sortingNum = sortingNum;
	}

	public BigDecimal getDeliveryNum() {
		return deliveryNum;
	}

	public void setDeliveryNum(BigDecimal deliveryNum) {
		this.deliveryNum = deliveryNum;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
    
    
}
