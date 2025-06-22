package com.example.sportsbettest.strategy;

import java.math.BigDecimal;

import com.example.sportsbettest.properties.TicketProperties;

/**
 * calculates price for children
 */
public class ChildrenTicketStrategy implements TicketPriceStrategy {
	
	private final BigDecimal childrenPrice;
	private final int threshold;
	private final BigDecimal discountPercent;
	private static final BigDecimal FULL_PERCENT = BigDecimal.valueOf(100);
	
	public ChildrenTicketStrategy(TicketProperties ticketProperties) {
		this.childrenPrice= BigDecimal.valueOf( ticketProperties.getPrice().get("children"));
		this.threshold= ticketProperties.getChildrenDiscount().getThreshold();
		this.discountPercent= BigDecimal.valueOf(ticketProperties.getChildrenDiscount().getPercent());
		
	}
	
	/**
	 * calculates total price for the children
	 * <p> 
	 * If the children's quantity is equal or more than the threshold value 
	 * then discount price will be reduced from the base price
	 * else return the base price
	 * @param total count of children
	 * @return calculated total price for children
	 */

	@Override
	public BigDecimal calculatePrice(int quantity) {
		BigDecimal basePrice= BigDecimal.valueOf(quantity).multiply(childrenPrice);
		if(quantity>=threshold) {
			BigDecimal discountAmount= basePrice.multiply(discountPercent).divide(FULL_PERCENT);
			return basePrice.subtract(discountAmount);
		}
		return basePrice;
	}

}
