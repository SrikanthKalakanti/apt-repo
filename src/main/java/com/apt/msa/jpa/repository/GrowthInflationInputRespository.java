package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Modifying
	@Query("delete from growth_inflation_input WHERE client_id = :clientId and growth_iflation_id =:growthIflationId")
	int deleteAssetByIdAndClient(@Param("clientId") Long clientId, @Param("growthIflationId") Long growthIflationId);
	
	@Query("FROM growth_inflation_input where client_id=?1")
	GrowthInflationInput fetchByClientId(@Param("clientId") Long clientId);
	
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE growth_inflation_input SET answer1 =:answer1, answer2 =:answer2, answer3 =:answer3, number_of_years =:numberOfYears, array1=:array1, array2=:array2 WHERE client_id = :clientId and growth_iflation_id =:growthIflationId")
	int updateGrowthInflationInput( @Param("answer1") String answer1, @Param("answer2") String answer2,
			@Param("answer3") String answer3, @Param("numberOfYears") Double numberOfYears,
			@Param("array1") String array1, @Param("array2") String array2, @Param("clientId") Long clientId,
			@Param("growthIflationId") Long growthIflationId );
	
	
}
