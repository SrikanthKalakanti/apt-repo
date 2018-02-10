package com.apt.msa.entity;

import java.util.List;

public class ClientGlobalInput {

	private Client client;
	private BasicInput basic;
	private List<AssetInput> assetList;
	private List<ExpensesInput> expensesList;
	private GrowthInflationInput growthInflation;
	
	public ClientGlobalInput(){
		
	}
			
	public ClientGlobalInput(Client client, BasicInput basic, List<AssetInput> assetList,
			List<ExpensesInput> expensesList, GrowthInflationInput growthInflation) {
		super();
		this.client = client;
		this.basic = basic;
		this.assetList = assetList;
		this.expensesList = expensesList;
		this.growthInflation = growthInflation;
	}
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public BasicInput getBasic() {
		return basic;
	}
	public void setBasic(BasicInput basic) {
		this.basic = basic;
	}
	public List<AssetInput> getAssetList() {
		return assetList;
	}
	public void setAssetList(List<AssetInput> assetList) {
		this.assetList = assetList;
	}
	public List<ExpensesInput> getExpensesList() {
		return expensesList;
	}
	public void setExpensesList(List<ExpensesInput> expensesList) {
		this.expensesList = expensesList;
	}
	public GrowthInflationInput getGrowthInflation() {
		return growthInflation;
	}
	public void setGrowthInflation(GrowthInflationInput growthInflation) {
		this.growthInflation = growthInflation;
	}

	
	
	
}
