package com.apt.msa.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.AssetInputRequest;
import com.apt.msa.request.BasicInputRequest;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.request.ExpensesInputRequest;
import com.apt.msa.request.GrowthInflationInputRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.service.IBasicInputService;
import com.apt.msa.service.IExpensesInputService;
import com.apt.msa.service.IGrowthInflationInputService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("apt")
@CrossOrigin(origins = { "http://localhost:9000" })
public class APTController {

	private static final Logger logger = LoggerFactory.getLogger(APTController.class);

	@Autowired
	private IBasicInputService basicInputService;

	@Autowired
	private IExpensesInputService expensesService;

	@Autowired
	private IGrowthInflationInputService growthService;

	@Autowired
	private IAssetInputService assetService;

	/**
	 * 1 Create API AssetInput 
	 * API which inserts the Asset input details to the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="createassetinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatAssetInput(RequestEntity<AssetInputRequest> assetInputReq) {
		try {

			if(null != assetInputReq ) {

				List<AssetInput> assetInputList = assetInputReq.getBody().getAssetInputList();

				if(null != assetInputList && assetInputList.size() > 0) {

					assetService.createAssetInput(assetInputList);

				}

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_SUCCESS,null);


			} else {

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_FAILURE,null);

			}


		} catch (APTException aptException) {
			return new Response(aptException);

		} catch(Exception e) {
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	/**
	 * 2 BasicInput Create API
	 * API which inserts the Basic input details to the Mysql DB basic_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */	
	@RequestMapping(method=RequestMethod.POST, value ="createbasicinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatBasicInput(RequestEntity<BasicInputRequest> requestEntity) {
		try {

			BasicInput basicInput = requestEntity.getBody().getBasicInput();

			if(null!= basicInput){

				Date termLoandate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(basicInput.getTermLoanDisbursement()).getTime());
				basicInput.setTermLoanFirstDisbursementDate(termLoandate);

				Date buisnessCommenceDate = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(basicInput.getBusinessCommencement()).getTime());
				basicInput.setBusinessCommencementDate(buisnessCommenceDate);

				basicInputService.createBasicInput(basicInput);

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_BASICINPUT_SUCCESS,null);

			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_BASICINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			return new Response(aptException);

		} catch(Exception e) {
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


	/**
	 * 3 Create Growth Input API 
	 * API which inserts the Growth Infaltion input details to the Mysql DB growth_inflation table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */

	@RequestMapping(method=RequestMethod.POST, value ="creategrowthinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatGrowthInflationInput(RequestEntity<GrowthInflationInputRequest> requestEntity) {
		try {

			GrowthInflationInput growthInput = requestEntity.getBody().getGrowthInflationInput();

			if(null!= growthInput) {

				growthService.createGrowthInflationInput(growthInput);

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_GROWTHINPUT_SUCCESS,null);

			} else {

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_GROWTHINPUT_FAILURE,null);
			}

		} catch (APTException aptException) {
			return new Response(aptException);

		} catch(Exception e) {
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	/**
	 * 4 Create Expenses Input API
	 * API which creates  the Expenses Input  details to the Mysql DB expenses_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="createexpensesinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatExpensesInput(RequestEntity<ExpensesInputRequest> requestEntity) {
		try {

			List<ExpensesInput> expensesInputList = requestEntity.getBody().getExpensesInputList();

			if(null!= expensesInputList && expensesInputList.size() > 0) {

				expensesService.createExpensesInput(expensesInputList);
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_SUCCESS,null);

			} else {
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_EXPENSESINPUT_FAILURE,null);
			}

		} catch (APTException aptException) {
			return new Response(aptException);

		} catch(Exception e) {
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	/**
	 * 5 Get AssetInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getassetinputbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getAssetInputByClientId(final RequestEntity<ClientRequest> requestEntity) {
		try {

			System.out.println(requestEntity.getBody().getClientId());

			List<AssetInput> assetInputList = assetService.fetchByClientId(requestEntity.getBody().getClientId());

			if(null!= assetInputList && assetInputList.size() >0){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_ASSETINPUT_DETAILS,null,assetInputList);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_ASSETINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOASSETINPUT_DETAILS,null);
			}
		}
		/*catch (APTException aptException) {

			return new Response(aptException);
		}*/
		catch(Exception e){

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}


	/**
	 * 6 Get BasicInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getbasicinputbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getBasicInputByClientId(final RequestEntity<ClientRequest> requestEntity) {
		try {

			System.out.println(requestEntity.getBody().getClientId());

			BasicInput basicInput = basicInputService.findOne(requestEntity.getBody().getClientId());

			if(basicInput!=null){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_BASIC_DETAILS,null,basicInput);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_BASICINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOBASICINPUT_DETAILS,null);
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

	/**
	 * 7 Get GrowthInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getgrowthinputbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getGrowthInputByCustomer(RequestEntity<ClientRequest> requestEntity) {
		try {

			System.out.println(requestEntity.getBody().getClientId());

			GrowthInflationInput growthInput = growthService.findOne(requestEntity.getBody().getClientId());

			if(growthInput!=null){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_GROWTHINPUT_DETAILS,null,growthInput);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.STATUS_NOGROWTHINFLATION_DETAILS,
						ResultStatusConstants.STATUS_NOCLIENT_DETAILS,null);
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


	/**
	 * 8 Get Expenses Input By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getexpensesinputbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getExpensesInputByClientId(final RequestEntity<ClientRequest> requestEntity) {
		try {

			System.out.println(requestEntity.getBody().getClientId());

			List<ExpensesInput> expensesInputList = expensesService.fetchByClientId(requestEntity.getBody().getClientId());

			if(null!= expensesInputList && expensesInputList.size() >0){
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_EXPENSESINPUT_DETAILS,null,expensesInputList);
			} else{
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_USER_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOEXPENSES_DETAILS,null);
			}
		}
		/*catch (APTException aptException) {

			return new Response(aptException);
		}*/
		catch(Exception e){

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,null);
		}

	}
	
	
	/**
	 * 9 Update API AssetInput 
	 * API which Updates the Asset input details to the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="updateassetinput",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateAssetInput(RequestEntity<AssetInput> assetInputReq) {
		try {


				//AssetInput assetInput = assetInputReq.getBody().getAssetInputList();

				if(null != assetInputReq.getBody()) {

					assetService.updateAssetInput(assetInputReq.getBody());
					
					return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
							ResultStatusConstants.STATUS_UPDATE_ASSETINPUT_SUCCESS,null);

				} else {

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_UPDATE_ASSETINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			return new Response(aptException);

		} catch(Exception e) {
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

}
