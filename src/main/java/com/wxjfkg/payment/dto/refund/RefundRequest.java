package com.wxjfkg.payment.dto.refund;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;
import com.wxjfkg.payment.validate.ValidMethod;

public class RefundRequest extends AbstractRequest {

	@ValidMethod(methodName = "isValid", message = "商户订单号和支付平台订单号不能同时为空", profiles = {
			"alipay", "wxpay" }, parameterType = String.class)
	@AlipayParam("out_trade_no")
	private String outTradeNo;
	
	@AlipayParam("trade_no")
	private String tradeNo;
	
	@NotNull(message = "商户退款单号[outRefundNo]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "商户退款单号[outRefundNo]不能为空", profiles = { "wxpay" })
	@AlipayParam("out_request_no")
	private String outRefundNo;
	
	@NotNull(message = "订单金额[totalAmount]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "订单金额[totalAmount]不能为空", profiles = { "wxpay" })
	private String totalAmount;
	
	@NotNull(message = "退款金额[refundAmount]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "退款金额[refundAmount]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("refund_amount")
	private String refundAmount;
	
	@AlipayParam("refund_reason")
	private String refundReason;
	
	@SuppressWarnings("unused")
	private boolean isValid(String value) {
		if (StringUtils.isBlank(tradeNo) && StringUtils.isBlank(outTradeNo)) {
			return false;
		}
		return true;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOutRefundNo() {
		return outRefundNo;
	}

	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}
	
}
