package com.apt.msa.util;

public class ResultStatusConstants {
    
	//success constants
    public static final String STATUS_OK ="ok";
    public static final String SUCCESS_CODE ="APT1000";
    public static final String FAIL_CODE ="APT1001";
    public static final String STATUS_SUCCESS ="Success";
    public static final String STATUS_CREATE_CUSTOMER_SUCCESS ="Customer Created Successfully";
    public static final String STATUS_CREATE_CLIENT_SUCCESS ="Client Created Successfully";
    public static final String STATUS_UPDATE_CLIENT_SUCCESS ="Client Updated Successfully";
    
    public static final String STATUS_CREATE_APTINPUT_SUCCESS ="Client APT Input Created Successfully";
    
    public static final String STATUS_CREATE_BASICINPUT_SUCCESS ="Client Basic Input Created Successfully";
    public static final String STATUS_CREATE_BASICINPUT_FAILURE ="Client Basic Input Creation Failed";
    public static final String STATUS_RETRIEVED_BASIC_DETAILS ="Retrieved Basic Input details Successfully";
    
    public static final String STATUS_CREATE_GROWTHINPUT_SUCCESS ="Client Growth and Inflation Input Created Successfully";
    public static final String STATUS_CREATE_GROWTHINPUT_FAILURE ="Client Growth and Inflation Creation Failed";
    public static final String STATUS_RETRIEVED_GROWTHINPUT_DETAILS ="Retrieved Growth and Inflation Input details Successfully";
    
    public static final String STATUS_CREATE_ASSETINPUT_SUCCESS ="Client Asset Input Created Successfully";
    public static final String STATUS_CREATE_ASSETINPUT_FAILURE ="Client Asset Input Creation Failed";
    public static final String STATUS_RETRIEVED_ASSETINPUT_DETAILS ="Retrieved Asset Input details Successfully";
    
    public static final String STATUS_CREATE_EXPENSESINPUT_SUCCESS ="Client Expenses Input Created Successfully";
    public static final String STATUS_CREATE_EXPENSESINPUT_FAILURE ="Client Expenses Input Creation Failed";
    public static final String STATUS_RETRIEVED_EXPENSESINPUT_DETAILS ="Retrieved Expenses Input details Successfully";
    
   
    public static final String STATUS_SUCCESS_LOGIN ="Login Successful";
    public static final String STATUS_MAIL_SENDED ="Mail sended successfully.Check your email id for your password.";
    
    public static final String STATUS_RETRIEVED_CUSTOMER_DETAILS ="Retrieved Customer details Successfully";
    public static final String STATUS_RETRIEVED_CLIENT_DETAILS ="Retrieved Client details Successfully";
    
    public static final String STATUS_NOCLIENT_DETAILS ="No Client details available";
    
    //failure constants
    public static final String STATUS_FAIL = "fail";
    
    public static final String ERROR_CODE_INVALID_JSON       = "APT1002";
 	public static final String ERROR_MESSAGE_INVALID_JSON    = "Invalid JSON request";
 	
    public static final String  ERROR_CODE_UNKNOWN_ERROR = "APT1003";
    public static final String  ERROR_MESSAGE_UNKNOWN_ERROR ="Unknown Excepton, Please contact Admistrator";
    
    public static final String ERROR_UNKNOWN_EXCPETION ="APT1004";
    public static final String ERROR_MESSAGE_UNKNOWN_EXCPETION    = "UNKNOWN_EXCPETION";
    
	public static final String ERROR_CODE_USER_ALREADY_EXISTS          = "APT1004";	  
	public static final String ERROR_MESSAGE_USER_ALREADY_EXISTS 	   = "User already exists";
	
	public static final String ERROR_CODE_USER_NOT_EXISTS          = "APT1005";	  
	public static final String ERROR_MESSAGE_USER_NOT_EXISTS 	   = "Invalid User Name or Password";
	
    
 
}
