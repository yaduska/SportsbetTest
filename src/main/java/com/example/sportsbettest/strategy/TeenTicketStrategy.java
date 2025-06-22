package com.example.sportsbettest.strategy;

import java.math.BigDecimal;

import com.example.sportsbettest.properties.TicketProperties;

/**
 * Calculate the price for teenagers
 */

public class TeenTicketStrategy implements TicketPriceStrategy {
	
	private final BigDecimal teenPrice;
	
	public TeenTicketStrategy(TicketProperties ticketProperties) {
		this.teenPrice=BigDecimal.valueOf(ticketProperties.getPrice().get("teen"));
	}
	
    /**
     * @param total count of teens
     * @return total price of teen customers
     */
	@Override
	public BigDecimal calculatePrice(int quantity) {
		return teenPrice.multiply(BigDecimal.valueOf(quantity));
	}

}
