package com.apt.msa.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="report_audit")
@Table(name="report_audit")
public class ReportAudit implements Serializable { 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private Long customerId;  
	private Long clientId;
	
	private Timestamp downloadedTime;
	
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
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public Timestamp getDownloadedTime() {
		return downloadedTime;
	}
	public void setDownloadedTime(Timestamp downloadedTime) {
		this.downloadedTime = downloadedTime;
	}
	
}
