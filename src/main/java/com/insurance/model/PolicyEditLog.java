package com.insurance.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PolicyEditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String editedTableName;

	@Column(nullable = false)
	private Date editedDate;

	@Column(nullable = false, columnDefinition = "varchar(50)")
	private String editedBy;

	@Column(columnDefinition = "text")
	private String additionalInfos;

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

	public PolicyEditLog() {
	}

	public PolicyEditLog(Integer id, String editedTableName, Date editedDate, String editedBy, String additionalInfos,
			Policy policy) {
		super();
		this.id = id;
		this.editedTableName = editedTableName;
		this.editedDate = editedDate;
		this.editedBy = editedBy;
		this.additionalInfos = additionalInfos;
		this.policy = policy;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEditedTableName() {
		return editedTableName;
	}

	public void setEditedTableName(String editedTableName) {
		this.editedTableName = editedTableName;
	}

	public Date getEditedDate() {
		return editedDate;
	}

	public void setEditedDate(Date editedDate) {
		this.editedDate = editedDate;
	}

	public String getEditedBy() {
		return editedBy;
	}

	public void setEditedBy(String editedBy) {
		this.editedBy = editedBy;
	}

	public String getAdditionalInfos() {
		return additionalInfos;
	}

	public void setAdditionalInfos(String additionalInfos) {
		this.additionalInfos = additionalInfos;
	}

	@Override
	public String toString() {
		return "PolicyEditLog " + "[id=" + id + ", editedTableName=" + editedTableName + ", editedDate=" + editedDate
				+ ", editedBy=" + editedBy + ", additionalInfos=" + additionalInfos + "]";
	}
}
