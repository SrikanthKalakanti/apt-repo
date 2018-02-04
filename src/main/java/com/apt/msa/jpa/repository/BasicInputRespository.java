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
	
	@Modifying(clearAutomatically = true)
	@Query("UPDATE basic_input SET business_commencement_date = :businessCommencementDate, cash_credit_amount_required = :cashCreditAmountRequired, fixed_expenses_per_month = :fixedExpensesPerMonth, "
			+ "interest_rate_for_cc = :interestRateForCc, moratorium = :moratorium, name_of_the_business = :nameOfTheBusiness, number_of_days_inamonth = :numberOfDaysInAMonth, payment_date = :paymentDate, "
			+ "production_per_month_in_units = :productionPerMonthInUnits,status = :status, tenure_of_term_loan =:tenureOfTermLoan,term_loan_first_disbursement_date = :termLoanFirstDisbursementDate  "
			+ "WHERE client_id = :clientId and basic_input_id = :basicInputId")
	int updateBasicInputByIdAndClient( 	@Param("businessCommencementDate") Date businessCommencementDate, 
									@Param("cashCreditAmountRequired") Integer cashCreditAmountRequired,
									@Param("fixedExpensesPerMonth") Integer fixedExpensesPerMonth, 
									@Param("interestRateForCc") double interestRateForCc,			
									@Param("moratorium") double moratorium,
									@Param("nameOfTheBusiness") String nameOfTheBusiness, 
									@Param("numberOfDaysInAMonth") Integer numberOfDaysInAMonth,			
									@Param("paymentDate") Integer paymentDate,
									@Param("productionPerMonthInUnits") Integer productionPerMonthInUnits, 
									@Param("status") Integer status,			
									@Param("tenureOfTermLoan") Integer tenureOfTermLoan, 
									@Param("termLoanFirstDisbursementDate") Date termLoanFirstDisbursementDate,
									
									@Param("clientId") Long clientId,
									@Param("basicInputId") Long basicInputId );

	@Modifying
	@Query("delete from basic_input WHERE client_id = :clientId and basic_input_id = :basicInputId")
	int deleteBasicByIdAndClient(@Param("clientId") Long clientId, @Param("basicInputId") Long basicInputId);
	
}
