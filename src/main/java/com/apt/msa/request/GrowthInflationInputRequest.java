package com.apt.msa.request;

import java.io.Serializable;

import com.apt.msa.entity.GrowthInflationInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GrowthInflationInputRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("growthInflationInput")
	private GrowthInflationInput growthInflationInput;

	public GrowthInflationInput getGrowthInflationInput() {
		return growthInflationInput;
	}

	public void setGrowthInflationInput(GrowthInflationInput growthInflationInput) {
		this.growthInflationInput = growthInflationInput;
	}
	
}
