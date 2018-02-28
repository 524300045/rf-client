package com.wologic.response;

public class AreaPickerInfoResponse {

    /** 搴撳尯缂栫爜 */
    private String areaCode; 
    /** 搴撳尯鍚嶇О */
    private String areaName;  
    
    //鎬荤殑浠诲姟鏁伴噺
    private int totalCount;
    
    //瀹屾垚鐨勪换鍔℃暟閲�
    private int finishCount;

	
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getFinishCount() {
		return finishCount;
	}

	public void setFinishCount(int finishCount) {
		this.finishCount = finishCount;
	}
    
    
}
