package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

	@Query("SELECT p FROM Bill p WHERE CONCAT(p.id, ' ', p.dueDate, ' ', p.minimumPayment, ' ', p.balance, ' ', p.status, ' ', p.createdDate, ' ', p.policy.id) LIKE %?1%")
	public Page<Bill> findAll(String keyword, Pageable pageable);
}

