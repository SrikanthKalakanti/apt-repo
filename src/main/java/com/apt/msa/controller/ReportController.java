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

import com.apt.msa.business.CostOfProjectRow;
import com.apt.msa.business.CostOfProjectTable;
import com.apt.msa.entity.AssetInput;
import com.apt.msa.entity.BasicInput;
import com.apt.msa.entity.Client;
import com.apt.msa.entity.ClientGlobalInput;
import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.service.IBasicInputService;
import com.apt.msa.service.IClientService;
import com.apt.msa.service.IExpensesInputService;
import com.apt.msa.service.IGrowthInflationInputService;
import com.apt.msa.util.ExcelUtil;
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
	
	/**
	 * 1 Generate Report API
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="generate",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response generateReport(final RequestEntity<ClientRequest> requestEntity) {

		logger.info("---- Start of generateReport API-------");
		try {
			
			ClientGlobalInput clientGlobalInput = new ClientGlobalInput();
			
			
			
			/**
			 * get client details
			 */
			
			Client clientDetails = clientService.findOne(requestEntity.getBody().getClientId());
			
			if(clientDetails!=null){
				clientGlobalInput.setClient(clientDetails);
			}
			
			
			System.out.println(requestEntity.getBody().getClientId());

			BasicInput basicInput = basicInputService.findOne(requestEntity.getBody().getClientId());

			if(basicInput!=null){

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				basicInput.setTermLoanDisbursement(df.format(basicInput.getTermLoanFirstDisbursementDate()));
				basicInput.setBusinessCommencement(df.format(basicInput.getBusinessCommencementDate()));

				logger.info("---- retrieved basic input successfully-------");
				clientGlobalInput.setBasic(basicInput);
				/**
				 * *Need to write business logic for basic input
				 * 
				 * 
				 */

				
				
				
				/**
				 * Get assets for client
				 */

				List<AssetInput> assetInputList = assetService.fetchByClientId(requestEntity.getBody().getClientId());

				if(null!= assetInputList && assetInputList.size() >0){

					logger.info("---- getassetinputbyclient API success-------");
					clientGlobalInput.setAssetList(assetInputList);
					for (AssetInput asset: assetInputList){
						logger.info("asset: "+asset);
					}
				}

				/**
				 * get expenses for client
				 */
				
				List<ExpensesInput> expensesInputList = expensesService.fetchByClientId(requestEntity.getBody().getClientId());

				if(null!= expensesInputList && expensesInputList.size() >0){

					logger.info("---- getexpensesinputbyclient API success-------");
					clientGlobalInput.setExpensesList(expensesInputList);
					for (ExpensesInput expense: expensesInputList){
						logger.info("expense: "+expense);
					}
				}
				
				/**
				 * get growth and inflation for client
				 */
				
				GrowthInflationInput growthInput = growthService.findOne(requestEntity.getBody().getClientId());

				if(growthInput!=null){
					
					logger.info("---- getgrowthinputbyclient API success-------");
					clientGlobalInput.setGrowthInflation(growthInput);
				}
				
				logger.info("......client global input returned successfully...."+clientGlobalInput+
						"......asset list size...."+assetInputList.size());
				
				
				/**
				 * create a method in my logic
				 * 
				 */
				
				logger.info("......asset list size...."+assetInputList.size());
				CostOfProjectTable costOfProjectTable = new CostOfProjectTable(clientGlobalInput);
				costOfProjectTable.printCostOfProjectTable(costOfProjectTable);
				logger.info("......asset list size...."+CostOfProjectTable.columnHeaders);
				
				
				//Print above CostOfProjectTable related to Asset in Excel
				ExcelUtil.createAptReport(costOfProjectTable);
				
				
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_BASIC_DETAILS,null,clientGlobalInput);
			} else{
				logger.info("---- generateReport API no records-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_BASICINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOBASICINPUT_DETAILS,null);
			}
		}
		catch (APTException aptException) {

			logger.debug("---- generateReport API failed APTException -------" + aptException.getMessage());
			
			return new Response(aptException);
		}
		catch(Exception e){
			logger.debug("---- generateReport API failed Exception -------" + e.getMessage());
			logger.info("---- generateReport API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}


}
