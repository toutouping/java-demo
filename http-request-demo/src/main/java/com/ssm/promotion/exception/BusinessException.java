package com.ssm.promotion.exception;

public class BusinessException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BusinessException() {
		super();
	}
	
	public BusinessException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
