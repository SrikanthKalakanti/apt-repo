package com.apt.msa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.Customer;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.LoginRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.ICustomerService;
import com.apt.msa.util.CryptoUtil;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService customerService;
	
	/**
	 * author srikanth
	 * 
	 * 
	 * @param requestEntity
	 * @return
	 */

	//@PostMapping(value ="register")
	@RequestMapping(method=RequestMethod.POST, value ="register",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response createCustomer(RequestEntity<Customer> requestEntity) {
		try {
			Customer customer = requestEntity.getBody();
			final String encryptPwd = CryptoUtil.encrypt(customer.getPassword());
			customer.setPassword(encryptPwd);
					
			customerService.createCustomer(customer);

			return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,ResultStatusConstants.STATUS_CREATE_CUSTOMER_SUCCESS,null);
			
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
	
	@RequestMapping(method=RequestMethod.POST, value ="login",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response login(RequestEntity<LoginRequest> requestEntity) {
		try {
			
			Customer customer = customerService.login(requestEntity.getBody().getUserName(), requestEntity.getBody().getPassword());
			
			if(customer!=null){
				
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_SUCCESS_LOGIN,null,customer.getCustomerId());
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_USER_NOT_EXISTS,
						ResultStatusConstants.ERROR_MESSAGE_USER_NOT_EXISTS,null,null);
			}
		}
		 catch (APTException aptException) {
			
			return new Response(aptException);
		}
		 catch(Exception e){
			 
			 e.printStackTrace();
			 logger.error(e.getMessage());
			
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null,null);
		}

	}
		
	//@GetMapping(value ="getcustomerdetails")
	@RequestMapping(method=RequestMethod.GET, value ="getcustomerdetails",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getCustomer(final @RequestParam("customerId") Long customerId) {
		try {
			
			System.out.println("======================================== 1"+customerId);
			
			Customer customer = customerService.findOne(customerId);
			System.out.println("======================================== 2");
			
			if(customer!=null){
				customer.setPassword("");
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_CUSTOMER_DETAILS,null,customer);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_USER_NOT_EXISTS,
						ResultStatusConstants.ERROR_MESSAGE_USER_NOT_EXISTS,null,null);
			}
		}
		 catch (APTException aptException) {
			
			return new Response(aptException);
		}
		 catch(Exception e){
			
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null,null);
		}

	}
	
}
