package com.insurance.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Policy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, columnDefinition = "nvarchar(20)")
	private String policyNumber;
	
	@Column(nullable = false)
	private Date policyEffectiveDate;

	@Column(nullable = false)
	private Date policyExpireDate;

	@Column(nullable = false, columnDefinition = "varchar(100)")
	private String paymentOption;

	@Column(nullable = false)
	private double totalAmount;

	@Column(nullable = false)
	private boolean active;

	@Column(columnDefinition = "text")
	private String additionalInfos;

	@Column(nullable = false)
	private Date creationDate;

	@ManyToMany
	@JoinTable(name = "policy_coverage", joinColumns = @JoinColumn(name = "policy_id"), inverseJoinColumns = @JoinColumn(name = "coverage_id"))
	private Set<Coverage> coverages = new HashSet<Coverage>();
	
	@OneToMany(mappedBy = "policy", cascade = CascadeType.ALL)
	private List<PolicyEditLog> pEditLogs = new ArrayList<PolicyEditLog>();
	
	public List<PolicyEditLog> getpEditLogs() {
		return pEditLogs;
	}

	public void setpEditLogs(List<PolicyEditLog> pEditLogs) {
		this.pEditLogs = pEditLogs;
	}

	public Policy() {}
	
	public Policy(Integer id) {
		super();
		this.id = id;
	}

	public Policy(String policyNumber, Date policyEffectiveDate, Date policyExpireDate, String paymentOption, double totalAmount, boolean active, String additionalInfos, Date creationDate) {
		super();
		this.policyNumber = policyNumber;
		this.policyEffectiveDate = policyEffectiveDate;
		this.policyExpireDate = policyExpireDate;
		this.paymentOption = paymentOption;
		this.totalAmount = totalAmount;
		this.active = active;
		this.additionalInfos = additionalInfos;
		this.creationDate = creationDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

		
	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public Date getPolicyEffectiveDate() {
		return policyEffectiveDate;
	}

	public void setPolicyEffectiveDate(Date policyEffectiveDate) {
		this.policyEffectiveDate = policyEffectiveDate;
	}

	public Date getPolicyExpireDate() {
		return policyExpireDate;
	}

	public void setPolicyExpireDate(Date policyExpireDate) {
		this.policyExpireDate = policyExpireDate;
	}

	public String getPaymentOption() {
		return paymentOption;
	}

	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getAdditionalInfos() {
		return additionalInfos;
	}

	public void setAdditionalInfos(String additionalInfos) {
		this.additionalInfos = additionalInfos;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
		return "Policy " +
				"[id=" + id +
				", policyNumber=" + policyNumber +
				", policyEffectiveDate=" + policyEffectiveDate +
				", policyExpireDate=" + policyExpireDate +
				", paymentOption=" + paymentOption +
				", totalAmount=" + totalAmount +
				", active=" + active +
				", additionalInfos=" + additionalInfos +
				", creationDate=" + creationDate +
				"]";
	}
}
