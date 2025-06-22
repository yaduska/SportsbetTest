package com.example.sportsbettest.exception;

/**
 * Custom runtime exception to handle the invalid ticket requests
 */
public class InvalidTicketException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidTicketException(String message) {
		super(message);
	}
	
}
