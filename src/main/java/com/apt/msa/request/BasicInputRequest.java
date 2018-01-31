package com.apt.msa.request;

import java.io.Serializable;

import com.apt.msa.entity.BasicInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BasicInputRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("basicInput")
	private BasicInput basicInput;
	
	public BasicInput getBasicInput() {
		return basicInput;
	}

	public void setBasicInput(BasicInput basicInput) {
		this.basicInput = basicInput;
	}
	
}
