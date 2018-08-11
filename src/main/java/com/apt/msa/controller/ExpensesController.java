package com.apt.msa.controller;

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

import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.request.ExpensesInputRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IExpensesInputService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/expenses")
@CrossOrigin(origins = { "http://localhost:9000" })
public class ExpensesController {

	private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);

	@Autowired
	private IExpensesInputService expensesService;

	/**
	 * 1 Create Expenses Input API
	 * API which creates  the Expenses Input  details to the Mysql DB expenses_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return

	 */		 
	@RequestMapping(method=RequestMethod.POST, value ="create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatExpensesInput(RequestEntity<ExpensesInput> requestEntity) {

		logger.info("---- Start of create expensesinput API-------");

		try {

			ExpensesInput expensesInput = requestEntity.getBody();

			if(null!= expensesInput){

				expensesService.createExpensesInput(expensesInput);

				logger.info("---- create expensesinput API successfull -------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_SUCCESS,null);

			} else{

				logger.info("---- create expensesinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- create expensesinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- create expensesinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


	/**
	 * 2 Get Expenses Input By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getallbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getExpensesInputByClientId(final RequestEntity<ClientRequest> requestEntity) {
		logger.info("---- Start of getexpensesinputbyclient API-------");
		try {

			System.out.println(requestEntity.getBody().getClientId());

			List<ExpensesInput> expensesInputList = expensesService.fetchByClientId(requestEntity.getBody().getClientId());

			if(null!= expensesInputList && expensesInputList.size() >0){

				logger.info("---- getexpensesinputbyclient API success-------");
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_EXPENSESINPUT_DETAILS,null,expensesInputList);
			} else{

				logger.info("---- getexpensesinputbyclient API no records-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_EXPENSES_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOEXPENSES_DETAILS,null);
			}

		} catch (APTException aptException) {

			logger.debug("---- getexpensesinputbyclient API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);

		} catch(Exception e){

			logger.debug("---- getexpensesinputbyclient API failed Exception -------" + e.getMessage());

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}


	/**
	 * 3 Update API ExpensesInput 
	 * API which Updates the Expenses input details to the Mysql DB expenses_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="update",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateExpensesInput(RequestEntity<ExpensesInput> expensesInputReq) {

		logger.info("---- Start of update expensesinput API -------");
		try {

			if(null != expensesInputReq.getBody()) {

				expensesService.updateExpensesInput(expensesInputReq.getBody());

				logger.info("---- updateexpensesinput API success-------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_UPDATE_EXPENSESINPUT_SUCCESS,null);

			} else {

				logger.info("---- updateexpensesinput API failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_UPDATE_EXPENSESINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- updateexpensesinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- updateexpensesinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	/**
	 * 4 Delete API ExpensesInput 
	 * API which Delete an Expenses input details from the Mysql DB expenses_input table
	 * @param expensesInputReq
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="delete",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteExpensesInput(RequestEntity<ExpensesInput> expesnseInputReq) {

		logger.info("---- Start of deleteexpesesinput API -------");
		try {

			if(null != expesnseInputReq.getBody()) {

				expensesService.deleteExpensesInput(expesnseInputReq.getBody());

				logger.info("---- deleteexpesesinput API success-------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_DELETE_EXPENSESINPUT_SUCCESS,null);

			} else {

				logger.info("---- deleteexpesesinput API Failed-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_DELETE_EXPENSESINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {

			logger.debug("---- deleteexpesesinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {

			logger.debug("---- deleteexpesesinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	/**
	 * 5 Create All Expenses Input API
	 * API which creates  the Expenses Input  details to the Mysql DB expenses_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="createall",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatAllExpensesInput(RequestEntity<ExpensesInputRequest> requestEntity) {

		logger.info("---- Start of createexpensesinput API-------");
		try {

			List<ExpensesInput> expensesInputList = requestEntity.getBody().getExpensesInputList();

			if(null!= expensesInputList && expensesInputList.size() > 0) {

				expensesService.createExpensesInput(expensesInputList);
				logger.info("---- createexpensesinput API successfull -------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_SUCCESS,null);

			} else {

				logger.info("---- createexpensesinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_FAILURE,null);
			}

		} catch (APTException aptException) {

			logger.debug("---- createexpensesinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {

			logger.debug("---- createexpensesinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
	/**
	 * 6 Get Expenses Input By Client Id and CMA Nomenclature "SELLING PRICE"
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getallbyclientsellingprice",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getExpensesInputByClientSellingPrice(final RequestEntity<ClientRequest> requestEntity) {
		logger.info("---- Start of getallbyclientsellingprice API-------");
		try {

			System.out.println(requestEntity.getBody().getClientId());

			ExpensesInput expensesInput = expensesService.fetchByClientSellingPrice(requestEntity.getBody().getClientId());

			if(null!= expensesInput ){

				logger.info("---- getallbyclientsellingprice API success-------");
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_EXPENSESINPUT_DETAILS,null,expensesInput);
			} else{

				logger.info("---- getallbyclientsellingprice API no records-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_EXPENSES_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOSELLINGPRICE_DETAILS,null);
			}

		} catch (APTException aptException) {

			logger.debug("---- getallbyclientsellingprice API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);

		} catch(Exception e){

			logger.debug("---- getallbyclientsellingprice API failed Exception -------" + e.getMessage());

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}

}
