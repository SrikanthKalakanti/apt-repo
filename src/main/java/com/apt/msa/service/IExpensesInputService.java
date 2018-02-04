package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.ExpensesInput;
import com.apt.msa.exception.APTException;

public interface IExpensesInputService {
	
	boolean createExpensesInput(List<ExpensesInput> expensesList) throws APTException;
	List<ExpensesInput> fetchByClientId(Long clientId) throws APTException;
	int updateExpensesInput(ExpensesInput expensesInput) throws APTException;
	int deleteExpensesInput(ExpensesInput expensesInput) throws APTException;
	boolean createExpensesInput(ExpensesInput expensesInput)throws APTException;
     
}
