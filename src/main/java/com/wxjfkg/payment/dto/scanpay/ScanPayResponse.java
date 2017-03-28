package com.wxjfkg.payment.dto.scanpay;

import java.util.Date;

import com.wxjfkg.payment.core.AbstractResponse;

public class ScanPayResponse extends AbstractResponse {

	private String tradeNo;
	
	private String outTradeNo;
	
	private String totalAmount;
	
	private String userId;
	
	private Date payTime;
	
	public ScanPayResponse(boolean success) {
		super(success);
	}
	
	public ScanPayResponse(String code, String msg) {
		super(code, msg);
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
