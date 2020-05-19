package com.wologic.response;

import java.math.BigDecimal;
import java.util.List;

public class StandPickTaskResponse  {

	private BigDecimal sortingNum;
	
	 private BigDecimal realityNum; 
	 
	 private BigDecimal totalStock; 
	 
	 /*id闆嗗悎*/
	    private List<Long> ids;
	    
	    private String goodsUnit; 
	    
	  //  private String productionDate; 
	    
	    
	    
	 
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

	/** 闁哥喎妫楅幖褏绱撻弽顐ゅ灣 */
    private String skuCode; 
    /** 闁哥喎妫楅幖褔宕ュ鍥嗭拷 */
    private String goodsName; 
    
    /** 闂傚倶鍔岀花鐢电磽閺嶎偆鍨� */
    private String storedCode; 
    /** 闂傚倶鍔岀花鐢稿触瀹ュ泦锟� */
    private String storedName; 
    
    /** 閻犱讲锟藉啿鐏婇柡浣峰嵆閸ｏ拷 */
    private BigDecimal planNum;
    
    //閻庣懓鏈崹姘跺极娴兼潙娅�
    private Integer finishNum;
    
    //闁诡剛绮弳鐔兼煂閿燂拷
    private Integer totalNum;
    
    private String physicsUnit;

	public String getPhysicsUnit() {
		return physicsUnit;
	}

	public void setPhysicsUnit(String physicsUnit) {
		this.physicsUnit = physicsUnit;
	}
    
}
