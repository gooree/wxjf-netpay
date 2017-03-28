package com.wxjfkg.payment.dto.webpay;

import com.wxjfkg.payment.core.AbstractResponse;

public class WebPayResponse extends AbstractResponse {

	private String form;
	
	public WebPayResponse(boolean success) {
		super(success);
	}
	
	public WebPayResponse(String code, String msg) {
		super(code, msg);
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}
	
}
