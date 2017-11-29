package com.apt.msa.response;

import com.apt.msa.util.ResultStatusConstants;

public class ResponseHelper {

	public static Response UNKNOWN_EXCEPTION_RESPONSE = new Response(
			ResultStatusConstants.STATUS_FAIL,
			ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
			ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
			ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
	
}