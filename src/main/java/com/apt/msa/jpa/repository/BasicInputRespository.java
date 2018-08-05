package com.apt.msa.jpa.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.BasicInput;

@Repository
@Transactional
public interface BasicInputRespository extends JpaRepository<BasicInput, Long> {
	
	
	/*
	 * {
basicInput.getNameOfTheBusiness(),basicInput.getBusinessCommencementDate(),
				basicInput.getTenureOfTermLoan(),basicInput.getMoratorium(), basicInput.getInterestRateForCc(),basicInput.getStatus(),
				basicInput.getCashCreditAmountRequired(),basicInput.getClientId(), basicInput.getBasicInputId()
}
	 */
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE basic_input SET name_of_the_business = :nameOfTheBusiness, business_commencement_date = :businessCommencementDate, tenure_of_term_loan =:tenureOfTermLoan, "
			+ "moratorium = :moratorium, interest_rate_for_cc = :interestRateForCc, status = :status, cash_credit_amount_required = :cashCreditAmountRequired "
			+ "WHERE client_id = :clientId and basic_input_id = :basicInputId")
	int updateBasicInputByIdAndClient(@Param("nameOfTheBusiness") String nameOfTheBusiness,
									@Param("businessCommencementDate") Date businessCommencementDate, 
									@Param("tenureOfTermLoan") Integer tenureOfTermLoan,
									@Param("moratorium") double moratorium,
									@Param("interestRateForCc") double interestRateForCc,
									@Param("status") Integer status,
									@Param("cashCreditAmountRequired") Integer cashCreditAmountRequired,
									@Param("clientId") Long clientId,
									@Param("basicInputId") Long basicInputId );

	@Modifying
	@Query("delete from basic_input WHERE client_id = :clientId and basic_input_id = :basicInputId")
	int deleteBasicByIdAndClient(@Param("clientId") Long clientId, @Param("basicInputId") Long basicInputId);
	@Query("FROM basic_input where client_id=?1")
	BasicInput fetchByClientId(@Param("clientId") Long clientId);
}
