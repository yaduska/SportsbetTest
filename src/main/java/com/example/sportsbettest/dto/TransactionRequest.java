package com.example.sportsbettest.dto;

import java.util.List;

/**
 * Request dto 
 * holding transaction id and customer details
 */
public class TransactionRequest {

	private int transactionId;
	private List<Customer> customers;
	
	public TransactionRequest() {}

	public TransactionRequest(int transactionId, List<Customer> customers) {
		this.transactionId = transactionId;
		this.customers = customers;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	
	
}
