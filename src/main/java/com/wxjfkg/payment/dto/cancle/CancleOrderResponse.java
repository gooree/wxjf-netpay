package com.wxjfkg.payment.dto.cancle;

import com.wxjfkg.payment.core.AbstractResponse;

public class CancleOrderResponse extends AbstractResponse {

	private String tradeNo;
	
	private String outTradeNo;
	
	private String retryFlag;
	
	private String action;
	
	public CancleOrderResponse(boolean success) {
		super(success);
	}
	
	public CancleOrderResponse(String code, String msg) {
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

	public String getRetryFlag() {
		return retryFlag;
	}

	public void setRetryFlag(String retryFlag) {
		this.retryFlag = retryFlag;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
}
