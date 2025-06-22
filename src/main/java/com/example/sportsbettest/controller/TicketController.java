package com.example.sportsbettest.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sportsbettest.dto.TransactionRequest;
import com.example.sportsbettest.dto.TransactionResponse;
import com.example.sportsbettest.service.TicketCalculatorService;


/**
 * <p>
 * Rest controller which is having end point to accept the transaction requests with
 * transaction id and the customer details
 * and return the response back with ticket summary based of the ticket type
 * and the total costs
 * <p>
 */
@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
	
	private TicketCalculatorService ticketCalculatorService;
	
	
	public TicketController(TicketCalculatorService ticketCalculatorService) {
		this.ticketCalculatorService = ticketCalculatorService;
	}
   
	/**
	 * <p>
	 * End point which is providing the ticket summary as transaction id with total costs 
	 * per ticketType and the total costs of all ticket types 
	 * @param transactionRequest
	 * @return transactionResponse
	 */
   @PostMapping("/summary")
	public ResponseEntity<TransactionResponse> getResponseEntity(@RequestBody TransactionRequest transactionRequest){
		return new ResponseEntity<>(ticketCalculatorService.returnResponse(transactionRequest),HttpStatus.OK);
	}

}
