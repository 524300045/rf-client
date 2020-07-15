package com.wologic.domainnew;

import java.io.Serializable;
import java.util.Date;

public class GoodsSuit implements Serializable {
	
	 /** 序列化标识 */
		private static final long serialVersionUID = 1L;
		
	    /**  */
	    private Long id; 
	    /** 主编码 */
	    private String skuCode; 
	    
	    private String goodsName; 
	    
	    
	    private String goodsUnit; 
	    
	    
	    /** 内部SKU编码 */
	    private String childSkuCode; 
	    
	    private String childGoodsName; 
	    
	    private String childGoodsUnit; 
	    
	    
	    /**  */
	    private Date createTime; 
	    /**  */
	    private String createUser; 
	    /**  */
	    private Date updateTime; 
	    /**  */
	    private String updateUser; 
	    /**  */
	    private Integer yn;
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
		public String getGoodsUnit() {
			return goodsUnit;
		}
		public void setGoodsUnit(String goodsUnit) {
			this.goodsUnit = goodsUnit;
		}
		public String getChildSkuCode() {
			return childSkuCode;
		}
		public void setChildSkuCode(String childSkuCode) {
			this.childSkuCode = childSkuCode;
		}
		public String getChildGoodsName() {
			return childGoodsName;
		}
		public void setChildGoodsName(String childGoodsName) {
			this.childGoodsName = childGoodsName;
		}
		public String getChildGoodsUnit() {
			return childGoodsUnit;
		}
		public void setChildGoodsUnit(String childGoodsUnit) {
			this.childGoodsUnit = childGoodsUnit;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public String getCreateUser() {
			return createUser;
		}
		public void setCreateUser(String createUser) {
			this.createUser = createUser;
		}
		public Date getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Date updateTime) {
			this.updateTime = updateTime;
		}
		public String getUpdateUser() {
			return updateUser;
		}
		public void setUpdateUser(String updateUser) {
			this.updateUser = updateUser;
		}
		public Integer getYn() {
			return yn;
		}
		public void setYn(Integer yn) {
			this.yn = yn;
		} 
	    
	    

}
