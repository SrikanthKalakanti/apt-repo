package com.apt.msa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="customer_transaction")
@Table(name="customer_transaction")
public class CustomerTransaction implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long customerId;
	private Long planId;
	private Date purchase_date;
	private Date validaity_date;	
	private int number_of_reportsremaining;
	private int number_of_days_remaining;
	
	public CustomerTransaction(){
		
	}
	
	public CustomerTransaction(Long id, Long customerId, Long planId, Date purchase_date, Date validaity_date) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.planId = planId;
		this.purchase_date = purchase_date;
		this.validaity_date = validaity_date;
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

	public Date getPurchase_date() {
		return purchase_date;
	}

	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}

	public Date getValidaity_date() {
		return validaity_date;
	}

	public void setValidaity_date(Date validaity_date) {
		this.validaity_date = validaity_date;
	}
	
}
