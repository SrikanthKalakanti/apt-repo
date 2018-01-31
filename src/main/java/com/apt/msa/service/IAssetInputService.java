package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.AssetInput;
import com.apt.msa.exception.APTException;

public interface IAssetInputService {
	
	boolean createAssetInput(List<AssetInput> assetList) throws APTException;
	
	//List<AssetInput> fetchByClientId(Long clientId) throws APTException;
     
}
