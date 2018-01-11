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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.Client;
import com.apt.msa.exception.APTException;
import com.apt.msa.response.Response;
import com.apt.msa.service.IClientService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client")
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private IClientService clientService;
	
	/**
	 * author srikanth
	 * 
	 * 
	 * @param requestEntity
	 * @return
	 */

	@RequestMapping(method=RequestMethod.POST, value ="createclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response creatClient(RequestEntity<Client> requestEntity) {
		try {
			Client clientDetails = requestEntity.getBody();
			

			Date termLoandate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(clientDetails.getBasicInput().getTermLoanDisbursement()).getTime());
			clientDetails.getBasicInput().setTermLoanFirstDisbursementDate(termLoandate);
			
			Date buisnessCommenceDate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(clientDetails.getBasicInput().getBusinessCommencement()).getTime());
			clientDetails.getBasicInput().setBusinessCommencementDate(buisnessCommenceDate);
								
			clientService.createClient(clientDetails);
			
			return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
					ResultStatusConstants.STATUS_CREATE_CLIENT_SUCCESS,null);
			
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
	
	
	@RequestMapping(method=RequestMethod.GET, value ="getallclientsbycustomerid",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getAllClientsByCustomer(final @RequestParam("customerId") Long customerId) {
		try {
			
			System.out.println("======================================== 1"+customerId);
			
			List<Client> clientList = clientService.fetchByCustomerId(customerId);
			
			if(clientList!=null && clientList.size() > 0){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_CLIENT_DETAILS,null,clientList);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_USER_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS,null,null);
			}
		}
		 catch (APTException aptException) {
			
			return new Response(aptException);
		}
		 catch(Exception e){
			
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}
	
	@RequestMapping(method=RequestMethod.GET, value ="getclientcetailsbyid",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getClient(final @RequestParam("clientId") Long clientId) {
		try {
			
			System.out.println("======================================== 1"+clientId);
			
			Client clientDetails = clientService.findOne(clientId);
			
			if(clientDetails!=null){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_CLIENT_DETAILS,null,clientDetails);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_USER_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS,null,null);
			}
		}
		 catch (APTException aptException) {
			
			return new Response(aptException);
		}
		 catch(Exception e){
			
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}
	
}
