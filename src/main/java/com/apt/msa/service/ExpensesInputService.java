package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.ExpensesInputRespository;

@Service
public class ExpensesInputService implements IExpensesInputService {

	@Autowired
	private ExpensesInputRespository expensesInputRepository;
	
	@Override
	public boolean createExpensesInput(List<ExpensesInput> expensesList) throws APTException {
		
		boolean flag = false;
		expensesInputRepository.save(expensesList);
		flag = true;
		return flag;
	}
	
	@Override
	public List<ExpensesInput> fetchByClientId(Long clientId) throws APTException {
		List<ExpensesInput> expensesInputList = expensesInputRepository.fetchByClientId(clientId);
		return expensesInputList;
	}

}
