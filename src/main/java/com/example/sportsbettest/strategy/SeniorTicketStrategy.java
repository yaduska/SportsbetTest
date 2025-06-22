package com.example.sportsbettest.strategy;

import java.math.BigDecimal;

import com.example.sportsbettest.properties.TicketProperties;

/**
 * calculate the ticket price for senior customers
 */

public class SeniorTicketStrategy implements TicketPriceStrategy{
	
    private final BigDecimal adultPrice;
	private final BigDecimal discountPercent;
	private static final BigDecimal FULL_PERCENT = BigDecimal.valueOf(100);
	
	public SeniorTicketStrategy(TicketProperties ticketProperties) {
		this.adultPrice= BigDecimal.valueOf(ticketProperties.getPrice().get("adult"));
		this.discountPercent= BigDecimal.valueOf(ticketProperties.getSeniorDiscount().getPercent());
	}
	
	/**calculate the price for the senior type customers
	 * <p> subtract the discounted amount from the adult price as
	 * it gives the senior customer ticket price
	 * @param total count of seniors
	 * @return calculated total price for the senior customers
	 */
	
	@Override
	public BigDecimal calculatePrice(int quantity) {
		return adultPrice.multiply((FULL_PERCENT.subtract(discountPercent)).multiply(BigDecimal.valueOf(quantity)).divide(FULL_PERCENT));
	}

}
