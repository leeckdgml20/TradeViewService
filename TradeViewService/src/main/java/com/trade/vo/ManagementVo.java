package com.trade.vo;

public class ManagementVo {
	private String mngCd;
	private String mngNm;
	
	public ManagementVo() {
	}
	
	public ManagementVo(String mngCd, String mngNm) {
		this.mngCd = mngCd;
		this.mngNm = mngNm;
	}
	
	public String getMngCd() {
		return mngCd;
	}
	public void setMngCd(String mngCd) {
		this.mngCd = mngCd;
	}
	public String getMngNm() {
		return mngNm;
	}
	public void setMngNm(String mngNm) {
		this.mngNm = mngNm;
	}
	
}
