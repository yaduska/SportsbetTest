package com.example.sportsbettest.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * response dto for the request  
 * holding transaction id, tickets and total cost of all tickets
 */

public class TransactionResponse {

	private int transactionId;
	private List<Ticket> tickets;
	private BigDecimal totalCost;
	
	public TransactionResponse() {}
	
	
	public TransactionResponse(int transactionId, List<Ticket> tickets, BigDecimal totalCost) {
		this.transactionId = transactionId;
		this.tickets = tickets;
		this.totalCost = totalCost;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public List<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
}
