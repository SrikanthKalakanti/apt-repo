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
    public static final String STATUS_UPDATE_BASICINPUT_SUCCESS ="Client Basic Input Updated Successfully";
    public static final String STATUS_UPDATE_BASICINPUT_FAILURE ="Client Basic Input Update Failed";
    public static final String STATUS_DELETE_BASICINPUT_SUCCESS ="Client Basic Input Deleted Successfully";
    public static final String STATUS_DELETE_BASICINPUT_FAILURE ="Client Basic Input Delete Failed";

    
    
    public static final String STATUS_CREATE_GROWTHINPUT_SUCCESS ="Client Growth and Inflation Input Created Successfully";
    public static final String STATUS_CREATE_GROWTHINPUT_FAILURE ="Client Growth and Inflation Creation Failed";
    public static final String STATUS_RETRIEVED_GROWTHINPUT_DETAILS ="Retrieved Growth and Inflation Input details Successfully";
    public static final String STATUS_UPDATE_GROWTHINPUT_SUCCESS ="Client Growth and Inflation Updated Successfully";
    public static final String STATUS_UPDATE_GROWTHINPUT_FAILURE ="Client Growth and Inflation Updated Failed";
    public static final String STATUS_DELETE_GROWTHINPUT_SUCCESS ="Client Growth and Inflation Deleted Successfully";
    public static final String STATUS_DELETE_GROWTHINPUT_FAILURE ="Client Growth and Inflation Delete Failed";
    
    
    public static final String STATUS_CREATE_ASSETINPUT_SUCCESS ="Client Asset Input Created Successfully";
    public static final String STATUS_CREATE_ASSETINPUT_FAILURE ="Client Asset Input Creation Failed";
    public static final String STATUS_RETRIEVED_ASSETINPUT_DETAILS ="Retrieved Asset Input details Successfully";
    public static final String STATUS_UPDATE_ASSETINPUT_SUCCESS ="Client Asset Input Updated Successfully";
    public static final String STATUS_UPDATE_ASSETINPUT_FAILURE ="Client Asset Input Update Failed";
    public static final String STATUS_DELETE_ASSETINPUT_SUCCESS ="Client Asset Input Deleted Successfully";
    public static final String STATUS_DELETE_ASSETINPUT_FAILURE ="Client Asset Input Delete Failed";
    
    public static final String STATUS_CREATE_EXPENSESINPUT_SUCCESS ="Client Expenses Input Created Successfully";
    public static final String STATUS_CREATE_EXPENSESINPUT_FAILURE ="Client Expenses Input Creation Failed";
    public static final String STATUS_RETRIEVED_EXPENSESINPUT_DETAILS ="Retrieved Expenses Input details Successfully";
    public static final String STATUS_UPDATE_EXPENSESINPUT_SUCCESS ="Client Expenses Input Updated Successfully";
    public static final String STATUS_UPDATE_EXPENSESINPUT_FAILURE ="Client Expenses Input Update Failed";
    public static final String STATUS_DELETE_EXPENSESINPUT_SUCCESS ="Client Expenses Input Deleted Successfully";
    public static final String STATUS_DELETE_EXPENSESINPUT_FAILURE ="Client Expenses Input Delete Failed";
    
    
    public static final String STATUS_SUCCESS_LOGIN ="Login Successful";
    public static final String STATUS_MAIL_SENDED ="Mail sended successfully.Check your email id for your password.";
    
    public static final String STATUS_RETRIEVED_CUSTOMER_DETAILS ="Retrieved Customer details Successfully";
    public static final String STATUS_RETRIEVED_CLIENT_DETAILS ="Retrieved Client details Successfully";
    
    public static final String STATUS_NOCLIENT_DETAILS ="No Client details available";
    
    public static final String STATUS_NOASSETINPUT_DETAILS ="No Asset Input details available";
    public static final String STATUS_NOBASICINPUT_DETAILS ="No Basic Input details available";
    public static final String STATUS_NOGROWTHINFLATION_DETAILS ="No Growth & Inflation details available";
    public static final String STATUS_NOEXPENSES_DETAILS ="No Expenses details available";
    public static final String STATUS_NOSELLINGPRICE_DETAILS ="No Selling Price details available";
    
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
	
	public static final String ERROR_CODE_ASSETINPUT_NOT_EXISTS          = "APT1006";	  
	public static final String ERROR_CODE_BASICINPUT_NOT_EXISTS          = "APT1007";	  
	public static final String ERROR_CODE_GROWTHINFLATION_NOT_EXISTS     = "APT1008";	  
	public static final String ERROR_CODE_EXPENSES_NOT_EXISTS          	 = "APT1009";	  
 
}
