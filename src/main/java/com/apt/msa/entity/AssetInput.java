package com.apt.msa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity(name="asset_input")
@Table(name="asset_input")
public class AssetInput implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long assetId;
	
	private String name; 
	private double value;
	private double depreciationRate;
	private double promoterMargin;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonBackReference
    @JoinColumn(name = "clientId")
	private Client client;
	
	public AssetInput(){
		
	}
	
	public AssetInput(Long assetId, String name, double value, double depreciationRate, double promoterMargin,
			Client client) {
		super();
		this.assetId = assetId;
		this.name = name;
		this.value = value;
		this.depreciationRate = depreciationRate;
		this.promoterMargin = promoterMargin;
		this.client = client;
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

	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public double getDepreciationRate() {
		return depreciationRate;
	}

	public void setDepreciationRate(double depreciationRate) {
		this.depreciationRate = depreciationRate;
	}

	public double getPromoterMargin() {
		return promoterMargin;
	}

	public void setPromoterMargin(double promoterMargin) {
		this.promoterMargin = promoterMargin;
	}

	@Override
	public String toString() {
		return "AssetInput [assetId=" + assetId + ", name=" + name + ", value=" + value + ", depreciationRate="
				+ depreciationRate + ", promoterMargin=" + promoterMargin + ", client=" + client + "]";
	}

}
