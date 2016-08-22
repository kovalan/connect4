package com.games.demo.connect4.exception;

public class ErrorMessage {

	private int errorCode;
	private String errorMessage;
	
	public ErrorMessage(int errCode, String errMsg) {
		this.errorCode = errCode;
		this.errorMessage = errMsg;
	}
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
}
