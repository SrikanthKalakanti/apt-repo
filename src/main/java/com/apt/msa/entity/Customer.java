package com.apt.msa.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="customer")
public class Customer implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long customerId;  

	private String namePrefix;

	private String firstName;

	private String lastName;

	private String userName;

	private String password;

	private String status;

	private String profession;

	private String otherProfession;

	private int trial;

	private String email;
	
	/*private boolean emailValidationflag;
	private boolean mobileValidationflag;*/
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
	@LazyCollection(LazyCollectionOption.FALSE)
	@JsonManagedReference
	private List<Address> address = new ArrayList<Address>();
	
	// for JPA default constructor
	public Customer() {

	}

	public Customer(Long customerId, String namePrefix, String firstName, String lastName, String userName,
			String password, String status, String profession, String otherProfession, int trial, String email,
			List<Address> address) {
		super();
		this.customerId = customerId;
		this.namePrefix = namePrefix;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.status = status;
		this.profession = profession;
		this.otherProfession = otherProfession;
		this.trial = trial;
		this.email = email;
		this.address = address;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password; 
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public String getOtherProfession() {
		return otherProfession;
	}

	public void setOtherProfession(String otherProfession) {
		this.otherProfession = otherProfession;
	}

	public int getTrial() {
		return trial;
	}

	public void setTrial(int trial) {
		this.trial = trial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", namePrefix=" + namePrefix + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userName=" + userName + ", password=" + password + ", status=" + status
				+ ", profession=" + profession + ", otherProfession=" + otherProfession + ", trial=" + trial
				+ ", email=" + email + ", address=" + address + "]";
	}

	
}
