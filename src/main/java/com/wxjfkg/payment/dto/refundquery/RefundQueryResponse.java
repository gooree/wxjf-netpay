package com.wxjfkg.payment.dto.refundquery;

import java.util.List;

import com.wxjfkg.payment.core.AbstractResponse;

public class RefundQueryResponse extends AbstractResponse {

	private String tradeNo;
	
	private String outTradeNo;
	
	private String totalAmount;
	
	private List<RefundInfo> refundList;
	
	public RefundQueryResponse(boolean success) {
		super(success);
	}
	
	public RefundQueryResponse(String code, String msg) {
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

	public List<RefundInfo> getRefundList() {
		return refundList;
	}

	public void setRefundList(List<RefundInfo> refundList) {
		this.refundList = refundList;
	}
	
}
