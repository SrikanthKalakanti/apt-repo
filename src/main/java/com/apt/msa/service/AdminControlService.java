package com.apt.msa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.AdminRespository;

@Service
public class AdminControlService implements IAdminControlService {

	@Autowired
	private AdminRespository adminRepository;
	
	@Override
	public boolean createAdminControl(AdminControl adminControl) throws APTException {
		
		boolean flag = false;
		adminRepository.save(adminControl);
		flag = true;
		
		return flag;
	}
	
	@Override
	public int updateAdminControl(Long customerId) throws APTException {
		
		AdminControl adminControl = adminRepository.fetchByCustomerId(customerId);

		int result  = adminRepository.updateAdminControl( adminControl.getCustomerId(), adminControl.getReportsdownloaded()+1);
		
		return result;
	}
	
	@Override
	public AdminControl fetchAdminControlByCustomerId(Long customerId) throws APTException {
		AdminControl adminControl = adminRepository.fetchByCustomerId(customerId);
		return adminControl;
	}

	@Override
	public boolean deleteAdminControl(Long customerId) throws APTException {
		boolean flag = false;
		flag = adminRepository.deleteByCustomerId(customerId);
		return flag;
	}

	
}
