package com.apt.msa.util;

public class ErrorCodes {

	public static int SUCCESS = 1001; // Database SUCCESS
	public static int FAILURE = 1002; // Database Failure
	public static int INVALID_USER = 1003; //Invalid User
	public static int EMPTY = 1004; // Empty while retrieving the records from table
	public static int INVALID_USERNAME = 1005; // Invalid Username
	public static int INVALID_PASSWORD = 1006; // Invalid password
	public static int INVALID_CAPCHA = 1007; // INVALID_CAPCHA
	public static int UNAUTHORIZED_USER = 1008; // UNAUTHORIZED_USER
	public static int DB_FAILURE = 1009; // Database failure
	public static int USER_ALREADY_EXIST = 1010; // Database failure
	public static int UNKNOWN_ERROR = 1011; // Database failure
	public static int UPDATE_SUCCESS = 1011;
	public static int UPDATE_FAIL = 1012;
	
	public interface ExceptionType{
		
		public static String SUCCESS = "SUCCESS"; // Database SUCCESS
		public static String COULD_NOT_PROCSS_REQ = "COULD_NOT_PROCSS_REQ"; //Network problem
		public static String INVALID_USER = "INVALID_USER"; //Invalid User
		public static String INVALID_PASSWORD = "INVALID_PASSWORD"; //Invalid User
		public static String INACTIVE_USER = "INACTIVE_USER"; //Invalid User
		public static String FAILURE = "FAILURE"; // Database Failure
		public static String RETRY = "RETRY"; // Database Retry
		public static String EMPTY = "EMPTY"; // Empty while retrieving the records from table
		public static String DB_ERROR = "DB_EXCEPTION"; // Database Error
		public static String SQL_EXCEPTION = "SQL_EXCEPTION"; // SQL Error
		public static String EXCEPTION = "EXCEPTION"; // Exception
    	public static String RUNTIME_ERROR = "RUNTIME_ERROR"; // Runtime Error
    	public static String USER_ALREADY_EXIST = "USER_ALREADY_EXIST"; // USER_ALREADY_EXIST
    	
    	public static String UNEXPECTED_EXCPETION = "UNEXPECTED_EXCPETION";
    	public static String UNKNOWN_EXCPETION = "UNKNOWN_EXCPETION";
    	public static String VALIDATION_EXCPETION = "VALIDATION_EXCPETION";
    	public static String UNAUTHORIZED_USER = "UNAUTHORIZED_USER"; // UNAUTHORIZED USER
    	public static String UPDATESUCCESS = "UPDATE_SUCCESS"; // UPDATE_SUCCESS
    	public static String UPDATE_FAIL = "UPDATE FAILURE";   
				
	}
	
	public interface ErrorMessage{
		
		public static String INVALID_USERNAME = "We didn't recognize the email address you entered.";
		public static String INVALID_PASSWORD = "The password you entered is incorrect.";
		public static String UNAUTHORIZED_USER = "Unauthorized User.";
		public static String DB_ERROR = "Error occured while performing database operation";
		public static String USER_ALREADY_EXIST_ERROR = "User already exists";
		public static String UNKNOWN_ERROR = "Unknown error.Please contact Site Administrator";
		public static String UPDATE_SUCCESS = "Update Success"; 
    	public static String UPDATE_FAIL = "Update failure"; 
    	
	}
	
    	
}

