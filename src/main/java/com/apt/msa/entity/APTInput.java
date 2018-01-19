package com.apt.msa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="aptinput")
public class APTInput implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long aptId; 
	
	private Long clientId;
	private Long customerId;
		
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<AssetInput> assetInput = new ArrayList<AssetInput>();

	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private BasicInput basicInput;
	
	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private GrowthInflationInput growthInflationInput; 
	
	
	@OneToMany(cascade = {CascadeType.ALL}, mappedBy = "client")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference	
	private List<ExpensesInput> expensesInput = new ArrayList<>();
	
	// for JPA default constructor
	public APTInput() {

	}

	public APTInput(Long clientId,Long customerId, List<AssetInput> assetInput, BasicInput basicInput,
			GrowthInflationInput growthInflationInput, List<ExpensesInput> expensesInput) {
		super();
		this.clientId = clientId;
		this.customerId = customerId;
		this.assetInput = assetInput;
		this.basicInput = basicInput;
		this.growthInflationInput = growthInflationInput;
		this.expensesInput = expensesInput;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public BasicInput getBasicInput() {
		return basicInput;
	}

	public void setBasicInput(BasicInput basicInput) {
		this.basicInput = basicInput;
	}

	
	public List<ExpensesInput> getExpensesInput() {
		return expensesInput;
	}

	public void setExpensesInput(List<ExpensesInput> expensesInput) {
		this.expensesInput = expensesInput;
	}

	public List<AssetInput> getAssetInput() {
		return assetInput;
	}

	public void setAssetInput(List<AssetInput> assetInput) {
		this.assetInput = assetInput;
	}

	public GrowthInflationInput getGrowthInflationInput() {
		return growthInflationInput;
	}

	public void setGrowthInflationInput(GrowthInflationInput growthInflationInput) {
		this.growthInflationInput = growthInflationInput;
	}

		@Override
	public String toString() {
		return "APTInput [clientId=" + clientId + ", customerId=" + customerId + ", assetInput=" + assetInput
				+ ", basicInput=" + basicInput + ", growthInflationInput=" + growthInflationInput + ", expensesInput="
				+ expensesInput + "]";
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

		

}
