package com.revature.util;

public class HttpException extends RuntimeException{
	private int errorCode;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public HttpException() {
		super();
	}

	public HttpException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
}
