# Sportsbet Test Application
 
 - It processes ticket transactions and returns customer details and the transaction ID based on the input request.   
   
## Technologies used

- Java 17
- Spring Boot version 3.5
- Gradle version 8.14
- JUnit 5 for unit testing
- SLF4J for logging


## Project Structure
### src/main/java
- com.example.sportsbettest
  - SportsbetTestApplication.java
- com.example.sportsbettest.dto
   - Customer.java
   - Ticket.java
   - TransactionRequest.java
   - TransactionResponse.java
- com.example.sportsbettest.model
   - TicketType.java
- com.example.sportsbettest.strategy
   - TicketPriceStrategy.java
   - AdultTicketStrategy.java
   - ChildrenTicketStrategy.java
   - SeniorTicketStrategy.java
   - TeenTicketStrategy.java
- com.example.sportsbettest.factory
   - TicketPriceStrategyFactory.java
- com.example.sportsbettest.service
   - TicketCalculatorService.java    
- com.example.sportsbettest.controller
   - TicketController.java
- com.example.sportsbettest.exception
   - GlobalExceptionHandler.java  
   - InvalidTicketException.java 
   - ErrorResponse
- com.example.sportsbettest.validatio
   - TransactionRequestValidator.java
   - Validator.java  
- com.example.sportsbettest.properties    
   - TicketProperties.java
- com.example.sportsbettest.config
   - Appconfig.java 

     
### src/main/resources
- application.yml

### src/test/java
- com.example.sportsbetTest.service
   - TicketCalculatorServiceTest.java
   
## Running the application
- Run via Gradle:  './gradlew bootrun'
- or run from the IDE: Right click the main class ->  Run as ->  Spring Boot App

## Running the tests
- Run via Gradle: ./gradlew test
- or run from the IDE: Right click the test class-> Run as -> JUnit Test  

## Checking the endpoints
- Swagger UI:  /swagger-ui.html (http://localhost:8080/swagger-ui.html)
- or else can be checked using third party tools like Postman

> **Note:** Decimal values are formatted to show up to 2 decimal places correctly in Postman responses.  
> However, Swagger UI may sometimes display full precision without rounding or formatting decimals.
     
## Assumptions made
- Returns the same transaction ID received in the request
- Using BigDecimal though it takes more space in the memory than other data types like Double
  to make sure the accuracy since the transactions are made using currency and with 2 decimal point 
  as the response is expected to display values with 2 decimal points
- Using the external properties file to make sure the age,prices and percentages can be changed in
  future  
- Added Validations for the customer age,customer name,customer list and the transaction Id based on the following assumptions           
   -Transaction Id must be positive
   -customer list cannot be empty
   -customer name must not be null or empty 
   -age should be between 0–150 
- Controller tests have not been implemented as the controller only  delegates requests to the service layer without containing any business logic
- Added the prices, age limit and the percentage in the application.yml file as I thought it can be altered in the future 


## Can be implemented in the future
- Implement Security using JWT tokens or other authentication mechanisms

   
    


