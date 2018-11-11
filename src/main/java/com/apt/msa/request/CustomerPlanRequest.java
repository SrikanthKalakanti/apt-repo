package com.apt.msa.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.apt.msa.entity.AssetInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerPlanRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("customerId")
	private Long customerId;
	
	@JsonProperty("planId")
	private Long planId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	
}
