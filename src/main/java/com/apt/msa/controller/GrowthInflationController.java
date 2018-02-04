package com.apt.msa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.request.GrowthInflationInputRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IGrowthInflationInputService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/growthinflation")
@CrossOrigin(origins = { "http://localhost:9000" })
public class GrowthInflationController {

	private static final Logger logger = LoggerFactory.getLogger(GrowthInflationController.class);

	@Autowired
	private IGrowthInflationInputService growthService;

	/**
	 * 1 Create Growth Input API 
	 * API which inserts the Growth Infaltion input details to the Mysql DB growth_inflation table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */

	@RequestMapping(method=RequestMethod.POST, value ="create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatGrowthInflationInput(RequestEntity<GrowthInflationInput> requestEntity) {
		
		logger.info("---- Start of creategrowthInflationinput API-------");
		try {

			GrowthInflationInput growthInput = requestEntity.getBody();

			if(null!= growthInput) {

				growthService.createGrowthInflationInput(growthInput);
				
				logger.info("---- creategrowthinput API successfull -------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_GROWTHINPUT_SUCCESS,null);

			} else {

				logger.info("---- creategrowthinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_GROWTHINPUT_FAILURE,null);
			}

		} catch (APTException aptException) {
			
			logger.debug("---- creategrowthinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			
			logger.debug("---- creategrowthinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	
	/**
	 * 2 Get GrowthInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getallbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getGrowthInputByCustomer(RequestEntity<ClientRequest> requestEntity) {
		
		logger.info("---- Start of getgrowthinputbyclient API-------");
		try {

			System.out.println(requestEntity.getBody().getClientId());

			GrowthInflationInput growthInput = growthService.findOne(requestEntity.getBody().getClientId());

			if(growthInput!=null){
				
				logger.info("---- getgrowthinputbyclient API success-------");
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_GROWTHINPUT_DETAILS,null,growthInput);
			} else{
				
				logger.info("---- getgrowthinputbyclient API no records-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.STATUS_NOGROWTHINFLATION_DETAILS,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS,null);
			}
		}
		catch (APTException aptException) {
			
			logger.debug("---- getgrowthinputbyclient API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		}
		catch(Exception e){
			
			logger.debug("---- getgrowthinputbyclient API failed Exception -------" + e.getMessage());

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}
	
	
	/**
	 * 3 Update API GrowthInput 
	 * API which Updates the Growth & Inflation input details to the Mysql DB growth_inflation_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="update",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateGrowthInput(RequestEntity<GrowthInflationInput> growthInputReq) {
		
		logger.info("---- Start of updategrowthinput API -------");
		try {

				if(null != growthInputReq.getBody()) {

					growthService.updateGrowthInput(growthInputReq.getBody());
					
					logger.info("---- updategrowthinput API success-------");
					
					return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
							ResultStatusConstants.STATUS_UPDATE_GROWTHINPUT_SUCCESS,null);

				} else {
					
					logger.info("---- updategrowthinput API Failed -------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_UPDATE_GROWTHINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- updategrowthinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			
			logger.debug("---- updategrowthinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
	/**
	 * 4 Delete API Growth & Inflation Input 
	 * API which Delete an Growth & Inflation input details from the Mysql DB growth_inflation_input table
	 * @param expensesInputReq
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="delete",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteGrowthInput(RequestEntity<GrowthInflationInput> growthInputReq) {
		
		logger.info("---- Start of deletegrowthinput API -------");
		try {

				if(null != growthInputReq.getBody()) {

					growthService.deleteGrowthInput(growthInputReq.getBody());
					
					logger.info("---- deletegrowthinput API success-------");
					
					return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
							ResultStatusConstants.STATUS_DELETE_GROWTHINPUT_SUCCESS,null);

				} else {
					
					logger.info("---- deletegrowthinput API Failed-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_DELETE_GROWTHINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			
			logger.debug("---- deletegrowthinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			
			logger.debug("---- deletegrowthinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

}
