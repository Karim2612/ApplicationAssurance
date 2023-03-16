package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.PaymentDetail;

public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Integer>{

	@Query("SELECT p FROM PaymentDetail p WHERE CONCAT(p.id, ' ', p.paidDate, ' ', p.amount, ' ',p.paymentMethod, ' ',p.payerFirstName, ' ',p.payerLastName, ' ',p.createdDate, ' ', p.bill.id) LIKE %?1%")
	public Page<PaymentDetail> findAll(String keyword, Pageable pageable);
}

