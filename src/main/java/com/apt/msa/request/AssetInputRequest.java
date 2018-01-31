package com.apt.msa.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.apt.msa.entity.AssetInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssetInputRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("assetInput")
	private List<AssetInput> assetInputList = new ArrayList<AssetInput>();

	public List<AssetInput> getAssetInputList() {
		return assetInputList;
	}

	public void setAssetInputList(List<AssetInput> assetInputList) {
		this.assetInputList = assetInputList;
	}

	
}
