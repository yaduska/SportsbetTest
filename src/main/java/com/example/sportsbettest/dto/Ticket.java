package com.example.sportsbettest.dto;

import java.math.BigDecimal;

import com.example.sportsbettest.model.TicketType;

/**
 * Ticket dto
 * holding ticket type, quantity of customers, total cost 
 */

public class Ticket {

	private TicketType ticketType;
	private int quantity;
	private BigDecimal totalCost;
	
	
	public Ticket() {}
	
	public Ticket(TicketType ticketType, int quantity, BigDecimal totalCost) {
		super();
		this.ticketType = ticketType;
		this.quantity = quantity;
		this.totalCost = totalCost;
	}
	
	public TicketType getTicketType() {
		return ticketType;
	}
	public void setTicketType(TicketType ticketType) {
		this.ticketType = ticketType;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
}
