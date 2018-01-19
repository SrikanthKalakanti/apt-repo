package com.apt.msa.service;

import com.apt.msa.entity.APTInput;
import com.apt.msa.entity.Client;
import com.apt.msa.exception.APTException;

public interface IAPTService {
	
	APTInput createAPTInput(APTInput aptInput) throws APTException;
     
}
