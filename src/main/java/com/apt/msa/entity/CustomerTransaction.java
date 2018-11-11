package com.apt.msa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="customer_transaction")
@Table(name="customer_transaction")
public class CustomerTransaction implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long customerId;
	private Long planId;
	
	private String  purchase_date_time;
	private String  validaity_date_time;	
	
	private int number_of_reportsremaining;
	
	@Transient
	private int number_of_days_remaining;
	
	public CustomerTransaction(){
		
	}
	
	public CustomerTransaction(Long id, Long customerId, Long planId, String purchase_date_time, String validaity_date_time) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.planId = planId;
		this.purchase_date_time = purchase_date_time;
		this.validaity_date_time = validaity_date_time;
	}

	
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
	public int getNumber_of_reportsremaining() {
		return number_of_reportsremaining;
	}
	public void setNumber_of_reportsremaining(int number_of_reportsremaining) {
		this.number_of_reportsremaining = number_of_reportsremaining;
	}
	public int getNumber_of_days_remaining() {
		return number_of_days_remaining;
	}
	public void setNumber_of_days_remaining(int number_of_days_remaining) {
		this.number_of_days_remaining = number_of_days_remaining;
	}

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public String getPurchase_date_time() {
		return purchase_date_time;
	}

	public void setPurchase_date_time(String purchase_date_time) {
		this.purchase_date_time = purchase_date_time;
	}

	public String getValidaity_date_time() {
		return validaity_date_time;
	}

	public void setValidaity_date_time(String validaity_date_time) {
		this.validaity_date_time = validaity_date_time;
	}

		
}
