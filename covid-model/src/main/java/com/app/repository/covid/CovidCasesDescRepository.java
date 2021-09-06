package com.app.repository.covid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.CovidCasesDescEntity;

public interface CovidCasesDescRepository extends JpaRepository<CovidCasesDescEntity, Long> {
	
	// @transactional and modifying annotation need to be added 
	@Transactional
	@Modifying
	@Query("DELETE  FROM CovidCasesDescEntity d WHERE d.description = :desc")
	int deleteDescWithCondition(String desc);
	
	// Complete the JPQL below
	@Query("SELECT description FROM CovidCasesDescEntity")
	List<String> findDuplicateNdelete();
	

}
