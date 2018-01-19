package com.apt.msa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client")
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long clientId;
	
	private Long customerId;
	
	private String namePrefix;
	private String name;
	private String status;
	private String doorNo;
	private String road;
	private String locality;
	private String landmark;
	private String town;
	private int pincode;
	private String state;
	private String landphone;
	private Long mobile;
	private String email;
	private String lineofactivity;
	private String dateoffirstditributionoftermloan;
	private String reportgenerated;
	
	// for JPA default constructor
	public Client() {

	}

	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", customerId=" + customerId + ", namePrefix=" + namePrefix + ", name="
				+ name + ", status=" + status + ", doorNo=" + doorNo + ", road=" + road + ", locality=" + locality
				+ ", landmark=" + landmark + ", town=" + town + ", pincode=" + pincode + ", state=" + state
				+ ", landphone=" + landphone + ", mobile=" + mobile + ", email=" + email + ", lineofactivity="
				+ lineofactivity + ", dateoffirstditributionoftermloan=" + dateoffirstditributionoftermloan
				+ ", reportgenerated=" + reportgenerated + "]";
	}

	public Client(Long clientId,Long customerId, String namePrefix, String name, String status, String doorNo, String road, String locality, String landmark, String town, 
				int pincode, String state, String landphone,Long mobile, String email, String lineofactivity, String dateoffirstditributionoftermloan, String reportgenerated) {
		super();
		this.clientId = clientId;
		this.namePrefix = namePrefix;
		this.name = name;
		this.status = status;
		this.doorNo = doorNo;
		this.road = road;
		this.locality = locality;
		this.landmark = landmark;
		this.town = town;
		this.pincode = pincode;
		this.state = state;
		this.landphone = landphone;
		this.mobile = mobile;
		this.email = email;
		this.lineofactivity = lineofactivity;
		this.dateoffirstditributionoftermloan = dateoffirstditributionoftermloan;
		this.reportgenerated = reportgenerated;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getNamePrefix() {
		return namePrefix;
	}

	public void setNamePrefix(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDoorNo() {
		return doorNo;
	}

	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}

	public String getRoad() {
		return road;
	}

	public void setRoad(String road) {
		this.road = road;
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

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLandphone() {
		return landphone;
	}

	public void setLandphone(String landphone) {
		this.landphone = landphone;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLineofactivity() {
		return lineofactivity;
	}

	public void setLineofactivity(String lineofactivity) {
		this.lineofactivity = lineofactivity;
	}

	public String getDateoffirstditributionoftermloan() {
		return dateoffirstditributionoftermloan;
	}

	public void setDateoffirstditributionoftermloan(String dateoffirstditributionoftermloan) {
		this.dateoffirstditributionoftermloan = dateoffirstditributionoftermloan;
	}

	public String getReportgenerated() {
		return reportgenerated;
	}

	public void setReportgenerated(String reportgenerated) {
		this.reportgenerated = reportgenerated;
	}

		

}
