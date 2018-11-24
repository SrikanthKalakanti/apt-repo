package com.apt.msa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="plan_details")
@Table(name="plan_details")
public class PlanDetails implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="plan_id")
	private Long planId;

	@Column(name="plan_name")
	private String plan_name;
	
	@Column(name="date_of_introduction")
	private Date date_of_introduction;
	
	@Column(name="base_price")
	private Double base_price;	
	
	@Column(name="gst")
	private Double gst;
	
	@Column(name="validity")
	private Integer validity;
	
	@Column(name="no_of_reports")
	private Integer no_of_reports;
	
	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public PlanDetails(){}
	
	public PlanDetails(Long planId, String plan_name, Date date_of_introduction, Double base_price, Double gst,
			Integer validity, Integer no_of_reports) {
		super();
		this.planId = planId;
		this.plan_name = plan_name;
		this.date_of_introduction = date_of_introduction;
		this.base_price = base_price;
		this.gst = gst;
		this.validity = validity;
		this.no_of_reports = no_of_reports;
	}
	

	public String getPlan_name() {
		return plan_name;
	}
	public void setPlan_name(String plan_name) {
		this.plan_name = plan_name;
	}
	public Date getDate_of_introduction() {
		return date_of_introduction;
	}
	public void setDate_of_introduction(Date date_of_introduction) {
		this.date_of_introduction = date_of_introduction;
	}
	public Double getBase_price() {
		return base_price;
	}
	public void setBase_price(Double base_price) {
		this.base_price = base_price;
	}
	public Double getGst() {
		return gst;
	}
	public void setGst(Double gst) {
		this.gst = gst;
	}
	public Integer getValidity() {
		return validity;
	}
	public void setValidity(Integer validity) {
		this.validity = validity;
	}
	public Integer getNo_of_reports() {
		return no_of_reports;
	}
	public void setNo_of_reports(Integer no_of_reports) {
		this.no_of_reports = no_of_reports;
	}
	
}
