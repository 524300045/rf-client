package com.wologic.request;

import java.math.BigDecimal;
import java.util.Date;

/**
 * StandardPickingOperationRequest锛氭爣鍝佹嫞璐ф搷浣滆〃璇锋眰鍙傛暟
 * 
 * @author jinsicao
 * 
 */
public class StandardPickingContainerRequest  {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** 涓婚敭 */
    private Long id; 
   
    /** 瀹瑰櫒缂栫爜 */
    private String containerCode; 
    /** 瀹為檯鏁伴噺 */
    private BigDecimal realityNum; 
    /** 瀹為檯鎷ｈ揣鏃堕棿 */
    private Date realityTime; 
    /** 瀹為檯鎷ｈ揣浜� */
    private String realityUser; 
    /** 鍒涘缓鏃堕棿 */
    private Date createTime; 
    /** 鍒涘缓浜� */
    private String createUser; 
    /** 鏇存柊鏃堕棿 */
    private Date updateTime; 
    /** 鏇存柊浜� */
    private String updateUser;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContainerCode() {
		return containerCode;
	}
	public void setContainerCode(String containerCode) {
		this.containerCode = containerCode;
	}
	public BigDecimal getRealityNum() {
		return realityNum;
	}
	public void setRealityNum(BigDecimal realityNum) {
		this.realityNum = realityNum;
	}
	public Date getRealityTime() {
		return realityTime;
	}
	public void setRealityTime(Date realityTime) {
		this.realityTime = realityTime;
	}
	public String getRealityUser() {
		return realityUser;
	}
	public void setRealityUser(String realityUser) {
		this.realityUser = realityUser;
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
  
    
    
}
