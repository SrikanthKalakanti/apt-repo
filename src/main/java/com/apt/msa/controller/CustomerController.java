package com.apt.msa.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

import com.apt.msa.entity.Customer;
import com.apt.msa.entity.CustomerTransaction;
import com.apt.msa.entity.PlanDetails;
import com.apt.msa.exception.APTException;
import com.apt.msa.mail.Mail;
import com.apt.msa.mail.NotificationSender;
import com.apt.msa.request.CustomerPlanRequest;
import com.apt.msa.request.CustomerRequest;
import com.apt.msa.request.LoginRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.ICustomerService;
import com.apt.msa.service.IPlanDetailsService;
import com.apt.msa.util.CryptoUtil;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("customer")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IPlanDetailsService planDetailsService;
	
	@Autowired
	private NotificationSender mailSender;
	
	@Value("${registration.mail.success.message.subject}")
	private String registrationMailSuccessSubject;
	
	@Value("${registration.mail.success.message.content}")
	private String registrationMailSuccessContent;
	
	@Value("${registration.mail.success.message.from}")
	private String registrationMailFrom;	
	
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
			
			//send email for registered client
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
	
	
	@RequestMapping(method=RequestMethod.POST, value ="createtransaction",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response customerTransaction(RequestEntity<CustomerPlanRequest> requestEntity) {
		try {
			
			CustomerPlanRequest customerPlanRequest = requestEntity.getBody();
			
			if(customerPlanRequest!=null){
				
				PlanDetails planDetails = planDetailsService.fetchPlanById(customerPlanRequest.getPlanId());
				
				CustomerTransaction customerTransaction = new CustomerTransaction();
				customerTransaction.setPlanId(customerPlanRequest.getPlanId());
				customerTransaction.setCustomerId(customerPlanRequest.getCustomerId());
				
				Calendar cal = Calendar.getInstance();
		        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SS");
		        String purchaseDate = sdf.format(cal.getTime());				
				customerTransaction.setPurchase_date_time(purchaseDate);
				
				// add the plan validaty to the expiry date
				cal.add(Calendar.DATE, planDetails.getValidity());
				String validityDate = sdf.format(cal.getTime());
				customerTransaction.setValidaity_date_time(validityDate);
				customerTransaction.setNumber_of_reportsremaining(planDetails.getNo_of_reports());
				
				customerService.createTransaction(customerTransaction);
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_SUCCESS_CUSTOMER_TRANSACTION,null,customerTransaction.getId());
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.STATUS_FAIL_CUSTOMER_TRANSACTION,
						ResultStatusConstants.STATUS_FAIL_CUSTOMER_TRANSACTION,null,null);
			}
		}
		 catch (APTException aptException) {
			
			return new Response(aptException);
		}
		 catch(Exception e){
			 
			 logger.error(e.getMessage());
			
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null,null);
		}

	}
	
	@RequestMapping(method=RequestMethod.POST, value ="getcustomerPlandetails",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getCustomerplandetails(RequestEntity<CustomerRequest> requestEntity) {
		try {
			
			CustomerTransaction customerTransaction = customerService.findTransaction(requestEntity.getBody().getCustomerId());
			
			if(customerTransaction!=null){
				
			    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
			    Date purchaseDate = dateFormat.parse(customerTransaction.getPurchase_date_time());
			    Date validityDate = dateFormat.parse(customerTransaction.getValidaity_date_time());
			    
			    //Timestamp purchaseTimestamp = new java.sql.Timestamp(purchaseDate.getTime());
			    customerTransaction.setPurchaseTimestamp(purchaseDate.getTime());
			    
			    //Timestamp validityTimestamp = new java.sql.Timestamp(purchaseDate.getTime());
			    customerTransaction.setPurchaseTimestamp(validityDate.getTime());
				
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CUSTOMER_PLAN_EXIST,null,customerTransaction);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.STATUS_CUSTOMER_PLAN_NOT_EXIST,
						ResultStatusConstants.STATUS_CUSTOMER_PLAN_NOT_EXIST,null,null);
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
		
	@RequestMapping(method=RequestMethod.GET, value ="getlistofclientdetails",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getCustomer(final @RequestParam("customerId") Long customerId) {
		try {
			
			Customer customer = customerService.findOne(customerId);
			
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
