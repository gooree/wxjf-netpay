package com.wxjfkg.payment.dto.create;

import java.util.Map;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;

public class CreateOrderRequest extends AbstractRequest {

	@NotNull(message = "商品描述[body]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "商品描述[body]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("subject")
	private String body;
	
	private Map<String, Object> attach;
	
	@NotNull(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("out_trade_no")
	private String outTradeNo;
	
	@NotNull(message = "订单金额[totalAmount]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "订单金额[totalAmount]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("total_amount")
	private String totalAmount;
	
	@NotNull(message = "销售产品码[productCode]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "销售产品码[productCode]不能为空", profiles = { "alipay" })
	@AlipayParam("product_code")
	private String productCode;
	
	@NotNull(message = "用户ID[userId]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "用户ID[userId]不能为空", profiles = { "wxpay" })
	private String userId;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Map<String, Object> getAttach() {
		return attach;
	}

	public void setAttach(Map<String, Object> attach) {
		this.attach = attach;
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

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
