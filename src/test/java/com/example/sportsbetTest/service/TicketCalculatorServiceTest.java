package com.example.sportsbetTest.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.example.sportsbettest.dto.*;
import com.example.sportsbettest.exception.InvalidTicketException;
import com.example.sportsbettest.factory.TicketStrategyFactory;
import com.example.sportsbettest.model.TicketType;
import com.example.sportsbettest.properties.TicketProperties;
import com.example.sportsbettest.service.TicketCalculatorService;
import com.example.sportsbettest.strategy.AdultTicketStrategy;
import com.example.sportsbettest.strategy.ChildrenTicketStrategy;
import com.example.sportsbettest.strategy.SeniorTicketStrategy;
import com.example.sportsbettest.strategy.TeenTicketStrategy;
import com.example.sportsbettest.validation.*;

/**
 * Testing methods from TicketCalculatorService
 */
public class TicketCalculatorServiceTest {

	private TicketCalculatorService ticketCalculatorService;
	private TicketStrategyFactory ticketStrategyFactory;
	private SeniorTicketStrategy seniorTicketStrategy;
	private AdultTicketStrategy adultTicketStrategy;
	private ChildrenTicketStrategy childrenTicketStrategy;
	private TeenTicketStrategy teenTicketStrategy;
	private TicketProperties ticketProperties;
	private TransactionRequestValidator transactionRequestValidator;

