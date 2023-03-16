package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.PolicyEditLog;

public interface PolicyEditLogRepository extends JpaRepository<PolicyEditLog, Integer>{

	@Query("SELECT p FROM PolicyEditLog p WHERE CONCAT(p.id, ' ', p.editedTableName, ' ', p.editedBy, ' ', p.policy.id) LIKE %?1%")
	public Page<PolicyEditLog> findAll(String keyword, Pageable pageable);
}

