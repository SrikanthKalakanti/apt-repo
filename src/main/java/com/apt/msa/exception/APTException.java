package com.apt.msa.exception;

public class APTException extends Exception{
	
	/**
	 * Custom Expection class for our application
	 @author srikanth
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	private String errMessage;
	private String exceptionType;
	private Exception exception;

	public APTException(String errorCode, String errMessage,
			String exceptionType, Exception exception) {
		super();
		this.errorCode = errorCode;
		this.errMessage = errMessage;
		this.exceptionType = exceptionType;
		this.exception = exception;
	}

	public APTException(String errorCode, String errMessage,
			String exceptionType) {
		super();
		this.errorCode = errorCode;
		this.errMessage = errMessage;
		this.exceptionType = exceptionType;
	}

	public APTException(String errMessage,	String exceptionType,Exception exception){
		this.errMessage = errMessage;
		this.exception = exception;
		this.exceptionType = exceptionType;
	}
	
	public APTException(String exceptionType){
		this.exceptionType = exceptionType;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public String getExceptionType() {
		return exceptionType;
	}

	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
