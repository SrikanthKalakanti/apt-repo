package com.apt.msa.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apt.msa.entity.Test;
import com.apt.msa.service.ITestService;

@RestController
@RequestMapping("test")
public class TestController {
	
	@Autowired
	private ITestService testService;

	
	@PostMapping(value ="createTest")
	public void creatTest(RequestEntity<Test> requestEntity) {
		try {
			Test test = requestEntity.getBody();
			
			Date date = new Date(new SimpleDateFormat("dd/MM/yyyy").parse(test.getDateInput()).getTime());
			System.out.println("Test:"+test);
			test.setTermDate(date);
			
			System.out.println(test);
			
			testService.createTest(test);
			
			Test test1 = testService.findOne(1L);
			System.out.println(test1);
					
		} catch(Exception e){
			e.printStackTrace();
		  }

	}
}
