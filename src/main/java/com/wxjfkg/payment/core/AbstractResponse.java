package com.wxjfkg.payment.core;

import java.io.Serializable;

/**
 * 抽象返回类
 * 
 * @author GuoRui
 *
 */
public class AbstractResponse implements Serializable {

	private boolean success;
	
	private String errorCode;
	
	private String errorMsg;
	
	public AbstractResponse(boolean success) {
		this.success = success;
	}
	
	public AbstractResponse(String code, String msg) {
		this.errorCode = code;
		this.errorMsg = msg;
		this.success = false;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
