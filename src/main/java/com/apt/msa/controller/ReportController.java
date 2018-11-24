package com.apt.msa.controller;

import java.text.DateFormat;
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
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.AssetInput;
import com.apt.msa.entity.BasicInput;
import com.apt.msa.entity.Client;
import com.apt.msa.entity.ClientGlobalInput;
import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.entity.ReportAudit;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAdminControlService;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.service.IBasicInputService;
import com.apt.msa.service.IClientService;
import com.apt.msa.service.ICustomerTransactionService;
import com.apt.msa.service.IExpensesInputService;
import com.apt.msa.service.IGrowthInflationInputService;
import com.apt.msa.service.IReportControlService;
//import com.apt.msa.service.IReportControlService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/report")
@CrossOrigin(origins = { "http://localhost:9000" })
public class ReportController {

	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private IClientService clientService;

	@Autowired
	private IBasicInputService basicInputService;

	@Autowired
	private IAssetInputService assetService;

	@Autowired
	private IExpensesInputService expensesService;

	@Autowired
	private IGrowthInflationInputService growthService;

	@Autowired
	private IAdminControlService adminControlService;
	
	@Autowired
	private IReportControlService reportControlService;
	
	/**
	 * 1 Generate Report API
	 * 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "generate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response generateReport(final RequestEntity<ClientRequest> requestEntity) {

		logger.info("---- Start of generateReport API-------");
		try {

			ClientGlobalInput clientGlobalInput = new ClientGlobalInput();

			/**
			 * get client details
			 */

			Client clientDetails = clientService.findOne(requestEntity.getBody().getClientId());

			if (clientDetails != null) {
				clientGlobalInput.setClient(clientDetails);
			}else{

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS, null);
			}

			logger.info("Generate Report of Customer Id:"+clientDetails.getCustomerId()+ " for Client Id: "+requestEntity.getBody().getClientId());

			BasicInput basicInput = basicInputService.fetchByClientId(requestEntity.getBody().getClientId());

			if (basicInput != null) {

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				//basicInput.setTermLoanDisbursement(df.format(basicInput.getTermLoanFirstDisbursementDate()));
				basicInput.setBusinessCommencement(df.format(basicInput.getBusinessCommencementDate()));

				logger.info("---- retrieved basic input successfully-------");
				clientGlobalInput.setBasic(basicInput);

			} else {
				logger.info("---- generateReport API no records-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_BASICINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOBASICINPUT_DETAILS, null);
			}	

			/**
			 * Get assets for client
			 */

			List<AssetInput> assetInputList = assetService.fetchByClientId(requestEntity.getBody().getClientId());

			if (null != assetInputList && assetInputList.size() > 0) {

				logger.info("---- AssetInput details exist for the client -------");
				logger.info("......asset list size...." + assetInputList.size());
				clientGlobalInput.setAssetList(assetInputList);
			}else{

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_ASSETINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOASSETINPUT_DETAILS, null);
			}

			/**
			 * get expenses for client
			 */

			List<ExpensesInput> expensesInputList = expensesService
					.fetchByClientId(requestEntity.getBody().getClientId());

			if (null != expensesInputList && expensesInputList.size() > 0) {

				logger.info("---- ExpensesInput details exist for the client-------");
				clientGlobalInput.setExpensesList(expensesInputList);
			}else{

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_EXPENSES_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOEXPENSES_DETAILS, null);
			}

			/**
			 * get growth and inflation for client
			 */

			GrowthInflationInput growthInput = growthService.fetchByClientId(requestEntity.getBody().getClientId());

			if (growthInput != null) {

				logger.info("---- GrowthInflation details exist for the client-------");
				clientGlobalInput.setGrowthInflation(growthInput);
			} else{

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_GROWTHINFLATION_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOGROWTHINFLATION_DETAILS, null);
			}

			/**
			 * create a method in my logic
			 * 
			 */

			logger.info("Before downloading the report");
			//ExcelUtil.createAptReport(clientGlobalInput);
			logger.info("Report Successfully downloaded for customer :"+clientDetails.getCustomerId() + " for client:"+clientDetails.getClientId());
		
			ReportAudit reportAudit = new ReportAudit();
			
			reportAudit.setClientId(clientDetails.getClientId());
			reportAudit.setCustomerId(clientDetails.getCustomerId());
			reportControlService.createReportsAudit(reportAudit);
			
			return new Response(ResultStatusConstants.STATUS_OK, ResultStatusConstants.SUCCESS_CODE,
					ResultStatusConstants.STATUS_RETRIEVED_BASIC_DETAILS, null, clientGlobalInput);

		} catch (APTException aptException) {

			logger.debug("---- generateReport API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		} catch (Exception e) {
			logger.debug("---- generateReport API failed Exception -------" + e.getMessage());
			logger.info("---- generateReport API failed Exception -------" + e.getMessage());
			return new Response(ResultStatusConstants.STATUS_FAIL, ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR, null);
		}

	}
	
	/**
	 * 1 Get Report Count and Disable edit option in the API
	 * 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "editaptinputdetailscheck", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response editAptInputdetailsCheck(final RequestEntity<ClientRequest> requestEntity) {
		
		logger.info("Start of editAptInputdetailsCheck API");	
	try{
		
		List<ReportAudit> reportAuditList = reportControlService.fetchReportAuditByClientId(requestEntity.getBody().getClientId());
		logger.info(" Reports list for the client retrieved");
		
		if(null != reportAuditList && reportAuditList.size() > 0){
		return new Response(ResultStatusConstants.STATUS_OK, ResultStatusConstants.SUCCESS_CODE,
				ResultStatusConstants.STATUS_EDIT_CLIENT_DISABLED, null, false);
		} else {
			return new Response(ResultStatusConstants.STATUS_OK, ResultStatusConstants.SUCCESS_CODE,
					ResultStatusConstants.STATUS_EDIT_CLIENT_ENABLED, null, true);
		}
	} catch (Exception e) {
		logger.debug("---- generateReport API failed Exception -------" + e.getMessage());
		logger.info("---- generateReport API failed Exception -------" + e.getMessage());
		return new Response(ResultStatusConstants.STATUS_FAIL, ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
				ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR, null);
	}
	}

}
