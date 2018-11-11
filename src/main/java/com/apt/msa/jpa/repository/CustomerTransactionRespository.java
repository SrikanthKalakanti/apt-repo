package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AdminControl;
import com.apt.msa.entity.CustomerTransaction;

@Repository
@Transactional
public interface CustomerTransactionRespository extends JpaRepository<CustomerTransaction, Long> {
	
	@Query("FROM customer_transaction where customer_id=?1")
	CustomerTransaction fetchByCustomerId(@Param("customerId") Long customerId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE customer_transaction SET number_of_reportsremaining = :reportsdownloaded WHERE customer_id = :customerId ")
	int updateCustomerTransaction(@Param("customerId") Long customerId,@Param("reportsdownloaded") int reportsdownloaded );
	
}
