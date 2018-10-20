package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AdminControl;

@Repository
@Transactional
public interface AdminRespository extends JpaRepository<AdminControl, Long> {
	
	@Query("FROM admin_control where customer_id=?1")
	AdminControl fetchByCustomerId(@Param("customerId") Long customerId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE admin_control SET reportsdownloaded = :reportsdownloaded WHERE customer_id = :customerId ")
	int updateAdminControl(@Param("customerId") Long customerId,@Param("reportsdownloaded") int reportsdownloaded );

	boolean deleteByCustomerId(Long customerId);
	
}
