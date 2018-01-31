package com.apt.msa.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.apt.msa.entity.ExpensesInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ExpensesInputRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("expensesInput")
	private List<ExpensesInput> expensesInputList = new ArrayList<ExpensesInput>();

	public List<ExpensesInput> getExpensesInputList() {
		return expensesInputList;
	}

	public void setExpensesInputList(List<ExpensesInput> expensesInputList) {
		this.expensesInputList = expensesInputList;
	}

	
}
