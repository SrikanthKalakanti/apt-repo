package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.Customer;
import com.apt.msa.entity.CustomerTransaction;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.CustomerRespository;
import com.apt.msa.jpa.repository.CustomerTransactionRespository;
import com.apt.msa.util.CryptoUtil;

@Service
public class CustomerService implements ICustomerService {

	//@Autowired
	//private CustomerCRUDRepository customerCRUDRepository;

	
	@Autowired
	private CustomerRespository customerRepository;
	
	@Autowired
	private CustomerTransactionRespository customerTransactioRepository;
	
	/**
	 * create customer to DB
	 */
	public Customer createCustomer(Customer customer) throws APTException {

		return customerRepository.saveAndFlush(customer);

	}
	
	/**
	 * customer login 
	 */
	public Customer login(final String userName, final String password) throws APTException {

		Customer customer = null;
		
		try {
				customer = customerRepository.fetchByEmail(userName);
				
				if(null!= customer ){
					
					final String decryptedPwd = CryptoUtil.decrypt(customer.getPassword()).toString();
					
					if(userName.equals(customer.getEmail()) && (decryptedPwd.equals(password)) ) {
						return customer;
					}
				}		
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
	}

	@Override
	public Customer findOne(Long customerId) throws APTException {
		Customer customer = customerRepository.findOne(customerId);
		return customer;
	}
	
	
	@Override
	public CustomerTransaction createTransaction(CustomerTransaction customerTransaction) throws APTException {
		
		CustomerTransaction customerTransactionDB =  findTransaction(customerTransaction.getCustomerId());
		
		if(null!= customerTransactionDB) {
			
			customerTransactionDB.setPlanId(customerTransaction.getPlanId());
			customerTransactionDB.setValidaity_date_time(customerTransaction.getValidaity_date_time());
			customerTransactionDB.setPurchase_date_time(customerTransaction.getPurchase_date_time());
			customerTransactionDB.setNumber_of_reportsremaining(customerTransaction.getNumber_of_reportsremaining());
			
			return customerTransactioRepository.save(customerTransactionDB);
		
		} else {
			 return customerTransactioRepository.save(customerTransaction);
		}
	}

	@Override
	public CustomerTransaction findTransaction(Long customerId) throws APTException {
		CustomerTransaction customerTransaction = customerTransactioRepository.findOne(customerId);
		return customerTransaction;
	}

	

}
