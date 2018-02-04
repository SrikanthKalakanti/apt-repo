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

	@Override
	public int updateBasicInput(BasicInput basicInput) throws APTException {
		return basicInputRepository.updateBasicInputByIdAndClient(basicInput.getBusinessCommencementDate(), basicInput.getCashCreditAmountRequired(), basicInput.getFixedExpensesPerMonth(), 
				basicInput.getInterestRateForCc(), basicInput.getMoratorium(), basicInput.getNameOfTheBusiness(), basicInput.getNumberOfDaysInAMonth(), basicInput.getPaymentDate(), 
				basicInput.getProductionPerMonthInUnits(), basicInput.getStatus(), basicInput.getTenureOfTermLoan(), basicInput.getTermLoanFirstDisbursementDate(), basicInput.getClientId(), basicInput.getBasicInputId());
	}

	@Override
	public int deleteBasicInput(BasicInput basicInput) throws APTException {
		basicInputRepository.deleteBasicByIdAndClient(basicInput.getBasicInputId(), basicInput.getClientId());
		return 0;
	}

	
}
