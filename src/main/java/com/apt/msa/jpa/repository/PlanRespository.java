package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.entity.PlanDetails;

@Repository
@Transactional
public interface PlanRespository extends JpaRepository<PlanDetails, Long> {
	
}
