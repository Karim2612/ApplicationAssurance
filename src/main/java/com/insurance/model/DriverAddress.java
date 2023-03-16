package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DriverAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, columnDefinition = "varchar(150)")
	private String address;
	
	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String city;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String state;

	@Column(nullable = false)
	private Integer zipCode;
	
	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String country;
	
	@Column(nullable = false)
	private boolean isItGarageAdress;

	@ManyToOne
	@JoinColumn(name = "driver_id")
	private Driver driver;

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	// Constructors

	public DriverAddress() {
	}

	public DriverAddress(Integer id, String address, String city, String state, Integer zipCode, String country, Boolean isItGarageAdress,
			Driver driver) {
		super();
		this.id = id;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.isItGarageAdress = isItGarageAdress;
		this.driver = driver;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public boolean getIsItGarageAdress() {
		return isItGarageAdress;
	}

	public void setIsItGarageAdress(boolean isItGarageAdress) {
		this.isItGarageAdress = isItGarageAdress;
	}

	@Override
	public String toString() {
		return "DriverAddress " + "[id=" + id + ", address=" + address + ", city=" + city
				+ ", state=" + state + ", zipCode=" + zipCode + ", country=" + country + ", isItGarageAdress =" + isItGarageAdress + "]";
	}
}