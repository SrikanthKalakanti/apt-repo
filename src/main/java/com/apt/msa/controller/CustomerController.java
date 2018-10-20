package com.apt.msa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.entity.Customer;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.AdminRespository;
import com.apt.msa.mail.Mail;
import com.apt.msa.mail.NotificationSender;
import com.apt.msa.request.LoginRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAdminControlService;
import com.apt.msa.service.ICustomerService;
import com.apt.msa.util.CryptoUtil;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private NotificationSender mailSender;
	
	@Value("${registration.mail.success.message.subject}")
	private String registrationMailSuccessSubject;
	
	@Value("${registration.mail.success.message.content}")
	private String registrationMailSuccessContent;
	
	@Value("${registration.mail.success.message.from}")
	private String registrationMailFrom;
	
	
	
	
	@Autowired
	private IAdminControlService adminControlService;
	
	@Value("${customer.default.reports.amount}")
	private Long reportsAmout;
	
	@Value("${customer.default.report.noofreports}")
	private Integer noofReports;
	
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
		
		logger.info("Start of createCustomer API");
		try {
			Customer customer = requestEntity.getBody();
			logger.info(" createCustomer API Object created successfully");
			final String encryptPwd = CryptoUtil.encrypt(customer.getPassword());
			customer.setPassword(encryptPwd);
			
			logger.info(" createCustomer API Service start");
			customerService.createCustomer(customer);
			logger.info(" createCustomer API Successfull");
			
			//insert customer reports purchase to the admin control table
			AdminControl adminControl = new AdminControl();
			adminControl.setAmount(reportsAmout);
			adminControl.setNoofreportspurchased(noofReports);
			adminControl.setCustomerId(customer.getCustomerId());
			
			adminControlService.createAdminControl(adminControl);
			logger.info(" createCustomer API Insert default values to Admin Control Success");
			
			//send email for registered client
			
			//registrationMailSuccessSubject registrationMailSuccessContent registrationMailFrom
			Mail mail = new Mail();
	        mail.setFrom(registrationMailFrom);
	        mail.setTo(customer.getEmail());
	        mail.setSubject(registrationMailSuccessSubject);
	        mail.setContent("Hi "+customer.getFirstName() + ", "+registrationMailSuccessContent);
	        logger.info(" createCustomer API email sending....");
			
	        //MailSender mailSender = new MailSender();
			mailSender.sendSimpleMessage(mail);
			logger.info(" createCustomer API email sent successfully");
			
			
			
			return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,ResultStatusConstants.STATUS_CREATE_CUSTOMER_SUCCESS,null);
			
		} catch (APTException aptException) {
			
			logger.error("createCustomer API  APTexception"+ aptException.getMessage());
			return new Response(aptException);
		
		} catch(Exception e){
			logger.error("createCustomer API  Exception"+ e.getMessage());
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
	@RequestMapping(method=RequestMethod.GET, value ="getlistofclientdetails",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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
