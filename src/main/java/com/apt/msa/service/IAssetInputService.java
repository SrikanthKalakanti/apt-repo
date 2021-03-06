package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.AssetInput;
import com.apt.msa.exception.APTException;

public interface IAssetInputService {
	
	boolean createAssetInput(List<AssetInput> assetList) throws APTException;
	int updateAssetInput(AssetInput assetInput) throws APTException;
	
	List<AssetInput> fetchByClientId(Long clientId) throws APTException;
	boolean deleteAsset(AssetInput assetInput) throws APTException;
	boolean createAssetInput(AssetInput assetInput) throws APTException;
     
}
