package com.revature.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(value = {"author", "resolver"})

public class Expense {
	
	private int expenseID;
	private double amount;
	private String submittedAt;
	private String resolvedAt;
	private String desciption;
	private String receipt;
	
	private User author;
	private String authorName;
	private int authorId; // specifically used for jackson
	private User resolver;
	private String resolverName;
	private String status;
	private String type;
	
	
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public int getExpenseID() {
		return expenseID;
	}
	
	public String getResolverName() {
		return resolverName;
	}
	
	public String getAuthorName() {
		return authorName;
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


	public String getSubmittedAt() {
		return submittedAt;
	}


	public void setSubmittedAt(String submittedAt) {
		this.submittedAt = submittedAt;
	}


	public String getResolvedAt() {
		return resolvedAt;
	}


	public void setResolvedAt(String timestamp) {
		this.resolvedAt = timestamp;
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
		this.authorName = author.getFirstname().concat(author.getLastname());
	}


	public User getResolver() {
		return resolver;
	}


	public void setResolver(User resolver) {
		this.resolver = resolver;
		System.out.println(resolver);
		System.out.println(resolver.getFirstname() + resolver.getLastname());
		this.resolverName = resolver.getFirstname() + resolver.getLastname();
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
	public String toString() {
		return "Expense [expenseID=" + expenseID + ", amount=" + amount + ", submittedAt=" + submittedAt
				+ ", resolvedAt=" + resolvedAt + ", desciption=" + desciption + ", receipt=" + receipt + ", author="
				+ author + ", authorName=" + authorName + ", resolver=" + resolver + ", resolverName=" + resolverName
				+ ", status=" + status + ", type=" + type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((authorName == null) ? 0 : authorName.hashCode());
		result = prime * result + ((desciption == null) ? 0 : desciption.hashCode());
		result = prime * result + expenseID;
		result = prime * result + ((receipt == null) ? 0 : receipt.hashCode());
		result = prime * result + ((resolvedAt == null) ? 0 : resolvedAt.hashCode());
		result = prime * result + ((resolver == null) ? 0 : resolver.hashCode());
		result = prime * result + ((resolverName == null) ? 0 : resolverName.hashCode());
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
		if (authorName == null) {
			if (other.authorName != null)
				return false;
		} else if (!authorName.equals(other.authorName))
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
		if (resolverName == null) {
			if (other.resolverName != null)
				return false;
		} else if (!resolverName.equals(other.resolverName))
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

	public Expense(int expenseID, double amount, String submittedAt, String resolvedAt, String desciption,
			String receipt, User author, User resolver, String status, String type, int authorId) {
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
		this.authorId = authorId;
		this.authorName = author.getFirstname().concat(author.getLastname());
		this.resolverName = resolver.getFirstname().concat(resolver.getLastname());
	}


	public Expense() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
