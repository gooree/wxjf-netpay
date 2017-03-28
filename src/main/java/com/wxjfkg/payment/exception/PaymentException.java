package com.wxjfkg.payment.exception;

public class PaymentException extends Exception {

	private static final long serialVersionUID = 9121503880432661598L;

	public PaymentException(String message) {
		super(message);
	}
	
	public PaymentException(Throwable cause) {
		super(cause);
	}
	
	public PaymentException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
