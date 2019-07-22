package com.trade.vo;

public class TradeVo {
	private String bsDt;
	private String acctNo;
	private String trdNo;
	private int amt;
	private int fee;
	private String cnclYn;
	
	
	public TradeVo() {
	}
	
	public TradeVo(String bsDt, String acctNo, String trdNo,
			int amt, int fee, String cnclYn) {
		this.bsDt = bsDt;
		this.acctNo = acctNo;
		this.trdNo = trdNo;
		this.amt = amt;
		this.fee = fee;
		this.cnclYn = cnclYn;
	}
	
	public String getBsDt() {
		return bsDt;
	}
	public void setBsDt(String bsDt) {
		this.bsDt = bsDt;
	}
	public String getAcctNo() {
		return acctNo;
	}
	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	public String getTrdNo() {
		return trdNo;
	}
	public void setTrdNo(String trdNo) {
		this.trdNo = trdNo;
	}
	public int getAmt() {
		return amt;
	}
	public void setAmt(int amt) {
		this.amt = amt;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public String getCnclYn() {
		return cnclYn;
	}
	public void setCnclYn(String cnclYn) {
		this.cnclYn = cnclYn;
	}
	
	public String getYear() {
		return this.bsDt.substring(0,4);
	}
	
	public int calcAmtMinusFee() {
		return this.amt-this.fee;
	}
}
