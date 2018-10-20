package com.apt.msa.service;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.exception.APTException;

public interface IAdminControlService {
	
	boolean createAdminControl(AdminControl adminControl) throws APTException;

	int updateAdminControl(Long customerId) throws APTException;

	AdminControl fetchAdminControlByCustomerId(Long customerId) throws APTException;

	boolean deleteAdminControl(Long customerId) throws APTException;
     
}
