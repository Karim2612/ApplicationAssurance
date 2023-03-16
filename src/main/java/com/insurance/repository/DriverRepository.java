package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{

	@Query("SELECT p FROM Driver p WHERE CONCAT(p.id, ' ', p.firstName, ' ', p.lastName, ' ', p.doB, ' ', p.phoneNumber, ' ', p.createdDate, p.policy.id) LIKE %?1%")
	public Page<Driver> findAll(String keyword, Pageable pageable);
}

