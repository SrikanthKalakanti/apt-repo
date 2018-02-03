package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.AssetInput;

@Repository
@Transactional
public interface AssetInputRespository extends JpaRepository<AssetInput, Long> {
	
	@Query("FROM asset_input where client_id=?1")
	List<AssetInput> fetchByClientId(@Param("clientId") Long clientId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE asset_input SET name = :name, value = :value, depreciation_rate=:depreciationRate, promoter_margin=:promoterMargin WHERE client_id = :clientId and asset_id =:assetId")
	int updateAssetByClient(@Param("name") String name, @Param("value") Double value,
			@Param("depreciationRate") Double depreciationRate, @Param("promoterMargin") Double promoterMargin, @Param("clientId") Long clientId,@Param("assetId") Long assetId );
	
	
}
