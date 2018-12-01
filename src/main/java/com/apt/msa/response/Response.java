package com.apt.msa.response;

import java.io.Serializable;

import com.apt.msa.exception.APTException;
import com.apt.msa.util.ResultStatusConstants;

public class Response implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	private String status;
    private String errorCode;
    private String errorMessage;
    private String exceptionType;
    private Object result;
    private Long customerId;
    private String userName;
    
    public Response() {
    }
    
    public Response(String status,String errorCode, String errorMessage, String exceptionType,Object result) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
        this.result = result;
    }
    
    public Response(String status, String errorCode, String errorMessage, String exceptionType) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
    }
    
    public Response(String status, String errorCode, String errorMessage) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public Response(String status, String errorCode, String errorMessage,String exceptionType,Long customerId,String userName) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.exceptionType = exceptionType;
        this.customerId = customerId;
        this.userName = userName;
    }
    
    public Response(APTException aptException) {
        this.status = ResultStatusConstants.STATUS_FAIL;
        this.errorCode = aptException.getErrorCode();
        this.errorMessage = aptException.getErrMessage();
        this.exceptionType = aptException.getExceptionType();
    }
    
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getExceptionType() {
		return exceptionType;
	}
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
