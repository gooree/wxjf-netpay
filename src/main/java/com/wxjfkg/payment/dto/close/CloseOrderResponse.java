package com.wxjfkg.payment.dto.close;

import com.wxjfkg.payment.core.AbstractResponse;

public class CloseOrderResponse extends AbstractResponse {

	/**
	 * 微信不返回tradeNo
	 */
	private String tradeNo;

	private String outTradeNo;
	
	public CloseOrderResponse(boolean success) {
		super(success);
	}
	
	public CloseOrderResponse(String code, String msg) {
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

}
