package com.example.sportsbettest.factory;

import com.example.sportsbettest.model.TicketType;
import com.example.sportsbettest.properties.TicketProperties;
import com.example.sportsbettest.strategy.AdultTicketStrategy;
import com.example.sportsbettest.strategy.ChildrenTicketStrategy;
import com.example.sportsbettest.strategy.SeniorTicketStrategy;
import com.example.sportsbettest.strategy.TeenTicketStrategy;
import com.example.sportsbettest.strategy.TicketPriceStrategy;

/**
 * Factory class to get correct strategy based on the ticket type
 */
public class TicketStrategyFactory {
 
	private final TicketProperties ticketProperties;
	
	public TicketStrategyFactory(TicketProperties ticketProperties) {
		this.ticketProperties= ticketProperties;
	}
	
	public TicketPriceStrategy getStrategy(TicketType ticketType) {
	 return switch ( ticketType) {
			case Adult-> new AdultTicketStrategy(ticketProperties);
			case Senior-> new SeniorTicketStrategy(ticketProperties);
			case Teen-> new TeenTicketStrategy(ticketProperties);
			case Children-> new ChildrenTicketStrategy(ticketProperties);
			
			
		};
		
	}
	
}
