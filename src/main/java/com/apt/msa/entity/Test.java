package com.apt.msa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name = "test")
@Table(name="test")
public class Test implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long testId; 
	
	
	private String name;
	private int number;
	private double amount;
	
	@Transient
	private String dateInput;
	
	private Date termDate;
	
	public Test(){
		
	}
	
	public Test(String name, int number, double amount, Date termDate) {
		super();
		this.name = name;
		this.number = number;
		this.amount = amount;
		this.termDate = termDate;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Date getTermDate() {
		return termDate;
	}
	public void setTermDate(Date termDate) {
		this.termDate = termDate;
	}

	@Override
	public String toString() {
		return "Test [name=" + name + ", number=" + number + ", amount=" + amount + ", dateInput=" + dateInput
				+ ", getName()=" + getName() + ", getNumber()=" + getNumber() + ", getAmount()=" + getAmount()
				+ ", getTermDate()=" + getTermDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public String getDateInput() {
		return dateInput;
	}

	public void setDateInput(String dateInput) {
		this.dateInput = dateInput;
	}

	public Long getTestId() {
		return testId;
	}

	public void setTestId(Long testId) {
		this.testId = testId;
	}

	
}
