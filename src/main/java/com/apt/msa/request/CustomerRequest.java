package com.apt.msa.request;

import java.io.Serializable;

public class CustomerRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long customerId;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	} 
}
