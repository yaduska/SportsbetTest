package com.example.sportsbettest.strategy;

import java.math.BigDecimal;

import com.example.sportsbettest.properties.TicketProperties;


/**
 * calculates price for adults
 */
public class AdultTicketStrategy implements TicketPriceStrategy {

	private final BigDecimal adultPrice;
	
	
	public AdultTicketStrategy(TicketProperties ticketProperties) {
		this.adultPrice= BigDecimal.valueOf (ticketProperties.getPrice().get("adult"));
	}
	
	/**
	 * @param count of total adults
	 * @return calculated total price for the adult customers
	 */
	@Override
	public BigDecimal calculatePrice(int quantity) {
		
		return adultPrice.multiply(BigDecimal.valueOf(quantity));
	}

}
