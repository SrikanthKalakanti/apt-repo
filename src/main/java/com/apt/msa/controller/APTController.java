package com.apt.msa.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.APTInput;
import com.apt.msa.entity.Client;
import com.apt.msa.exception.APTException;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAPTService;
import com.apt.msa.service.IClientService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("apt")
public class APTController {
	
	private static final Logger logger = LoggerFactory.getLogger(APTController.class);

	@Autowired
	private IAPTService aptService;
	
	/**
	 * author srikanth
	 * 
	 * 
	 * @param requestEntity
	 * @return
	 */

	@RequestMapping(method=RequestMethod.POST, value ="createaptinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response creatAptInput(RequestEntity<APTInput> requestEntity) {
		try {
			APTInput aptInput = requestEntity.getBody();
			

			Date termLoandate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(aptInput.getBasicInput().getTermLoanDisbursement()).getTime());
			aptInput.getBasicInput().setTermLoanFirstDisbursementDate(termLoandate);
			
			Date buisnessCommenceDate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(aptInput.getBasicInput().getBusinessCommencement()).getTime());
			aptInput.getBasicInput().setBusinessCommencementDate(buisnessCommenceDate);
								
			aptService.createAPTInput(aptInput);
			
			return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
					ResultStatusConstants.STATUS_CREATE_APTINPUT_SUCCESS,null);
			
		} catch (APTException aptException) {
			return new Response(aptException);
		
		} catch(Exception e){
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
		
}