	/**
	 * Set all required dependencies before testing
	 */
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);

		ticketStrategyFactory = mock(TicketStrategyFactory.class);
		ticketProperties = mock(TicketProperties.class);
		transactionRequestValidator = new TransactionRequestValidator();

		// Mocking ticket age limits
		Map<String, Integer> ageLimit = new HashMap<>();
		ageLimit.put("senior", 65);
		ageLimit.put("adult", 18);
		ageLimit.put("teen", 11);
		when(ticketProperties.getAgeLimit()).thenReturn(ageLimit);

		// Mocking prices
		Map<String, Double> price = new HashMap<>();
		price.put("adult", 25.0);
		price.put("teen", 12.0);
		price.put("children", 5.0);
		when(ticketProperties.getPrice()).thenReturn(price);

		// Mocking Discount values of Children
		TicketProperties.ChildrenDiscount childrenDiscount = new TicketProperties.ChildrenDiscount();
		childrenDiscount.setPercent(25);
		childrenDiscount.setThreshold(3);
		when(ticketProperties.getChildrenDiscount()).thenReturn(childrenDiscount);

		// Mocking Discount values of Seniors
		TicketProperties.SeniorDiscount seniorDiscount = new TicketProperties.SeniorDiscount();
		seniorDiscount.setPercent(30);
		when(ticketProperties.getSeniorDiscount()).thenReturn(seniorDiscount);

		// Creating strategy instances
		seniorTicketStrategy = new SeniorTicketStrategy(ticketProperties);
		adultTicketStrategy = new AdultTicketStrategy(ticketProperties);
		teenTicketStrategy = new TeenTicketStrategy(ticketProperties);
		childrenTicketStrategy = new ChildrenTicketStrategy(ticketProperties);

		when(ticketStrategyFactory.getStrategy(TicketType.Senior)).thenReturn(seniorTicketStrategy);
		when(ticketStrategyFactory.getStrategy(TicketType.Adult)).thenReturn(adultTicketStrategy);
		when(ticketStrategyFactory.getStrategy(TicketType.Teen)).thenReturn(teenTicketStrategy);
		when(ticketStrategyFactory.getStrategy(TicketType.Children)).thenReturn(childrenTicketStrategy);

		ticketCalculatorService = new TicketCalculatorService(ticketProperties, ticketStrategyFactory,
				transactionRequestValidator);
	}
	
	/**
	 * Test case to validate that an exception is thrown when the transaction id is negative
	 */
	@Test
	public void returnException_WhenTransactionIdIsNegative() {

		List<Customer> customers = new ArrayList<>();
		TransactionRequest request = new TransactionRequest(-1, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Transaction Id must be between 0 and 2147483647", exception.getMessage());

	}
			

	/**
	 * Test case to validate that an exception is thrown when customers list is empty
	 */
	@Test
	public void returnException_WhenCustomerListIsEmpty() {

		List<Customer> customers = new ArrayList<>();
		TransactionRequest request = new TransactionRequest(4, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Customer list cannot be null or empty", exception.getMessage());

	}

	/**
	 * Test case to validate that an exception is thrown when customer list is null
	 */
	@Test
	public void returnException_WhenCustomerListIsNull() {

		TransactionRequest request = new TransactionRequest(4, null);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Customer list cannot be null or empty", exception.getMessage());

	}

	/**
	 * Test case to validate that an exception is thrown when customer name is null
	 */
	@Test
	public void returnException_WhenCustomerNameIsNull() {

		Customer childCustomer = new Customer(null, 6);

		List<Customer> customers = new ArrayList<>();
		customers.add(childCustomer);
		TransactionRequest request = new TransactionRequest(4, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Customer name cannot be null or empty", exception.getMessage());

	}

	/**
	 * Test case to validate that an exception is thrown when customer name is empty
	 */
	@Test
	public void returnException_WhenCustomerNameIsEmpty() {

		Customer childCustomer = new Customer("", 5);

		List<Customer> customers = new ArrayList<>();
		customers.add(childCustomer);
		TransactionRequest request = new TransactionRequest(4, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Customer name cannot be null or empty", exception.getMessage());

	}
	
	/**
	 * Test case to validate that an exception is thrown when customer age is negative
	 */
	@Test
	public void returnException_WhenCustomerAgeIsNegative() {

		Customer childCustomer = new Customer("Melani", -5);
		List<Customer> customers = new ArrayList<>();
		customers.add(childCustomer);
		TransactionRequest request = new TransactionRequest(4, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Invalid age: -5. Age must be between 0 and 150", exception.getMessage());

	}
	
	/**
	 * Test case to validate that an exception is thrown when customer age is above 150
	 */
	@Test
	public void returnException_WhenCustomerAgeIsAboveMaxLimit() {

		Customer childCustomer = new Customer("Stella", 151);
		List<Customer> customers = new ArrayList<>();
		customers.add(childCustomer);
		TransactionRequest request = new TransactionRequest(4, customers);
		Exception exception = assertThrows(InvalidTicketException.class,
				() -> ticketCalculatorService.returnResponse(request));
		assertEquals("Invalid age: 151. Age must be between 0 and 150", exception.getMessage());

	}
	

	/**
	 * Test case with 1 adult, 1 senior,1 teen and 1 children type customers 
	 * validates  the boundary age values and verifies the transaction id, ticket count and the total cost
	 */
	@Test
	public void returnsCorrectTransactionIdTotalCostAndTickets_ForBoundaryAges_success() {

		Customer seniorCustomer = new Customer("Mary", 65);
		Customer adultCustomer = new Customer("Ann", 18);
		Customer teenCustomer = new Customer("Catherine", 11);
		Customer childCustomer = new Customer("Helena", 10);

		List<Customer> customers = new ArrayList<>();
		customers.add(seniorCustomer);
		customers.add(adultCustomer);
		customers.add(teenCustomer);
		customers.add(childCustomer);

		TransactionRequest request = new TransactionRequest(1, customers);
		TransactionResponse response = ticketCalculatorService.returnResponse(request);
		assertEquals(1, response.getTransactionId());
		assertEquals(4, response.getTickets().size());
		assertEquals(BigDecimal.valueOf(59.50).setScale(2), response.getTotalCost());// (25*70/100)+25+12+5

	}

	/**
	 * Test case with 3 children type customers
	 * validate the transaction id, ticket count and the total cost
	 */
	@Test
	public void returnsCorrectTransactionIdTotalCostAndTickets_ForFourChildren_success() {

		Customer childCustomer_1 = new Customer("Smith", 5);
		Customer childCustomer_2 = new Customer("Bob", 8);
		Customer childCustomer_3 = new Customer("Billy", 6);

		List<Customer> customers = new ArrayList<>();
		customers.add(childCustomer_1);
		customers.add(childCustomer_2);
		customers.add(childCustomer_3);

		TransactionRequest request = new TransactionRequest(2, customers);
		TransactionResponse response = ticketCalculatorService.returnResponse(request);
		assertEquals(2, response.getTransactionId());
		assertEquals(1, response.getTickets().size());
		assertEquals(BigDecimal.valueOf(11.25).setScale(2), response.getTotalCost()); // 5*3*75/100

	}

	
	
	

}
