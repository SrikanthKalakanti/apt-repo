package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.CustomerTransaction;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.CustomerTransactionRespository;

@Service
public class CustomerTransactionService implements ICustomerTransactionService {

	@Autowired
	private CustomerTransactionRespository customerTransactioRepository;
	
	@Override
	public boolean createCustomerTransaction(CustomerTransaction customerTransaction) throws APTException {
		
		boolean flag = false;
		customerTransactioRepository.save(customerTransaction);
		flag = true;
		
		return flag;
	}
	
	@Override
	public int updateCustomerTransaction(Long customerId) throws APTException {
		
		CustomerTransaction customerTransaction = customerTransactioRepository.fetchByCustomerId(customerId);

		int result  = customerTransactioRepository.updateCustomerTransaction( customerId, customerTransaction.getNumber_of_reportsremaining()-1);
		
		return result;
	}
	
	@Override
	public CustomerTransaction fetchCustomerTransactionByCustomerId(Long customerId) throws APTException {
		CustomerTransaction customerTransaction = customerTransactioRepository.fetchByCustomerId(customerId);
		return customerTransaction;
	}

	
}
