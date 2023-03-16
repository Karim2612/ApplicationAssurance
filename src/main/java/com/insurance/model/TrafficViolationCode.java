package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TrafficViolationCode {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String trafficViolationQuestion;

	@Column(nullable = false)
	private Integer trafficViolationCode;

	@Column(nullable = false)
	private double trafficViolatonPoint;

	@Column(nullable = false)
	private String codeDescription;

	
	public TrafficViolationCode() {}
	
	public TrafficViolationCode(Integer id) {
		this.id = id;
	}
	
	public TrafficViolationCode(String trafficViolationQuestion, Integer trafficViolationCode, double trafficViolatonPoint, String codeDescription, boolean isVehicleCoverage, String description) {
		super();
		this.trafficViolationQuestion = trafficViolationQuestion;
		this.trafficViolationCode = trafficViolationCode;
		this.trafficViolatonPoint = trafficViolatonPoint;
		this.codeDescription = codeDescription;
	}
	
	public String getTrafficViolationQuestion() {
		return trafficViolationQuestion;
	}

	public void setTrafficViolationQuestion(String trafficViolationQuestion) {
		this.trafficViolationQuestion = trafficViolationQuestion;
	}

	public Integer getTrafficViolationCode() {
		return trafficViolationCode;
	}

	public void setTrafficViolationCode(Integer trafficViolationCode) {
		this.trafficViolationCode = trafficViolationCode;
	}

	public double getTrafficViolatonPoint() {
		return trafficViolatonPoint;
	}

	public void setTrafficViolatonPoint(double trafficViolatonPoint) {
		this.trafficViolatonPoint = trafficViolatonPoint;
	}

	public String getCodeDescription() {
		return codeDescription;
	}

	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TrafficViolationCode " +
				"[id=" + id +
				", trafficViolationQuestion=" + trafficViolationQuestion +
				", trafficViolationCode=" + trafficViolationCode +
				", trafficViolatonPoint=" + trafficViolatonPoint +
				", codeDescription=" + codeDescription +
				"]";
	}
	
	
}
