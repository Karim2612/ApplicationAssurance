package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.TrafficViolationCode;

public interface TrafficViolationCodeRepository extends JpaRepository<TrafficViolationCode, Integer>{

	@Query("SELECT p FROM TrafficViolationCode p WHERE CONCAT(p.id, ' ', p.trafficViolationQuestion, ' ', p.trafficViolationCode, ' ', p.trafficViolatonPoint, ' ',p.codeDescription) LIKE %?1%")
	public Page<TrafficViolationCode> findAll(String keyword, Pageable pageable);
}
