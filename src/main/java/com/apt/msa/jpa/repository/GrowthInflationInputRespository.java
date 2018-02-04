package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.apt.msa.entity.GrowthInflationInput;

@Repository
@Transactional
public interface GrowthInflationInputRespository extends JpaRepository<GrowthInflationInput, Long> {
	
	/*@Query("SELECT a FROM GrowthInflationInput a where a.Client.clientId=?1")
	GrowthInflationInput fetchByClientId(@Param("clientId") Long clientId);*/
	
	
	
}