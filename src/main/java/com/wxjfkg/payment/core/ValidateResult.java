package com.wxjfkg.payment.core;

import java.util.List;

public class ValidateResult {
	
	public static final ValidateResult SUCCESS = new ValidateResult(true);
	
	public ValidateResult() {
		this(true);
	}
	
	public ValidateResult(boolean success) {
		this(success, null);
	}
	
	public ValidateResult(boolean success, List<String> messages) {
		this.success = success;
		this.messages = messages;
	}

	private List<String> messages;
	
	private boolean success;

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getMessage() {
		if (messages != null && messages.size() > 0) {
			StringBuffer sb = new StringBuffer("");
			for (String msg : messages) {
				sb.append(msg);
			}
			return sb.toString();
		}
		return "";
	}
	
}
