package com.wxjfkg.payment.dto.webpay;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;

public class WebPayRequest extends AbstractRequest {
	
	@NotNull(message = "商品标题[subject]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "商品标题[subject]不能为空", profiles = { "alipay" })
	@MaxLength(value = 256, message = "商品标题[subject]长度超出限制", profiles = { "alipay" })
	private String subject;
	
	private String body;
	
	@NotNull(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay" })
	@MaxLength(value = 64, message = "商户订单号[outTradeNo]长度超出限制", profiles = { "alipay" })
	@AlipayParam("out_trade_no")
	private String outTradeNo;
	
	@NotNull(message = "订单总金额[totalAmount]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "订单总金额[totalAmount]不能为空", profiles = { "alipay" })
	@MaxLength(value = 12, message = "订单总金额[totalAmount]长度超出限制", profiles = { "alipay" })
	@AlipayParam("total_amount")
	private String totalAmount;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
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
	
}
