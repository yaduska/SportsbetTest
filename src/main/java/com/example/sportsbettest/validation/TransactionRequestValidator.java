package com.example.sportsbettest.validation;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.sportsbettest.dto.Customer;
import com.example.sportsbettest.dto.TransactionRequest;
import com.example.sportsbettest.exception.InvalidTicketException;

@Component
public class TransactionRequestValidator implements Validator<TransactionRequest> {

	/**
	 * Validates the transaction request.
	 * 
	 * @param request the TransactionRequest to be validated.
	 */
	@Override
	public void validate(TransactionRequest request) {
		if (request.getTransactionId() <= 0) {
			throw new InvalidTicketException("Transaction Id must be positive");
		}

		List<Customer> customers = request.getCustomers();
		if (customers == null || customers.isEmpty()) {
			throw new InvalidTicketException("Customer list cannot be null or empty");
		}
		
        // validating age and name of all customers
		for (Customer customer : customers) {
			validateCustomerName(customer.getName());
			validateAge(customer.getAge());
		}
	}
   
	// Validates the customer name by checking if it is null or empty
	private void validateCustomerName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new InvalidTicketException("Customer name cannot be null or empty");
		}
	}
    
	// Validates the customer age by checking if it is less than 150 and greater than 0
	private void validateAge(int age) {
		if (age < 0 || age > 150) {
			throw new InvalidTicketException("Invalid age: " + age + ". Age must be between 0 and 150");
		}
	}

}
