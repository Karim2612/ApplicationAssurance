package com.insurance.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Integer year;
	
	@Column(nullable = false)
	private String make;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private Integer mileage;
	
	@Column(nullable = false)
	private Integer vinNumber;

	@Column(nullable = false)
	private String vehicleNumberPlate;
	
	@Column(nullable = false)
	private String vehicleRegisteredState;
	
	@Column(nullable = false)
	private Date createdDate;
	
	@Column(nullable = false)
	private boolean active;
	
	@ManyToMany
	@JoinTable(name = "Vehicle_Coverage", joinColumns = @JoinColumn(name = "vehicle_id"), inverseJoinColumns = @JoinColumn(name = "coverage_id"))
	private Set<Coverage> coverages = new HashSet<Coverage>();
	
	@ManyToOne
	@JoinColumn(name = "policy_id")
	private Policy policy;

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	// Constructors

	public Vehicle() {
	}

	public Vehicle(Integer id, Integer year, String make, String model, Integer mileage, Integer vinNumber,
			String vehicleNumberPlate,String vehicleRegisteredState, Date createdDate,boolean active, Policy policy) {
		super();
		this.id = id;
		this.year = year;
		this.model = model;
		this.mileage = mileage;
		this.vinNumber = vinNumber;
		this.make = make;
		this.vehicleNumberPlate = vehicleNumberPlate;
		this.vehicleRegisteredState = vehicleRegisteredState;
		this.createdDate = createdDate;
		this.active = active;
		this.policy = policy;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public Integer getVinNumber() {
		return vinNumber;
	}

	public void setVinNumber(Integer vinNumber) {
		this.vinNumber = vinNumber;
	}
	
	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
	public String getVehicleNumberPlate() {
		return vehicleNumberPlate;
	}

	public void setVehicleNumberPlate(String vehicleNumberPlate) {
		this.vehicleNumberPlate = vehicleNumberPlate;
	}

	
	public String getVehicleRegisteredState() {
		return vehicleRegisteredState;
	}

	public void setVehicleRegisteredState(String vehicleRegisteredState) {
		this.vehicleRegisteredState = vehicleRegisteredState;
	}

	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	


	
	public Set<Coverage> getCoverages() {
		return coverages;
	}

	public void setCoverages(Set<Coverage> coverages) {
		this.coverages = coverages;
	}

	public void addCoverage(Coverage coverage) {
		coverages.add(coverage);
	}
	
	public void removeCoverage(Coverage coverage) {
		coverages.remove(coverage);
	}


	@Override
	public String toString() {
		return "Vehicle " + "[id=" + id + ", Model=" + model + ", Make=" + make
				+ ", mileage=" + mileage + ", vinNumber=" + vinNumber + ", year =" + year +
				", vehicleNumberPlate=" + vehicleNumberPlate +", vehicleRegisteredState=" + vehicleRegisteredState +
				", createdDate=" + createdDate +", active=" + active +"]";
	}
}
