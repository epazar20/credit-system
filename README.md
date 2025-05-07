# Banking Credit System

A Spring Boot multi-module application for managing banking credit operations for both individual and corporate customers.

## Project Structure

The project is divided into the following modules:

- **core**: Contains core functionality and configuration
- **entities**: Contains all JPA entities 
- **repositories**: Contains repository interfaces for data access
- **business**: Contains business logic and services
- **controller**: Contains REST API endpoints

## Technology Stack

- Java 17
- Spring Boot 3.2.3
- Spring Security with JWT Authentication
- JPA/Hibernate
- Lombok
- MapStruct

## Features

- User authentication and authorization
- Individual and corporate customer management
- Various loan types for both individual and corporate customers
- Loan application processing and approval workflow

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven

### Building the Application

```bash
mvn clean install
```

### Running the Application

```bash
mvn spring-boot:run
```

## API Endpoints

### Authentication

- POST /api/v1/auth/login - Login with email and password
- POST /api/v1/auth/register - Register a new user

### Customers

- GET /api/v1/customers - Get all customers
- GET /api/v1/individual-customers - Get all individual customers
- GET /api/v1/corporate-customers - Get all corporate customers

### Loan Applications

- POST /api/v1/loan-applications/individual - Create an individual loan application
- POST /api/v1/loan-applications/corporate - Create a corporate loan application 