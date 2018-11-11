package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.PlanDetails;
import com.apt.msa.exception.APTException;

public interface IPlanDetailsService {

	public List<PlanDetails> fetchAllPlans() throws APTException;
	public PlanDetails fetchPlanById(Long plan_id) throws APTException;
}
