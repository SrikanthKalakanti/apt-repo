package com.apt.msa.service;

import com.apt.msa.entity.CustomerTransaction;
import com.apt.msa.exception.APTException;

public interface ICustomerTransactionService {
	
	boolean createCustomerTransaction(CustomerTransaction customerTransaction) throws APTException;

	int updateCustomerTransaction(Long customerId) throws APTException;

	CustomerTransaction fetchCustomerTransactionByCustomerId(Long customerId) throws APTException;

     
}
