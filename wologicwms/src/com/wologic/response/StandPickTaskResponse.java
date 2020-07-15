package com.wologic.response;

import java.math.BigDecimal;
import java.util.List;

public class StandPickTaskResponse  {

	private BigDecimal sortingNum;
	
	 private BigDecimal realityNum; 
	 
	 private BigDecimal totalStock; 
	 
	 /*id闂嗗棗鎮�*/
	    private List<Long> ids;
	    
	    private String goodsUnit; 
	    
	  //  private String productionDate; 
	    
	    /**
	     * 商品类型0:正常 1:套装
	     */
	    private Integer goodsType;
	    
	    
	    
	 
	 public Integer getGoodsType() {
			return goodsType;
		}

		public void setGoodsType(Integer goodsType) {
			this.goodsType = goodsType;
		}

	public String getGoodsUnit() {
			return goodsUnit;
		}

		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}

//		public String getProductionDate() {
//			return productionDate;
//		}
//
//		public void setProductionDate(String productionDate) {
//			this.productionDate = productionDate;
//		}

	public List<Long> getIds() {
			return ids;
		}

		public void setIds(List<Long> ids) {
			this.ids = ids;
		}

	public BigDecimal getTotalStock() {
		return totalStock;
	}

	public void setTotalStock(BigDecimal totalStock) {
		this.totalStock = totalStock;
	}

	public BigDecimal getRealityNum() {
		return realityNum;
	}

	public void setRealityNum(BigDecimal realityNum) {
		this.realityNum = realityNum;
	}

	public BigDecimal getSortingNum() {
		return sortingNum;
	}

	public void setSortingNum(BigDecimal sortingNum) {
		this.sortingNum = sortingNum;
	}

	private Long id; 
	 
	  public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public BigDecimal getPlanNum() {
		return planNum;
	}

	public void setPlanNum(BigDecimal planNum) {
		this.planNum = planNum;
	}

	public Integer getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(Integer finishNum) {
		this.finishNum = finishNum;
	}

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	/** 闂佸摜鍠庡Λ妤呭箹瑜忕槐鎾诲冀椤愩倕鐏� */
    private String skuCode; 
    /** 闂佸摜鍠庡Λ妤呭箹瑜斿畷銉ヮ吋閸ュ棴鎷� */
    private String goodsName; 
    
    /** 闂傚倸鍊堕崝宀�鑺遍悽鐢电＝闁哄稁鍋嗛崹锟� */
    private String storedCode; 
    /** 闂傚倸鍊堕崝宀�鑺遍悽绋胯Е鐎广儱娉﹂敓锟� */
    private String storedName; 
    
    /** 闁荤姳璁查敓钘夊暱閻忓﹪鏌℃担宄板祮闁革綇鎷� */
    private BigDecimal planNum;
    
    //闁诲海鎳撻張顒勫垂濮樿泛鏋佸ù鍏兼綑濞咃拷
    private Integer finishNum;
    
    //闂佽鍓涚划顖炲汲閻斿吋鐓傞柨鐕傛嫹
    private Integer totalNum;
    
    private String physicsUnit;

	public String getPhysicsUnit() {
		return physicsUnit;
	}

	public void setPhysicsUnit(String physicsUnit) {
		this.physicsUnit = physicsUnit;
	}
    
}
