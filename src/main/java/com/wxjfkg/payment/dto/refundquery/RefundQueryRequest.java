package com.wxjfkg.payment.dto.refundquery;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;
import com.wxjfkg.payment.validate.ValidMethod;

public class RefundQueryRequest extends AbstractRequest {

	@ValidMethod(methodName = "isWxPayValid", message = "微信订单号|商户订单号|商户退款单号|微信退款单号不能同时为空", profiles = { "wxpay" }, parameterType = String.class)
	@AlipayParam("trade_no")
	private String tradeNo;

	@ValidMethod(methodName = "isAliPayValid", message = "支付宝交易号和商户订单号不能同时为空", profiles = { "alipay" }, parameterType = String.class)
	@AlipayParam("out_trade_no")
	private String outTradeNo;

	@NotNull(message = "商户退款单号[outRefundNo]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "商户退款单号[outRefundNo]不能为空", profiles = { "alipay" })
	@AlipayParam("out_request_no")
	private String outRefundNo;

	private String refundNo;
	
	@SuppressWarnings("unused")
	private boolean isWxPayValid(String value) {
		if (StringUtils.isBlank(tradeNo) && StringUtils.isBlank(outTradeNo)
				&& StringUtils.isBlank(outRefundNo) && StringUtils.isBlank(refundNo)) {
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unused")
	private boolean isAliPayValid(String value) {
		if (StringUtils.isBlank(tradeNo) && StringUtils.isBlank(outTradeNo)) {
			return false;
		}
		return true;
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

}
