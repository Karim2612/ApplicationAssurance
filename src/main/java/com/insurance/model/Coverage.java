package com.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coverage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "varchar(255)")
	private String coverageName;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String coverageGroup;

	@Column(nullable = false, columnDefinition = "varchar(20)")
	private String code;

	@Column(nullable = false)
	private boolean isPolicyCoverage;

	@Column(nullable = false)
	private boolean isVehicleCoverage;

	@Column(columnDefinition = "varchar(255)")
	private String description;
	
	public Coverage() {}
	
	public Coverage(Integer id) {
		this.id = id;
	}
	
	public Coverage(String coverageName, String coverageGroup, String code, boolean isPolicyCoverage, boolean isVehicleCoverage, String description) {
		super();
		this.coverageName = coverageName;
		this.coverageGroup = coverageGroup;
		this.code = code;
		this.isPolicyCoverage = isPolicyCoverage;
		this.isVehicleCoverage = isVehicleCoverage;
		this.description = description;
	}
	
	public String getCoverageName() {
		return coverageName;
	}

	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}

	public String getCoverageGroup() {
		return coverageGroup;
	}

	public void setCoverageGroup(String coverageGroup) {
		this.coverageGroup = coverageGroup;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isPolicyCoverage() {
		return isPolicyCoverage;
	}

	public void setPolicyCoverage(boolean isPolicyCoverage) {
		this.isPolicyCoverage = isPolicyCoverage;
	}

	public boolean isVehicleCoverage() {
		return isVehicleCoverage;
	}

	public void setVehicleCoverage(boolean isVehicleCoverage) {
		this.isVehicleCoverage = isVehicleCoverage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}



	@Override
	public String toString() {
		return "Coverage " +
				"[id=" + id +
				", coverageName=" + coverageName +
				", coverageGroup=" + coverageGroup +
				", code=" + code +
				", isPolicyCoverage=" + isPolicyCoverage +
				", isVehicleCoverage=" + isVehicleCoverage +
				", description=" + description +
				"]";
	}
	
	
}
