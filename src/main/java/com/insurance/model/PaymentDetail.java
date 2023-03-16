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
public class PaymentDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private Date paidDate;
	
	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private String paymentMethod;

	@Column(columnDefinition = "text")
	private String payerFirstName;
	
	@Column(columnDefinition = "text")
	private String payerLastName;
	
	@Column(nullable = false)
	private Date createdDate;

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	// Constructors

	public PaymentDetail() {
	}

	public PaymentDetail(Integer id, Date paidDate, double amount, String paymentMethod, String payerFirstName, String payerLastName, Date createdDate,
			Bill bill) {
		super();
		this.id = id;
		this.paidDate = paidDate;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.payerFirstName = payerFirstName;
		this.payerLastName = payerLastName;
		this.createdDate = createdDate;
		this.bill = bill;
	}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPayerFirstName() {
		return payerFirstName;
	}

	public void setPayerFirstName(String payerFirstName) {
		this.payerFirstName = payerFirstName;
	}
	
	public String getPayerLastName() {
		return payerLastName;
	}

	public void setPayerLastName(String payerLastName) {
		this.payerLastName = payerLastName;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "PaymentDetail " + "[id=" + id + ", paidDate=" + paidDate + ", amount=" + amount
				+ ", paymentMethod=" + paymentMethod + ", payerFirstName=" + payerFirstName + ", payerLastName=" + payerLastName + ", createdDate =" + createdDate + "]";
	}
}

