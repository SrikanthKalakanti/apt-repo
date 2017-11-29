package com.apt.msa.service;

import com.apt.msa.entity.Test;
import com.apt.msa.exception.APTException;

public interface ITestService {
	
     Test createTest(Test Test) throws APTException;
     Test findOne(Long testId) throws APTException;
}
