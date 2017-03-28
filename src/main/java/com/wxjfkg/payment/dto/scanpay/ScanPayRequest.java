package com.wxjfkg.payment.dto.scanpay;

import java.util.Map;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import com.wxjfkg.payment.annotation.AlipayParam;
import com.wxjfkg.payment.core.AbstractRequest;

public class ScanPayRequest extends AbstractRequest {

	@NotNull(message = "订单标题[subject]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "订单标题[subject]不能为空", profiles = { "alipay", "wxpay" })
	private String subject;
	
	private String body;
	
	private Map<String, Object> attach;
	
	@NotNull(message = "设备号[deviceInfo]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "设备号[deviceInfo]不能为空", profiles = { "wxpay" })
	private String deviceInfo;
	
	@NotNull(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "商户订单号[outTradeNo]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("out_trade_no")
	private String outTradeNo;
	
	@NotNull(message = "订单金额[totalAmount]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "订单金额[totalAmount]不能为空", profiles = { "wxpay" })
	@AlipayParam("total_amount")
	private String totalAmount;
	
	@NotNull(message = "终端编号[terminalId]不能为空", profiles = { "wxpay" })
	@NotEmpty(message = "终端编号[terminalId]不能为空", profiles = { "wxpay" })
	@AlipayParam("terminal_id")
	private String terminalId;
	
	@NotNull(message = "授权码[authCode]不能为空", profiles = { "alipay", "wxpay" })
	@NotEmpty(message = "授权码[authCode]不能为空", profiles = { "alipay", "wxpay" })
	@AlipayParam("auth_code")
	private String authCode;
	
	/**
	 * 支付场景(默认值：bar_code)
	 */
	@NotNull(message = "支付场景[scene]不能为空", profiles = { "alipay" })
	@NotEmpty(message = "支付场景[scene]不能为空", profiles = { "alipay" })
	private String scene = "bar_code";

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

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

	public String getTerminalId() {
		return terminalId;
	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}
	
}
