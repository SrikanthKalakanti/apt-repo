package com.apt.msa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="admin_control")
@Table(name="admin_control")
public class AdminControl implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long customerId;  
	private Long amount;
	private int noofreportspurchased;
	private int reportsdownloaded;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public int getNoofreportspurchased() {
		return noofreportspurchased;
	}
	public void setNoofreportspurchased(int noofreportspurchased) {
		this.noofreportspurchased = noofreportspurchased;
	}
	public int getReportsdownloaded() {
		return reportsdownloaded;
	}
	public void setReportsdownloaded(int reportsdownloaded) {
		this.reportsdownloaded = reportsdownloaded;
	}
		
}
