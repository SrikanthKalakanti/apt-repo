package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.entity.PlanDetails;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.AdminRespository;
import com.apt.msa.jpa.repository.PlanRespository;

@Service
public class PlanDetailsService implements IPlanDetailsService {

	@Autowired
	private PlanRespository planRepository;

	@Override
	public List<PlanDetails> fetchAllPlans() throws APTException {
		
		List<PlanDetails> plandetailsList = planRepository.findAll();
		
		return plandetailsList;
	}

	@Override
	public PlanDetails fetchPlanById(Long plan_id) throws APTException {

		PlanDetails plandetails = planRepository.findOne(plan_id);
		
		return plandetails;
	}
	
}
