package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.Coverage;

public interface CoverageRepository extends JpaRepository<Coverage, Integer> {
	@Query("SELECT p FROM Coverage p WHERE CONCAT(p.id, ' ', p.coverageName, ' ', p.coverageGroup, ' ', p.code, ' ', p.isPolicyCoverage, ' ', p.isVehicleCoverage, ' ', p.description) LIKE %?1%")
	public Page<Coverage> findAll(String keyword, Pageable pageable);
	
}
