package com.insurance.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.insurance.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	@Query("SELECT p FROM Vehicle p WHERE CONCAT(p.id, ' ', p.year, ' ', p.model, ' ', p.make, ' ', p.mileage, ' ', p.vinNumber, ' ',p.vehicleNumberPlate, ' ',p.vehicleRegisteredState, ' ',p.createdDate, ' ',p.active, ' ', p.policy.id) LIKE %?1%")
	public Page<Vehicle> findAll(String keyword, Pageable pageable);
}

