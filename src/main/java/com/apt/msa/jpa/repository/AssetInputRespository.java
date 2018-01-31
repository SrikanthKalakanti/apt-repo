package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AssetInput;

@Repository
@Transactional
public interface AssetInputRespository extends JpaRepository<AssetInput, Long> {
	
	/*@Query("SELECT a FROM AssetInput a where a.Client.clientId=?1")
	List<AssetInput> fetchByClientId(@Param("clientId") Long clientId);*/
	
	
}
