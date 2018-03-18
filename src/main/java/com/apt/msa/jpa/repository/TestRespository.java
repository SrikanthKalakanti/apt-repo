package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.Test;

@Repository
@Transactional
public interface TestRespository extends JpaRepository<Test, Long> {
	
	/*@Query("SELECT c FROM Test c where c.testId=?1")
	List<Test> fetchByTestId(@Param("testId") Long testId);*/
	
}
