package com.trade.vo;

public class AccountVo {
	private String acctNo;
	private String acctNm;
	private String mngCd;
	
	public AccountVo() {
	}
	
	public AccountVo(String acctNo, String acctNm, String mngCd) {
		this.acctNo = acctNo;
		this.acctNm = acctNm;
		this.mngCd = mngCd;
	}
	
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getAcctNm() {
		return acctNm;
	}
	public void setAcctNm(String acctNm) {
		this.acctNm = acctNm;
	}
	public String getMngCd() {
		return mngCd;
	}
	public void setMngCd(String mngCd) {
		this.mngCd = mngCd;
	}
}
