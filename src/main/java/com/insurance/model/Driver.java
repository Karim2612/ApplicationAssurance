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
public class Driver {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String firstName;
	
	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private Date doB;

	@Column(nullable = false)
	private Integer phoneNumber;
	
	@Column(nullable = false)
	private Date createdDate;
	
	@ManyToMany
	@JoinTable(name = "Driver_TrafficViolation_Record", joinColumns = @JoinColumn(name = "driver_id"), inverseJoinColumns = @JoinColumn(name = "trafficViolationCode_id"))
	private Set<TrafficViolationCode> trafficViolationCodes = new HashSet<TrafficViolationCode>();

	@ManyToMany
	@JoinTable(name = "vehicle_driver", joinColumns = @JoinColumn(name = "driver_id"), inverseJoinColumns = @JoinColumn(name = "vehicle_id"))
	private Set<Vehicle> vehicles = new HashSet<Vehicle>();
	
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

	public Driver() {
	}

	public Driver(Integer id, String firstName, String lastName, Date doB, Integer phoneNumber,Date createdDate,
			Policy policy) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.doB = doB;
		this.phoneNumber = phoneNumber;
		this.createdDate = createdDate;
		this.policy = policy;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDoB() {
		return doB;
	}

	public void setDoB(Date doB) {
		this.doB = doB;
	}

	public Integer getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Integer phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Set<TrafficViolationCode> getTrafficViolationCodes() {
		return trafficViolationCodes;
	}

	public void setTrafficViolationCodes(Set<TrafficViolationCode> trafficViolationCode) {
		this.trafficViolationCodes = trafficViolationCode;
	}

	public void addTrafficViolationCode(TrafficViolationCode trafficViolationCode) {
		trafficViolationCodes.add(trafficViolationCode);
	}
	
	public void removeTrafficViolationCode(TrafficViolationCode trafficViolationCode) {
		trafficViolationCodes.remove(trafficViolationCode);
	}
	public Set<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(Set<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
	}

	@Override
	public String toString() {
		return "Driver " + "[id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", doB=" + doB + ", phoneNumber=" + phoneNumber + ", createdDate =" + createdDate + "]";
	}
}
