package com.apt.msa.service;

import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;

public interface IGrowthInflationInputService {
	
	boolean createGrowthInflationInput(GrowthInflationInput growthInflationInput) throws APTException;
	public GrowthInflationInput findOne(Long clientId) throws APTException; 
}
