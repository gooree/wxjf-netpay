package com.wxjfkg.payment.dto.cancle;

import org.apache.commons.lang3.StringUtils;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;
import com.wxjfkg.payment.validate.ValidMethod;

public class CancleOrderRequest extends AbstractRequest {

	@ValidMethod(methodName = "isValid", message = "商户订单号和支付平台订单号不能同时为空", profiles = {
			"alipay", "wxpay" }, parameterType = String.class)
	@AlipayParam("trade_no")
	private String tradeNo;
	
	@AlipayParam("out_trade_no")
	private String outTradeNo;
	
	@SuppressWarnings("unused")
	private boolean isValid(String value) {
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
	
}
