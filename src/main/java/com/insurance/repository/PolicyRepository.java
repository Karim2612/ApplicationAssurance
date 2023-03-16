package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Integer>{

	@Query("SELECT p FROM Policy p WHERE CONCAT(p.id, ' ', p.policyNumber) LIKE %?1%")
	public Page<Policy> findAll(String keyword, Pageable pageable);
}
