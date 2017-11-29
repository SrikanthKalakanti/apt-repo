package com.apt.msa.service;

import com.apt.msa.entity.Customer;
import com.apt.msa.exception.APTException;
//import com.apt.msa.exception.APTException;

public interface ICustomerService {
	
     Customer createCustomer(Customer customer) throws APTException;
     Customer login(String username, String password) throws APTException;
     Customer findOne(Long customerId) throws APTException;
}
