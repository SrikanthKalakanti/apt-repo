package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.Client;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.ClientRespository;

@Service
public class ClientService implements IClientService {

	@Autowired
	private ClientRespository clientRepository;
	
	/**
	 * insert client details to DB
	 */
	public Client createClient(Client client) throws APTException {

		return clientRepository.save(client);

	}
	
	/**
	 * Update client details to DB
	 */
	public int updateClient(Client client) throws APTException {

		return clientRepository.updateClient(client.getClientId(), client.getDateoffirstditributionoftermloan(), 
				client.getDoorNo(), client.getEmail(), client.getLineofactivity(), client.getLandmark(), 
				client.getLandphone(), client.getLocality(), client.getMobile(), client.getName(), client.getNamePrefix(), 
				client.getPincode(), client.getRoad(), client.getState(), client.getStatus(), client.getTown());

	}
	
	@Override
	public Client findOne(Long clientId) throws APTException {
		Client client = clientRepository.findOne(clientId);
		return client;
	}

	@Override
	public List<Client> fetchByCustomerId(Long customerId) throws APTException {
		List<Client> clientList = clientRepository.fetchByCustomerId(customerId);
		return clientList;
	}

}
