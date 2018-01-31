package com.apt.msa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="expenses_input")
@Table(name="expenses_input")
public class ExpensesInput implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long expensesInputId;
	private String nomenclature;
	private String cmaNomenclature; 
	private String expenditurePer; 
	private double amountInINR;

	/*@ManyToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "clientId")
	@JsonBackReference
	private Client client;*/
	
	@JsonProperty("clientId")
	private Long clientId;
	
	public ExpensesInput(){
		
	}
	
	public ExpensesInput(Long expensesInputId, String nomenclature, String cmaNomenclature, String expenditurePer,
			double amountInINR, Long clientId) {
		super();
		this.expensesInputId = expensesInputId;
		this.nomenclature = nomenclature;
		this.cmaNomenclature = cmaNomenclature;
		this.expenditurePer = expenditurePer;
		this.amountInINR = amountInINR;
		this.clientId = clientId;
	}
	
	public Long getExpensesInputId() {
		return expensesInputId;
	}

	public void setExpensesInputId(Long expensesInputId) {
		this.expensesInputId = expensesInputId;
	}

	public String getNomenclature() {
		return nomenclature;
	}

	public void setNomenclature(String nomenclature) {
		this.nomenclature = nomenclature;
	}

	public String getCmaNomenclature() {
		return cmaNomenclature;
	}

	public void setCmaNomenclature(String cmaNomenclature) {
		this.cmaNomenclature = cmaNomenclature;
	}

	public String getExpenditurePer() {
		return expenditurePer;
	}

	public void setExpenditurePer(String expenditurePer) {
		this.expenditurePer = expenditurePer;
	}

	public double getAmountInINR() {
		return amountInINR;
	}

	public void setAmountInINR(double amountInINR) {
		this.amountInINR = amountInINR;
	}

	@Override
	public String toString() {
		return "ExpensesInput [expensesInputId=" + expensesInputId + ", nomenclature=" + nomenclature
				+ ", cmaNomenclature=" + cmaNomenclature + ", expenditurePer=" + expenditurePer + ", amountInINR="
				+ amountInINR + ", clientId=" + clientId + "]";
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

}
