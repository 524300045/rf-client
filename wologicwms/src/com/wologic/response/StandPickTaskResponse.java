package com.wologic.response;

import java.math.BigDecimal;

public class StandPickTaskResponse  {

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

	/** 鍟嗗搧缂栫爜 */
    private String skuCode; 
    /** 鍟嗗搧鍚嶇О */
    private String goodsName; 
    
    /** 闂ㄥ簵缂栫爜 */
    private String storedCode; 
    /** 闂ㄥ簵鍚嶇О */
    private String storedName; 
    
    /** 璁″垝鏁伴噺 */
    private BigDecimal planNum;
    
    //瀹屾垚鏁伴噺
    private Integer finishNum;
    
    //鎬绘暟閲�
    private Integer totalNum;
    
}
