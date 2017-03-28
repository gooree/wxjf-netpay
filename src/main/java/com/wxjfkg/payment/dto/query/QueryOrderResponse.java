package com.wxjfkg.payment.dto.query;

import com.wxjfkg.payment.core.AbstractResponse;

public class QueryOrderResponse extends AbstractResponse {

	private String tradeNo;
	
	private String outTradeNo;
	
	private String buyerId;
	
	private String tradeStatus;
	
	private String totalAmount;
	
	public QueryOrderResponse(boolean success) {
		super(success);
	}
	
	public QueryOrderResponse(String code, String msg) {
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

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
