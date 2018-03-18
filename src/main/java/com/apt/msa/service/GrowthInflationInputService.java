package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.GrowthInflationInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.GrowthInflationInputRespository;

@Service
public class GrowthInflationInputService implements IGrowthInflationInputService {

	@Autowired
	private GrowthInflationInputRespository growthInflationRepository;
	
	@Override
	public boolean createGrowthInflationInput(GrowthInflationInput growthInflationInput) throws APTException {
		boolean flag = false;
		growthInflationRepository.save(growthInflationInput);
		flag =true;
		return flag;
	}

	@Override
	public GrowthInflationInput findOne(Long clientId) throws APTException {
		GrowthInflationInput growthInflation = growthInflationRepository.findOne(clientId);
		return growthInflation;
	}

	@Override
	public int updateGrowthInput(GrowthInflationInput growthInflationInput) throws APTException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteGrowthInput(GrowthInflationInput growthInflationInput) throws APTException {
		growthInflationRepository.deleteAssetByIdAndClient(growthInflationInput.getClientId(), growthInflationInput.getGrowthIflationId());
		return 0;
	}
	@Override
	public GrowthInflationInput fetchByClientId(Long clientId) throws APTException {
		GrowthInflationInput growthInflation = growthInflationRepository.fetchByClientId(clientId);
		return growthInflation;
	}

}
