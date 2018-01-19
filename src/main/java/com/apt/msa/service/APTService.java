package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.APTInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.APTRespository;

@Service
public class APTService implements IAPTService {

	@Autowired
	private APTRespository aptRepository;
	
	@Override
	public APTInput createAPTInput(APTInput aptInput) throws APTException {
		 return aptRepository.save(aptInput);
	}

}
