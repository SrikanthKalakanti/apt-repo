package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.Customer;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.CustomerRespository;
import com.apt.msa.util.CryptoUtil;

@Service
public class CustomerService implements ICustomerService {

	//@Autowired
	//private CustomerCRUDRepository customerCRUDRepository;

	
	@Autowired
	private CustomerRespository customerRepository;
	
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

		List<Customer>  customerList = customerRepository.fetchByEmail(userName);
		
		if(null!= customerList && customerList.size() > 0 ){
			final Customer customer = customerList.get(0);
			try {
				final String decryptedPwd = CryptoUtil.decrypt(customer.getPassword()).toString();
				if(userName.equals(customer.getEmail()) && (decryptedPwd.equals(password)) ) {
					return customer;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return null;
	}

	@Override
	public Customer findOne(Long customerId) throws APTException {
		Customer customer = customerRepository.findOne(customerId);
		return customer;
	}

}
