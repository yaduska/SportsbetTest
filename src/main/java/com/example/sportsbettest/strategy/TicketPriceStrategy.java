package com.example.sportsbettest.strategy;

import java.math.BigDecimal;
/**
 * strategy interface to calculate the price of the customers
 */

public interface TicketPriceStrategy {
	
	/**
	 * calculate the total price of the given customers applicable to the ticket type
	 * @param quantity of the customers
	 * @return total price of the tickets
	 */

	BigDecimal calculatePrice(int quantity) ;
}
