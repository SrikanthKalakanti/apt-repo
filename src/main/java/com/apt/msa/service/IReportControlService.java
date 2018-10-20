package com.apt.msa.service;

import java.util.List;

import com.apt.msa.entity.ReportAudit;
import com.apt.msa.exception.APTException;

public interface IReportControlService {
	
	boolean createReportsAudit(ReportAudit reportAudit) throws APTException;

	List<ReportAudit> fetchReportAuditByCustomerId(Long customerId) throws APTException;
	
	List<ReportAudit> fetchReportAuditByClientId(Long clientId) throws APTException;

	boolean deleteReportAudit(Long customerId) throws APTException;
     
}
