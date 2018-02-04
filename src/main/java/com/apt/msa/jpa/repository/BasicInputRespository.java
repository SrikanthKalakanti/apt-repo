package com.apt.msa.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.apt.msa.entity.BasicInput;

@Repository
@Transactional
public interface BasicInputRespository extends JpaRepository<BasicInput, Long> {
	
	
}
