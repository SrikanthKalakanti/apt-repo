package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.APTInput;

@Repository
@Transactional
public interface APTRespository extends JpaRepository<APTInput, Long> {
	
	@Query("SELECT a FROM APTInput a where a.clientId=?1")
	List<APTInput> fetchByClientId(@Param("clientId") Long clientId);
	
}
