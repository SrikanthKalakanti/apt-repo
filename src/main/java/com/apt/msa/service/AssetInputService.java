package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
	public int updateAssetInput(AssetInput assetInput) throws APTException {

		int result  = assetRepository.updateAssetByClient(assetInput.getName(), assetInput.getValue(), assetInput.getDepreciationRate(), 
															assetInput.getPromoterMargin(), assetInput.getClientId(), assetInput.getAssetId() );
		
		return result;
	}
	
	@Override
	public List<AssetInput> fetchByClientId(Long clientId) throws APTException {
		List<AssetInput> assetInputList = assetRepository.fetchByClientId(clientId);
		return assetInputList;
	}

	@Override
	public boolean deleteAsset(AssetInput assetInput) throws APTException {
		
		assetRepository.deleteAssetByIdAndClient(assetInput.getClientId(), assetInput.getAssetId());
		return false;
	}

	@Override
	public boolean createAssetInput(AssetInput assetInput) throws APTException {
		boolean flag = false;
		assetRepository.save(assetInput);
		flag = true;
		
		return flag;
	}

}
