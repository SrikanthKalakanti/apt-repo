package com.apt.msa.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.ExpensesInput;

@Repository
@Transactional
public interface ExpensesInputRespository extends JpaRepository<ExpensesInput, Long> {
	
	@Query("FROM expenses_input where client_id=?1 and cma_nomenclature not like '%Selling Price%' ")
	List<ExpensesInput> fetchByClientId(@Param("clientId") Long clientId);
	
	@Query("FROM expenses_input where client_id=?1 and cma_nomenclature like '%Selling Price%' ")
	ExpensesInput fetchByClientIdAndSellingPrice(@Param("clientId") Long clientId);
	
	@Query("FROM expenses_input where client_id=?1")
	List<ExpensesInput> fetchAllByClientId(@Param("clientId") Long clientId);
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE expenses_input SET amount_ininr = :amountInINR, cma_nomenclature=:cmaNomenclature, expenditure_per=:expenditurePer, nomenclature=:nomenclature  WHERE client_id = :clientId and expenses_input_id =:expensesInputId")
	int updateExpensesByIdClient( @Param("amountInINR") Double amountInINR, @Param("expenditurePer") String expenditurePer,
			@Param("cmaNomenclature") String cmaNomenclature, @Param("nomenclature") String nomenclature, @Param("clientId") Long clientId,@Param("expensesInputId") Long expensesInputId );
	
	@Modifying
	@Query("delete from expenses_input WHERE client_id = :clientId and expenses_input_id =:expensesInputId")
	int deleteAssetByIdAndClient(@Param("clientId") Long clientId, @Param("expensesInputId") Long expensesInputId);		
	
	
}
