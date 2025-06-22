package com.example.sportsbettest.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.sportsbettest.dto.Customer;
import com.example.sportsbettest.dto.Ticket;
import com.example.sportsbettest.dto.TransactionRequest;
import com.example.sportsbettest.dto.TransactionResponse;
import com.example.sportsbettest.exception.InvalidTicketException;
import com.example.sportsbettest.factory.TicketStrategyFactory;
import com.example.sportsbettest.model.TicketType;
import com.example.sportsbettest.properties.TicketProperties;
import com.example.sportsbettest.validation.TransactionRequestValidator;

/**
 * Calculating the total cost of the tickets based on the customer type
 */
@Service
public class TicketCalculatorService {

	private final TicketProperties ticketProperties;
	private final TicketStrategyFactory ticketStratergyFactory;
	private final TransactionRequestValidator transactionRequestValidator;
	private final Logger logger= LoggerFactory.getLogger(TicketCalculatorService.class);
	
	
	public TicketCalculatorService(TicketProperties ticketProperties, TicketStrategyFactory ticketStratergyFactory,TransactionRequestValidator transactionRequestValidator) {
		this.ticketProperties = ticketProperties;
		this.ticketStratergyFactory = ticketStratergyFactory;
		this.transactionRequestValidator= transactionRequestValidator;
	}
	
	
	
	/**
	 * <p> calculates the ticket price based on the request after the validation of the request
	 * @param transactionRequest holding the transaction id and customer details
	 * @return TransactionResponse holding the total ticket cost and the customer type 
	 * and sorts the tickets based on Ticket Types
	 */
	public TransactionResponse returnResponse(TransactionRequest transactionRequest) {
		transactionRequestValidator.validate(transactionRequest);
		logger.info("Returning response with calculated cost of tickets with transactionid- "+transactionRequest.getTransactionId());
		Map<TicketType, Integer> ticketTypeCounts =transactionRequest.getCustomers().stream().collect(Collectors.groupingBy(this::getTicketType, Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
		List<Ticket> tickets= new ArrayList<>();
		BigDecimal totalCostOfAllTickets =BigDecimal.ZERO;
		for(Map.Entry<TicketType, Integer> ticketTypeCount: ticketTypeCounts .entrySet()) {
			BigDecimal ticketTypeTotalCost= ticketStratergyFactory.getStrategy(ticketTypeCount.getKey()).calculatePrice(ticketTypeCount.getValue());
			tickets.add(new Ticket(ticketTypeCount.getKey(),ticketTypeCount.getValue(),ticketTypeTotalCost.setScale(2)));
			totalCostOfAllTickets = totalCostOfAllTickets .add(ticketTypeTotalCost);
			
		}
		tickets.sort(Comparator.comparing(ticket->ticket.getTicketType().name()));
		return new TransactionResponse(transactionRequest.getTransactionId(),tickets,totalCostOfAllTickets.setScale(2) );
		
		
	}
	/**
	 * <p> Gets the customer's age and return the corresponding ticketType
	 * @param customer
	 * @return Type of the ticket
	 */
	public TicketType getTicketType(Customer customer) {
		int age= customer.getAge();
		if(age>=ticketProperties.getAgeLimit().get("senior")) return TicketType.Senior;
		else if(age>=ticketProperties.getAgeLimit().get("adult")) return TicketType.Adult;
		else if(age>=ticketProperties.getAgeLimit().get("teen")) return TicketType.Teen;
		else  return TicketType.Children;
		
		
	}
	
	
	
	
	
}
