package com.apt.msa.business;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.apt.msa.entity.AssetInput;
import com.apt.msa.entity.ClientGlobalInput;

public class CostOfProjectTable {
		
	private static final Logger logger = LoggerFactory.getLogger(CostOfProjectTable.class);

	
	/*
	 * Table header is defined as String Array
	 */
	public static String [] columnHeaders = {"assetId", "assetName", "assetValue", "termLoanAmount", 
			"promoterMargin", "clientId"};
	
	/*
	 * The last row is derined as a list of objects and is built by method later on in this class. 
	 * The object would contain three strings and two numbers.  
	 */
	public List<Object> totalsRow;
	
	
	/*
	 * Table contains several rows between the header and the last row.
	 * Each row is an object of CostOfProjectRow Class.
	 */
	public List<CostOfProjectRow> listOfCostOfProjectRows; 
	
	
	/*
	 * Constructor that takes ClientGlobalInput object as input
	 */
	public CostOfProjectTable(ClientGlobalInput clientGlobalInput){
		this.listOfCostOfProjectRows = setCostOfProjectRows(clientGlobalInput);
//		List<CostOfProjectRow> listOfCostOfProjectRows = setCostOfProjectRows(clientGlobalInput);
//		setTotalsRow(listOfCostOfProjectRows);
		setTotalsRow(setCostOfProjectRows(clientGlobalInput));
	}

	/*
	 * This method both sets and gets CostOfProjectRows taking ClientGlobalInput as input.
	 */
	
	@SuppressWarnings("null")
	public List<CostOfProjectRow> setCostOfProjectRows(ClientGlobalInput clientGlobalInput){
		List<CostOfProjectRow> listOfCostOfProjectRows = new ArrayList<CostOfProjectRow>();
		
		List<AssetInput> assetInputList = clientGlobalInput.getAssetList();
		
		for (AssetInput asset: assetInputList){
			CostOfProjectRow aRow = new CostOfProjectRow(asset);
			listOfCostOfProjectRows.add(aRow);
		}
		return listOfCostOfProjectRows;
	}
	
	/*
	 * This method constructs the last row of the table
	 */
	public List<Object> setTotalsRow(List<CostOfProjectRow> listOfCostOfProjectRows){
		List<Object> totalsRow = new ArrayList<Object>();
		double totalMargin = 0;
		double termLoan =0;
		totalsRow.add(0,"-");
		totalsRow.add(1,"Total");
		totalsRow.add(2,"-");
		for(CostOfProjectRow aRow: listOfCostOfProjectRows){
			termLoan = termLoan+aRow.getTermLoanAmount();
			totalMargin = totalMargin+aRow.getMarginAmount();
		}
		totalsRow.add(3,termLoan);
		totalsRow.add(4, totalMargin);
		return this.totalsRow = totalsRow;
	}
	
	/*
	 * This method is to print the table in console
	 */
	public void printCostOfProjectTable(CostOfProjectTable costOfProjectTable){
		
		logger.info("......Column Headers CoP Table...."+costOfProjectTable.columnHeaders);
		for (CostOfProjectRow costOfProjectRow: costOfProjectTable.listOfCostOfProjectRows){
			logger.info("copRow: "+costOfProjectRow);
		}
		logger.info("......Totals Row CoP Table...."+costOfProjectTable.totalsRow);
	}

}