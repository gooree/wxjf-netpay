package com.wxjfkg.payment.dto.create;

import java.util.HashMap;
import java.util.Map;

import com.wxjfkg.payment.core.AbstractResponse;

public class CreateOrderResponse extends AbstractResponse {

	private Map<String, String> data = new HashMap<String, String>();
	
	public CreateOrderResponse(boolean success) {
		super(success);
	}
	
	public CreateOrderResponse(String code, String msg) {
		super(code, msg);
	}
	
	public void put(String key, String value) {
		data.put(key, value);
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	public String getValue(String key) {
		return this.data.get(key);
	}
	
}
