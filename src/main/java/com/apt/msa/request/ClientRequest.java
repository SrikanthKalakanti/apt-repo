package com.apt.msa.request;

import java.io.Serializable;

public class ClientRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long clientId;

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	
}
