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
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Date dueDate;
	
	@Column(nullable = false)
	private double minimumPayment;

	@Column(nullable = false)
	private double balance;

	@Column(columnDefinition = "text")
	private String status;
	
	@Column(nullable = false)
	private Date createdDate;

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

	public Bill() {
	}

	public Bill(Integer id, Date dueDate, double minimumPayment, double balance, String status,Date createdDate,
			Policy policy) {
		super();
		this.id = id;
		this.dueDate = dueDate;
		this.minimumPayment = minimumPayment;
		this.balance = balance;
		this.status = status;
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

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public double getMinimumPayment() {
		return minimumPayment;
	}

	public void setMinimumPayment(double minimumPayment) {
		this.minimumPayment = minimumPayment;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "Bill " + "[id=" + id + ", dueDate=" + dueDate + ", minimumPayment=" + minimumPayment
				+ ", balance=" + balance + ", status=" + status + ", createdDate =" + createdDate + "]";
	}
}
