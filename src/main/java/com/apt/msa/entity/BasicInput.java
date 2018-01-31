package com.apt.msa.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name="basic_input")
@Table(name="basic_input")
public class BasicInput implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long basicInputId;
	
	/*@OneToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "clientId")
	@JsonBackReference
	private Client client;*/
	
	@JsonProperty("clientId")
	private Long clientId;
	 
	private String nameOfTheBusiness;
	
	private Date termLoanFirstDisbursementDate; 
	private Date businessCommencementDate;
	
	@Transient
	private String termLoanDisbursement;
	@Transient
	private String businessCommencement;
	
	
	private Integer tenureOfTermLoan; 
	private double moratorium;
	
	@JsonProperty()
	private double interestRateForCc; 
	private Integer paymentDate; 
	private Integer status; 
	private Integer cashCreditAmountRequired; 
	private Integer fixedExpensesPerMonth;  
	private Integer numberOfDaysInAMonth; 
	private Integer productionPerMonthInUnits;
	
	public BasicInput(){
		
	}

	public BasicInput(Long basicInputId, String nameOfTheBusiness, Date termLoanFirstDisbursementDate,
			Date businessCommencementDate, Integer tenureOfTermLoan, double moratorium, double interestRateForCc,
			Integer paymentDate, Integer status, Integer cashCreditAmountRequired, Integer fixedExpensesPerMonth,
			Integer numberOfDaysInAMonth, Integer productionPerMonthInUnits) {
		super();
		this.basicInputId = basicInputId;
		this.nameOfTheBusiness = nameOfTheBusiness;
		this.termLoanFirstDisbursementDate = termLoanFirstDisbursementDate;
		this.businessCommencementDate = businessCommencementDate;
		this.tenureOfTermLoan = tenureOfTermLoan;
		this.moratorium = moratorium;
		this.interestRateForCc = interestRateForCc;
		this.paymentDate = paymentDate;
		this.status = status;
		this.cashCreditAmountRequired = cashCreditAmountRequired;
		this.fixedExpensesPerMonth = fixedExpensesPerMonth;
		this.numberOfDaysInAMonth = numberOfDaysInAMonth;
		this.productionPerMonthInUnits = productionPerMonthInUnits;
	}

	public Long getBasicInputId() {
		return basicInputId;
	}

	public void setBasicInputId(Long basicInputId) {
		this.basicInputId = basicInputId;
	}

	public String getNameOfTheBusiness() {
		return nameOfTheBusiness;
	}

	public void setNameOfTheBusiness(String nameOfTheBusiness) {
		this.nameOfTheBusiness = nameOfTheBusiness;
	}

	public Date getTermLoanFirstDisbursementDate() {
		return termLoanFirstDisbursementDate;
	}

	public void setTermLoanFirstDisbursementDate(Date termLoanFirstDisbursementDate) {
		this.termLoanFirstDisbursementDate = termLoanFirstDisbursementDate;
	}

	public Date getBusinessCommencementDate() {
		return businessCommencementDate;
	}

	public void setBusinessCommencementDate(Date businessCommencementDate) {
		this.businessCommencementDate = businessCommencementDate;
	}

	public Integer getTenureOfTermLoan() {
		return tenureOfTermLoan;
	}

	public void setTenureOfTermLoan(Integer tenureOfTermLoan) {
		this.tenureOfTermLoan = tenureOfTermLoan;
	}

	public double getMoratorium() {
		return moratorium;
	}

	public void setMoratorium(double moratorium) {
		this.moratorium = moratorium;
	}

	public double getInterestRateForCc() {
		return interestRateForCc;
	}

	public void setInterestRateForCc(double interestRateForCc) {
		this.interestRateForCc = interestRateForCc;
	}

	public Integer getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Integer paymentDate) {
		this.paymentDate = paymentDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getCashCreditAmountRequired() {
		return cashCreditAmountRequired;
	}

	public void setCashCreditAmountRequired(Integer cashCreditAmountRequired) {
		this.cashCreditAmountRequired = cashCreditAmountRequired;
	}

	public Integer getFixedExpensesPerMonth() {
		return fixedExpensesPerMonth;
	}

	public void setFixedExpensesPerMonth(Integer fixedExpensesPerMonth) {
		this.fixedExpensesPerMonth = fixedExpensesPerMonth;
	}

	public Integer getNumberOfDaysInAMonth() {
		return numberOfDaysInAMonth;
	}

	public void setNumberOfDaysInAMonth(Integer numberOfDaysInAMonth) {
		this.numberOfDaysInAMonth = numberOfDaysInAMonth;
	}

	public Integer getProductionPerMonthInUnits() {
		return productionPerMonthInUnits;
	}

	public void setProductionPerMonthInUnits(Integer productionPerMonthInUnits) {
		this.productionPerMonthInUnits = productionPerMonthInUnits;
	}

	@Override
	public String toString() {
		return "BasicInput [basicInputId=" + basicInputId +  ", nameOfTheBusiness="
				+ nameOfTheBusiness + ", termLoanFirstDisbursementDate=" + termLoanFirstDisbursementDate
				+ ", businessCommencementDate=" + businessCommencementDate + ", tenureOfTermLoan=" + tenureOfTermLoan
				+ ", moratorium=" + moratorium + ", interestRateForCc=" + interestRateForCc + ", paymentDate="
				+ paymentDate + ", status=" + status + ", cashCreditAmountRequired=" + cashCreditAmountRequired
				+ ", fixedExpensesPerMonth=" + fixedExpensesPerMonth + ", numberOfDaysInAMonth=" + numberOfDaysInAMonth
				+ ", productionPerMonthInUnits=" + productionPerMonthInUnits + "]";
	}

	public String getTermLoanDisbursement() {
		return termLoanDisbursement;
	}

	public void setTermLoanDisbursement(String termLoanDisbursement) {
		this.termLoanDisbursement = termLoanDisbursement;
	}

	public String getBusinessCommencement() {
		return businessCommencement;
	}

	public void setBusinessCommencement(String businessCommencement) {
		this.businessCommencement = businessCommencement;
	}
	
	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
}
