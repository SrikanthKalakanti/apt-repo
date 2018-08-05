package com.apt.msa.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.BasicInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IBasicInputService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/basicinput")
@CrossOrigin(origins = { "http://localhost:9000" })
public class BasicController {

	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

	@Autowired
	private IBasicInputService basicInputService;


	/**
	 * 1 BasicInput Create API
	 * API which inserts the Basic input details to the Mysql DB basic_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */	
	@RequestMapping(method=RequestMethod.POST, value ="create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatBasicInput(RequestEntity<BasicInput> requestEntity) {

		logger.info("---- Start of createbasicinput API-------");

		try {

			BasicInput basicInput = requestEntity.getBody();

			if(null!= basicInput){

				Date buisnessCommenceDate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(basicInput.getBusinessCommencement()).getTime());
				basicInput.setBusinessCommencementDate(buisnessCommenceDate);

				basicInputService.createBasicInput(basicInput);

				logger.info("---- createbasicinput API successfull -------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_BASICINPUT_SUCCESS,null);

			} else{

				logger.info("---- createbasicinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_BASICINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- createbasicinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- createbasicinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


	/**
	 * 2 Get BasicInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getallbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getBasicInputByClientId(final RequestEntity<ClientRequest> requestEntity) {

		logger.info("---- Start of getbasicinputbyclient API-------");
		try {

			BasicInput basicInput = basicInputService.findOne(requestEntity.getBody().getClientId());

			if(basicInput!=null){

				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

				basicInput.setBusinessCommencement(df.format(basicInput.getBusinessCommencementDate()));

				logger.info("---- getbasicinputbyclient API success-------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_BASIC_DETAILS,null,basicInput);
			} else{
				logger.info("---- getbasicinputbyclient API no records-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_BASICINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOBASICINPUT_DETAILS,null);
			}
		}
		catch (APTException aptException) {

			logger.debug("---- getbasicinputbyclient API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		}
		catch(Exception e){
			logger.debug("---- getbasicinputbyclient API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}


	/**
	 * 3 Update API BasicInput 
	 * API which Updates the Basic input details to the Mysql DB basic_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="update",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateBasicInput(RequestEntity<BasicInput> basicInputReq) {

		logger.info("---- Start of updateBasicInput API -------");
		try {

			if(null != basicInputReq.getBody()) {

				Date buisnessCommenceDate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(basicInputReq.getBody().getBusinessCommencement()).getTime());
				basicInputReq.getBody().setBusinessCommencementDate(buisnessCommenceDate);

				basicInputService.updateBasicInput(basicInputReq.getBody());
				logger.info("---- updatebasicinput API success-------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_UPDATE_BASICINPUT_SUCCESS,null);

			} else {

				logger.info("---- updatebasicinput API failed-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_UPDATE_BASICINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- updatebasicinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- updatebasicinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


	/**
	 * 4 Delete API BasicInput 
	 * API which Delete an Basic input details from the Mysql DB basic_input table
	 * @param assetInputReq
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="delete",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteBasicInput(RequestEntity<BasicInput> basicInputReq) {

		logger.info("---- Start of delete basicinput API -------");
		try {

			if(null != basicInputReq.getBody()) {

				basicInputService.deleteBasicInput(basicInputReq.getBody());

				logger.info("---- deletebasicinput API success-------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_DELETE_BASICINPUT_SUCCESS,null);

			} else {
				
				logger.info("---- deletebasicinput API Failed-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_DELETE_BASICINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- deletebasicinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- deletebasicinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


}
