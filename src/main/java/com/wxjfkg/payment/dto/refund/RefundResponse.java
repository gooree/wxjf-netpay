package com.wxjfkg.payment.dto.refund;

import com.wxjfkg.payment.core.AbstractResponse;

public class RefundResponse extends AbstractResponse {

	private String tradeNo;
	
	private String outTradeNo;
	
	private String outRefundNo;
	
	private String refundNo;
	
	private String refundAmount;
	
	public RefundResponse(boolean success) {
		super(success);
	}
	
	public RefundResponse(String code, String msg) {
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

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getRefundNo() {
		return refundNo;
	}

	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}
	
}
