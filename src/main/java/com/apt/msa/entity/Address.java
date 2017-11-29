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

//import lombok.Data;

@Entity(name = "address")
@Table(name="address")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long addressId;  

	private String doorNumber;

	private String buildingName;

	private String roadNumber;

	private String locality;

	private String landmark;

	private String town;

	private String district;

	private String state;

	private Long mobile;

	private String landphone;
	
	private int pincode;

	// for JPA default constructor
	public Address() {

	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "customerId")
	@JsonBackReference
    private Customer customer;
	
	public Address(Long addressId, String doorNumber, String buildingName, String roadNumber, String locality,
			String landmark, String town, String district, String state, Long mobile, String landphone, int pincode) {
		super();
		this.addressId = addressId;
		this.doorNumber = doorNumber;
		this.buildingName = buildingName;
		this.roadNumber = roadNumber;
		this.locality = locality;
		this.landmark = landmark;
		this.town = town;
		this.district = district;
		this.state = state;
		this.mobile = mobile;
		this.landphone = landphone;
		this.pincode = pincode;
		//this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getRoadNumber() {
		return roadNumber;
	}

	public void setRoadNumber(String roadNumber) {
		this.roadNumber = roadNumber;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getLandphone() {
		return landphone;
	}

	public void setLandphone(String landphone) {
		this.landphone = landphone;
	}

	@Override
	public String toString() {
		return "Address [doorNumber=" + doorNumber + ", buildingName=" + buildingName + ", roadNumber=" + roadNumber
				+ ", locality=" + locality + ", landmark=" + landmark + ", town=" + town + ", district=" + district
				+ ", state=" + state + ", mobile=" + mobile + ", landphone=" + landphone + "]";
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}


}
