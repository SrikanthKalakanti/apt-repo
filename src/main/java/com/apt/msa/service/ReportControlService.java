package com.apt.msa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apt.msa.entity.ReportAudit;
import com.apt.msa.exception.APTException;
import com.apt.msa.jpa.repository.ReportRespository;

@Service
public class ReportControlService implements IReportControlService {

	@Autowired
	private ReportRespository reportRepository;
	
	@Override
	public boolean createReportsAudit(ReportAudit reportAudit) throws APTException {
		
		boolean flag = false;
		reportRepository.save(reportAudit);
		flag = true;
		
		return flag;
	}

	@Override
	public List<ReportAudit> fetchReportAuditByCustomerId(Long customerId) throws APTException {
	
		List<ReportAudit> reportAuditList = reportRepository.fetchByCustomerId(customerId);
		return reportAuditList;
	}

	@Override
	public boolean deleteReportAudit(Long customerId) throws APTException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ReportAudit> fetchReportAuditByClientId(Long clientId) throws APTException {
		List<ReportAudit> reportAuditList = reportRepository.fetchByClientId(clientId);
		return reportAuditList;
	}

	
}
