package com.apt.msa.service;

import com.apt.msa.entity.BasicInput;
import com.apt.msa.exception.APTException;

public interface IBasicInputService {
	
	BasicInput createBasicInput(BasicInput basicInput) throws APTException;
	public BasicInput findOne(Long clientId) throws APTException;
	int  updateBasicInput(BasicInput basicInput) throws APTException;
	int  deleteBasicInput(BasicInput basicInput) throws APTException;
	public BasicInput fetchByClientId(Long clientId) throws APTException;
}
