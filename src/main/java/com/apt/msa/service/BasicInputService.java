package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.BasicInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.BasicInputRespository;

@Service
public class BasicInputService implements IBasicInputService {

	@Autowired
	private BasicInputRespository basicInputRepository;
	
	@Override
	public BasicInput createBasicInput(BasicInput basicInput) throws APTException {
		
		return basicInputRepository.save(basicInput);
		
	}
	
	@Override
	public BasicInput findOne(Long clientId) throws APTException {
		BasicInput basicInput = basicInputRepository.findOne(clientId);
		return basicInput;
	}

	
}
