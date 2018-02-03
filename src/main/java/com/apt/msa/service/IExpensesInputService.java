package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.exception.APTException;

public interface IExpensesInputService {
	
	boolean createExpensesInput(List<ExpensesInput> expensesList) throws APTException;
	List<ExpensesInput> fetchByClientId(Long clientId) throws APTException;
     
}
