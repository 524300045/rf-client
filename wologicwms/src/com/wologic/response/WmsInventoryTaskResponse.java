package com.wologic.response;

import java.math.BigDecimal;
import java.util.Date;

/**
 * WmsInventoryResponse锛歐MS鐩樼偣鍗曡繑鍥炲璞�<br/>
 * 鎻愪緵rest鎺ュ彛鏃舵柟娉曠殑杩斿洖瀵硅薄
 * 
 * @author jinsicao
 * 
 */
public class WmsInventoryTaskResponse  {

    /** 搴忓垪鍖栨爣璇� */
    private static final long serialVersionUID = 1L;
    
    /** 涓婚敭id */
    private Long id; 
    /** 鐩樼偣鍗曞彿 */
    private String wmsInventoryOrderNo; 
   
    /** 搴撳尯缂栫爜 */
    private String areaCode; 
    /** 搴撳尯鍚嶇О */
    private String areaName; 
    
    //鎬讳换鍔℃暟閲�
    private Integer totalNum;
    
    //瀹屾垚浠诲姟鏁伴噺
    private Integer finishNum;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWmsInventoryOrderNo() {
		return wmsInventoryOrderNo;
	}

	public void setWmsInventoryOrderNo(String wmsInventoryOrderNo) {
		this.wmsInventoryOrderNo = wmsInventoryOrderNo;
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

	public Integer getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	public Integer getFinishNum() {
		return finishNum;
	}

	public void setFinishNum(Integer finishNum) {
		this.finishNum = finishNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
