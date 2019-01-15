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

	@Override
	public int updateExpensesInput(ExpensesInput expensesInput) throws APTException {
		return expensesInputRepository.updateExpensesByIdClient(expensesInput.getAmountInINR(), expensesInput.getExpenditurePer(), expensesInput.getCmaNomenclature(), expensesInput.getNomenclature(), expensesInput.getClientId(), expensesInput.getExpensesInputId());
		
	}

	@Override
	public int deleteExpensesInput(ExpensesInput expensesInput) throws APTException {
		return expensesInputRepository.deleteAssetByIdAndClient(expensesInput.getClientId(), expensesInput.getExpensesInputId());
		
	}

	@Override
	public boolean createExpensesInput(ExpensesInput expensesInput) throws APTException {
		boolean flag = false;
		expensesInputRepository.save(expensesInput);
		flag = true;
		return flag;
	}
	
	@Override
	public ExpensesInput fetchByClientSellingPrice(Long clientId) throws APTException {
		ExpensesInput expensesInput = expensesInputRepository.fetchByClientIdAndSellingPrice(clientId);
		return expensesInput;
	}

	@Override
	public List<ExpensesInput> fetchAllByClient(Long clientId) throws APTException {
		List<ExpensesInput> expensesInputList = expensesInputRepository.fetchAllByClientId(clientId);
		return expensesInputList;
	}
}
