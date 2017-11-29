package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.Test;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.TestRespository;

@Service
public class TestService implements ITestService {

	@Autowired
	private TestRespository testRepository;
	
	/**
	 * insert client details to DB
	 */
	public Test createTest(Test test) throws APTException {

		return testRepository.save(test);

	}
	
	@Override
	public Test findOne(Long clientId) throws APTException {
		Test test = testRepository.findOne(clientId);
		return test;
	}

}
