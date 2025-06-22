package com.example.sportsbettest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.sportsbettest.factory.TicketStrategyFactory;
import com.example.sportsbettest.properties.TicketProperties;

/**
 * App configuration to provide beans
 */
@Configuration
public class AppConfig {
	
	private final TicketProperties ticketProperties;
	
	public AppConfig(TicketProperties ticketProperties) {
		this.ticketProperties= ticketProperties;
	}
	
	/**
	 * <p> It is used to create ticketpricestrategy instances
	 * @return new strategy factory bean
	 */
	
	@Bean
	public TicketStrategyFactory getTicketStrategy() {
		return new TicketStrategyFactory(ticketProperties);
	}
	

}
