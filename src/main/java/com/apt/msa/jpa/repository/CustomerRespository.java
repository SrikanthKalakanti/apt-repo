package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.Customer;

@Repository
@Transactional
public interface CustomerRespository extends JpaRepository<Customer, Long> {
	
	@Query("SELECT c FROM Customer c where c.email=?1 or c.userName=?1")
	List<Customer> fetchByUserName(@Param("userName") String userName);
	
	@Query("SELECT c FROM Customer c where c.email=?1")
	List<Customer> fetchByEmail(@Param("email") String email);
	
}
