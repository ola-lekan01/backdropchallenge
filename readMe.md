# Backdrop Engineering Challenge

# Prerequisites

    Java JDK 8 or higher
    Apache Maven 3.6 or higher
    springboot 3.0.0 or higher

# Installation and Usage

    Clone this repository to your local machine.
    Open the project in your preferred IDE.
    Configure the config.env file and Build the project using Maven: mvn clean install.
    Run the project: mvn spring-boot:run.
    Open a web browser and go to http://localhost:8080/graphql.
    Use the GraphQL playground to mutate and query the Users account information.


# API Endpoints
This project includes the following GraphQL endpoints:
```
Mutate: This endpoint takes as argument account name, account number and bank code and validates the users Account
name as to the response from paystack API. if the name matches using the pure Levenshtein Distance algorithm
the account detailsis saved and stored into the database with the verification status true.
Otherwise it persitst to the database the returnedname from paystack API call with verification status false.
```

```
Query: This endpoint takes as argument account number and bank code and returns the account name associated with the
parameter details from the database if available. Otherwise it returns the name from the paystack API call.
```


# Configuration

This project uses a postgreSQL database for data storage. Kindly configure the database settings in the config.env file.
A sample of the config.env variables is given below.
```
DATABASE_URL=jdbc:postgresql://localhost:5432/backdrop
USERNAME=postgres
PASSWORD=12345
DATA_SOURCE=org.postgresql.Driver
DDL_AUT0=update
HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
DATABASE_JPA=postgresql

PAY_STACK_SECRET_KEY={Pass in value here}
```

# Testing

```
This project includes units test using Junit and Mockito for the service layers. Run the tests using Maven: mvn test.
```
# Contributions

```
Contributions to this project are welcome. Please fork the repository and submit a pull request with your changes.
```
# Difference between pure Levenshtein Distance algorithm and Damerau-Levenshtein Distance

```
The pure Levenshtein Distance algorithm is more effective solution than the Damerau-Levenshtein Distance algorithm
in this scenario, as only insertion, deletion, and substitution of characters operations is used, and transposition
 of adjacent characters is not needed. The Damerau-Levenshtein Distance algorithm adds an extra operation of
 transposing adjacent characters, which increases the number of possible edit distances that need to be calculated
 and can lead to longer processing times. In this scenario, the pure Levenshtein Distance algorithm is a simpler
 and more efficient solution to determine a deficient character in the string passed to it.
```