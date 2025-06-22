package com.example.sportsbettest.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * <p>
 * Global Exception Handler for controllers
 * It gives 400 bad request for invalid request
 * It gives 500 Internal server error for other exceptions
 * Converts the exceptions into HttpResponses with meaningful codes
 */
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	/**
	 * Handles when sending invalid requests
	 * @param ex the InvalidTicketException 
	 * @return response entity with error message as it's response body
	 */
	@ExceptionHandler(InvalidTicketException.class)
	public ResponseEntity<ErrorResponse> handleInvalidRequest(InvalidTicketException ex){
		logger.error("Invalid ticket Request: {} ",ex.getMessage(),ex);
		ErrorResponse error= new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
		
	}
	
    /**
     * Handles all other generic exceptions
     * @param ex the Exception
     * @return the response entity with the error message
     */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneralExceptions(Exception ex){
		logger.error("Unexpected error occured: {}",ex.getMessage(),ex);
		ErrorResponse error= new ErrorResponse("Something went wrong, Please try again later", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return  new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
}
