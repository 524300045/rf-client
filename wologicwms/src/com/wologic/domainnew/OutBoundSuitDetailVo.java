package com.wologic.domainnew;

import java.math.BigDecimal;
import java.util.Date;

public class OutBoundSuitDetailVo {

    /** 序列化标识 */
	private static final long serialVersionUID = 1L;
	
    //出库单明细表数据
    /** 出库任务单号 */
    private String outboundTaskCode;
    /** 商品编码 */
    private String skuCode; 
    /** 商品名称 */
    private String goodsName; 
    /** 客户商品编码 */
    private String customerSkuCode; 
    /** 客户商品名称 */
    private String customerGoodsName; 
    /** 供应商编码 */
    private String partnerCode; 
    /** 供应商名称 */
    private String partnerName; 
    /** 计划数量 */
    private BigDecimal planNum; 
    /** 分拣数量 */
    private BigDecimal sortingNum; 
    /** 发运数量*/
    private BigDecimal deliveryNum; 
    /** 含税单价 */
    private BigDecimal taxPrice;
    /** 金额 */
    private BigDecimal amount;
    //出库单主表数据
    /**采购单号*/
    private String orderNo;
    /** 状态 */
    private Integer status;
    /** 仓库编码 */
    private String warehouseCode;
    /** 仓库名称 */
    private String warehouseName;
    /** 城市编码 */
    private String regionCode;
    /** 门店编码 */
    private String storedCode;
    /** 门店名称*/
    private String storedName;
    /** 客户编码 */
    private String customerCode;
    /** 订单日期 */
    private Date orderDate;
    /** 配送日期 */
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
