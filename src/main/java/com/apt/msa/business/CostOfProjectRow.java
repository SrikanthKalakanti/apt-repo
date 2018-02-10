package com.apt.msa.business;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.apt.msa.entity.AssetInput;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CostOfProjectRow implements Serializable{
	
	/**
	 * Cost of Project is a table in the report to be generated with the user input.
	 * This table has a header row which is defined in CostOfProjectTable Class.
	 * This table's last row shows column totals and is defined in CostOfProjectTable Class too.
	 * All the rows between the first and last rows in this table are similar in type.
	 * This class defines a typical row in the table.
	 */
	
	
	
	private static final long serialVersionUID = 1L;
	
	private Long assetId;
	
	private String name; 
	private double value;
	private double promoterMargin;
	private double termLoanAmount;
	private double marginAmount;

	
	private Long clientId;
	
	public CostOfProjectRow(){
		
	}
	
	
	/*
	 * A constructor with all the fields passed on to it.
	 */
	public CostOfProjectRow(Long assetId, String name, double value, double promoterMargin,
			Long clientId) {
		super();
		this.assetId = assetId;
		this.name = name;
		this.value = value;
		this.termLoanAmount = termLoanAmount;
		this.marginAmount = marginAmount;
		this.clientId = clientId;
	}
	
	/*
	 * A constructor that takes an AssetInput object as input.
	 */
	public CostOfProjectRow(AssetInput asset) {
		super();
		this.assetId = asset.getAssetId();
		this.name = asset.getName();
		this.value = asset.getValue();
		this.marginAmount = asset.getValue()*asset.getPromoterMargin()/100;
		this.termLoanAmount = asset.getValue()*(1-asset.getPromoterMargin())/100;
		this.clientId = asset.getClientId();
	}

	public Long getAssetId() {
		return assetId;
	}

	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	
	public double getTermLoanAmount() {
		return termLoanAmount;
	}

	public void setTermLoanAmount(double depreciationRate) {
		this.termLoanAmount = termLoanAmount;
	}

	public double getPromoterMargin() {
		return promoterMargin;
	}

	public void setPromoterMargin(double promoterMargin) {
		this.promoterMargin = promoterMargin;
	}

	public double getMarginAmount() {
		return marginAmount;
	}

	public void setMarginAmount(double promoterMargin) {
		this.marginAmount = marginAmount;
	}

	
	@Override
	public String toString() {
		return "CoPRow [assetId=" + assetId + ", name=" + name + ", value=" + value 
				+", promoterMargin=" + promoterMargin + ", termLoanAmount =" + termLoanAmount + 
				", marginAmount=" + marginAmount + ", clientId=" + clientId + "]";
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
