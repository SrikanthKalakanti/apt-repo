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

import com.apt.msa.entity.AssetInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.AssetInputRequest;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("client/asset")
@CrossOrigin(origins = { "http://localhost:9000" })
public class AssetController {

	private static final Logger logger = LoggerFactory.getLogger(AssetController.class);

	@Autowired
	private IAssetInputService assetService;
	
	
	/**
	 * 1 AssetInput Create API
	 * API which inserts the Asset input details to the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */	
	@RequestMapping(method=RequestMethod.POST, value ="create",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatAssetInput(RequestEntity<AssetInput> requestEntity) {

		logger.info("---- Start of create assetinput API-------");

		try {

			AssetInput assetInput = requestEntity.getBody();

			if(null!= assetInput){

				assetService.createAssetInput(assetInput);

				logger.info("---- create assetinput API successfull -------");

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_SUCCESS,null);

			} else{

				logger.info("---- create assetinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- create assetinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- create assetinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}

	
	/**
	 * 2 Get AssetInput By Client Id 
	 * @author SrikanthKalakanti
	 * @param requestEntity
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="getallbyclient",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getAssetInputByClientId(final RequestEntity<ClientRequest> requestEntity) {
		try {

			logger.info("---- Start of getassetinputbyclient API-------");
			System.out.println(requestEntity.getBody().getClientId());

			List<AssetInput> assetInputList = assetService.fetchByClientId(requestEntity.getBody().getClientId());

			if(null!= assetInputList && assetInputList.size() >0){
				
				logger.info("---- getassetinputbyclient API success-------");
				
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_ASSETINPUT_DETAILS,null,assetInputList);
			} else{
				
				logger.info("---- getassetinputbyclient API no Assets defined-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_ASSETINPUT_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOASSETINPUT_DETAILS,null);
			}
		} catch (APTException aptException) {
			logger.debug("---- createassetinput API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		}
		catch(Exception e){

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
	
	/**
	 * 3 Update API AssetInput 
	 * API which Updates the Asset input details to the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="update",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response updateAssetInput(RequestEntity<AssetInput> assetInputReq) {
		try {

				if(null != assetInputReq.getBody()) {

					assetService.updateAssetInput(assetInputReq.getBody());
					
					logger.info("---- updateassetinput API success -------");
					
					return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
							ResultStatusConstants.STATUS_UPDATE_ASSETINPUT_SUCCESS,null);

				} else {
					logger.info("---- updateassetinput API failed -------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_UPDATE_ASSETINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- updateassetinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- updateassetinput API failed APTException -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
	
	/**
	 * 4 Delete API AssetInput 
	 * API which Delete an Asset input details from the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="delete",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response deleteAssetInput(RequestEntity<AssetInput> assetInputReq) {
		try {

				if(null != assetInputReq.getBody()) {

					assetService.deleteAsset(assetInputReq.getBody());
					
					logger.info("---- deleteasset API success-------");
					
					return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
							ResultStatusConstants.STATUS_DELETE_ASSETINPUT_SUCCESS,null);

				} else {
					
					logger.info("---- deleteasset API failed-------");

				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_DELETE_ASSETINPUT_FAILURE,null);

			}

		} catch (APTException aptException) {
			logger.debug("---- deleteasset API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- deleteasset API failed APTException -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
	/**
	 * 5 Create API All AssetInputs 
	 * API which inserts the Asset input details to the Mysql DB asset_input table
	 * @param requestEntity
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value ="createAll",consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response creatAllAssetInput(RequestEntity<AssetInputRequest> assetInputReq) {
		
		logger.info("---- Start of create all assetinput API-------");
		
		try {

			if(null != assetInputReq ) {

				List<AssetInput> assetInputList = assetInputReq.getBody().getAssetInputList();

				if(null != assetInputList && assetInputList.size() > 0) {

					assetService.createAssetInput(assetInputList);
					
					logger.info("---- create all assetinput API successfull -------");

				}

				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_SUCCESS,null);


			} else {
				logger.info("---- create all assetinput API insertion failed-------");
				return new Response(ResultStatusConstants.STATUS_FAIL,ResultStatusConstants.FAIL_CODE,
						ResultStatusConstants.STATUS_CREATE_ASSETINPUT_FAILURE,null);

			}


		} catch (APTException aptException) {
			logger.debug("---- create all assetinput API failed APTException -------" + aptException.getMessage());
			return new Response(aptException);

		} catch(Exception e) {
			logger.debug("---- create all assetinput API failed Exception -------" + e.getMessage());
			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_ERROR,
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
}
