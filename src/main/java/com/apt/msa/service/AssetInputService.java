package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.AssetInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.AssetInputRespository;

@Service
public class AssetInputService implements IAssetInputService {

	@Autowired
	private AssetInputRespository assetRepository;
	
	@Override
	public boolean createAssetInput(List<AssetInput> assetList) throws APTException {
		
		boolean flag = false;
		assetRepository.save(assetList);
		flag = true;
		
		return flag;
	}
	
	@Override
	public boolean updateAssetInput(AssetInput assetInput) throws APTException {
		
		boolean flag = false;
		assetRepository.save(assetInput);
		flag = true;
		
		return flag;
	}
	
	@Override
	public List<AssetInput> fetchByClientId(Long clientId) throws APTException {
		List<AssetInput> assetInputList = assetRepository.fetchByClientId(clientId);
		return assetInputList;
	}

}
