package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.Client;

@Repository
@Transactional
public interface ClientRespository extends JpaRepository<Client, Long> {
	
	@Query("SELECT c FROM Client c where c.clientId=?1")
	List<Client> fetchByUserName(@Param("clientId") Long clientId);
	
	@Query("SELECT clientId,customerId FROM Client c WHERE c.customerId=?1")
	List<Client> fetchByCustomerId(@Param("customerId") Long customerId);
	
}
