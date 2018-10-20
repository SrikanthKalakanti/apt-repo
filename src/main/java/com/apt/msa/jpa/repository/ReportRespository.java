package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.entity.AssetInput;
import com.apt.msa.entity.ReportAudit;

@Repository
@Transactional
public interface ReportRespository extends JpaRepository<ReportAudit, Long> {
	
	boolean deleteByCustomerId(Long customerId);
	
	@Query("FROM report_audit where customer_id=?1")
	List<ReportAudit> fetchByCustomerId(@Param("customerId") Long customerId);

	@Query("FROM report_audit where client_id=?1")
	List<ReportAudit> fetchByClientId(@Param("clientId") Long clientId);
}
