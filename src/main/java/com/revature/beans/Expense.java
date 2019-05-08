package com.revature.beans;

import java.time.LocalTime;

public class Expense {
	
	private int expenseID;
	private double amount;
	private LocalTime submittedAt;
	private LocalTime resolvedAt;
	private String desciption;
	private String receipt;
	private User author;
	private User resolver;
	private String status;
	private String type;
	
	
	public int getExpenseID() {
		return expenseID;
	}


	public void setExpenseID(int expenseID) {
		this.expenseID = expenseID;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public LocalTime getSubmittedAt() {
		return submittedAt;
	}


	public void setSubmittedAt(LocalTime submittedAt) {
		this.submittedAt = submittedAt;
	}


	public LocalTime getResolvedAt() {
		return resolvedAt;
	}


	public void setResolvedAt(LocalTime resolvedAt) {
		this.resolvedAt = resolvedAt;
	}


	public String getDesciption() {
		return desciption;
	}


	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}


	public String getReceipt() {
		return receipt;
	}


	public void setReceipt(String receipt) {
		this.receipt = receipt;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public User getResolver() {
		return resolver;
	}


	public void setResolver(User resolver) {
		this.resolver = resolver;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((desciption == null) ? 0 : desciption.hashCode());
		result = prime * result + expenseID;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolvedAt == null) ? 0 : resolvedAt.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submittedAt == null) ? 0 : submittedAt.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (desciption == null) {
			if (other.desciption != null)
				return false;
		} else if (!desciption.equals(other.desciption))
			return false;
		if (expenseID != other.expenseID)
			return false;
		if (receipt == null) {
			if (other.receipt != null)
				return false;
		} else if (!receipt.equals(other.receipt))
			return false;
		if (resolvedAt == null) {
			if (other.resolvedAt != null)
				return false;
		} else if (!resolvedAt.equals(other.resolvedAt))
			return false;
		if (resolver == null) {
			if (other.resolver != null)
				return false;
		} else if (!resolver.equals(other.resolver))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submittedAt == null) {
			if (other.submittedAt != null)
				return false;
		} else if (!submittedAt.equals(other.submittedAt))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Expense [expenseID=" + expenseID + ", amount=" + amount + ", submittedAt=" + submittedAt
				+ ", resolvedAt=" + resolvedAt + ", desciption=" + desciption + ", receipt=" + receipt + ", author="
				+ author + ", resolver=" + resolver + ", status=" + status + ", type=" + type + "]";
	}


	public Expense(int expenseID, double amount, LocalTime submittedAt, LocalTime resolvedAt, String desciption,
			String receipt, User author, User resolver, String status, String type) {
		super();
		this.expenseID = expenseID;
		this.amount = amount;
		this.submittedAt = submittedAt;
		this.resolvedAt = resolvedAt;
		this.desciption = desciption;
		this.receipt = receipt;
		this.author = author;
		this.resolver = resolver;
		this.status = status;
		this.type = type;
	}


	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
