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
import com.apt.msa.entity.PlanDetails;
import com.apt.msa.exception.APTException;
import com.apt.msa.request.AssetInputRequest;
import com.apt.msa.request.ClientRequest;
import com.apt.msa.response.Response;
import com.apt.msa.service.IAssetInputService;
import com.apt.msa.service.IPlanDetailsService;
import com.apt.msa.util.ResultStatusConstants;

@RestController
@RequestMapping("plandetails")
@CrossOrigin(origins = { "http://localhost:9000" })
public class PlanController {

	private static final Logger logger = LoggerFactory.getLogger(PlanController.class);

	@Autowired
	private IPlanDetailsService planService;
	
	
	/**
	 * 1 Get All Plan Details
	 * @author SrikanthKalakanti
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value ="getallplans", produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = { "http://localhost:9000" })
	public Response getAllPlans() {
		try {

			logger.info("---- Start of getAllPlans API-------");

			List<PlanDetails> plansList = planService.fetchAllPlans();

			if(null!= plansList && plansList.size() >0){
				
				logger.info("---- getAllPlans API success-------");
				
				return new Response(ResultStatusConstants.STATUS_OK,ResultStatusConstants.SUCCESS_CODE,
						ResultStatusConstants.STATUS_RETRIEVED_PLAN_DETAILS,null,plansList);
			} else{
				
				logger.info("---- getAllPlans API no PlanDetails Exist -------");
				return new Response(ResultStatusConstants.STATUS_FAIL,
						ResultStatusConstants.ERROR_CODE_PLANS_NOT_EXISTS,
						ResultStatusConstants.STATUS_NOPLAN_DETAILS,null);
			}
		} catch (APTException aptException) {
			logger.debug("---- STATUS_NOPLAN_DETAILS API failed APTException -------" + aptException.getMessage());

			return new Response(aptException);
		}
		catch(Exception e){
			
			logger.error("Unkown Exception getAllPlans "+e.getMessage());

			return new Response(
					ResultStatusConstants.STATUS_FAIL,
					ResultStatusConstants.ERROR_CODE_UNKNOWN_ERROR,
					e.getMessage(),
					ResultStatusConstants.ERROR_MESSAGE_UNKNOWN_EXCPETION);
		}

	}
	
}
