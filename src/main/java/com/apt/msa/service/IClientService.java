package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.Client;
import com.apt.msa.exception.APTException;

public interface IClientService {
	
     Client createClient(Client client) throws APTException;
     Client findOne(Long clientId) throws APTException;
     List<Client> fetchByCustomerId(Long customerId) throws APTException;
}
