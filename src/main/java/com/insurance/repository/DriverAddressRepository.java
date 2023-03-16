package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.DriverAddress;

public interface DriverAddressRepository extends JpaRepository<DriverAddress, Integer>{

	@Query("SELECT p FROM DriverAddress p WHERE CONCAT(p.id, ' ', p.address, ' ', p.city, ' ',p.state, ' ',p.zipCode, ' ',p.country, ' ',p.isItGarageAdress, ' ', p.driver.id) LIKE %?1%")
	public Page<DriverAddress> findAll(String keyword, Pageable pageable);
}