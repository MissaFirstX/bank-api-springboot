# Bank API

A REST banking API built with **Spring Boot**, following a clean/hexagonal architecture organized by domain (`customer`, `account`, `security`) and split into `api`, `application`, `domain`, and `infrastructure` layers.

It supports customer management, bank accounts, deposits, withdrawals, transaction history, and JWT-based authentication.

## Table of contents

- [Tech stack](#tech-stack)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Running the app](#running-the-app)
- [Authentication](#authentication)
- [Endpoints](#endpoints)
    - [Auth](#auth)
    - [Customers](#customers)
    - [Accounts](#accounts)
- [Error handling](#error-handling)
- [Tests](#tests)

## Tech stack

- Java 21
- Spring Boot 4.1 (Web MVC, Data JPA, Validation, Security)
- PostgreSQL
- JJWT (JSON Web Tokens) 0.12.6
- Maven

## Architecture

The project follows a **layered / hexagonal architecture**, replicated across each domain module:

```
src/main/java/com/missa/bank/
├── customer/
│   ├── api/            # Controllers, requests and responses
│   ├── application/    # Use cases
│   ├── domain/         # Models, value objects, exceptions and repository contracts
│   └── infrastructure/ # JPA persistence implementation
├── account/
│   ├── api/
│   ├── application/
│   ├── domain/
│   └── infrastructure/
├── security/
│   ├── api/             # Login and user registration
│   ├── application/
│   ├── domain/
│   └── infrastructure/  # JWT, filters and Spring Security configuration
└── common/
    └── infrastructure/  # Global exception handling and error DTOs
```

This keeps business logic (`domain`) framework-agnostic, so persistence or HTTP transport can be swapped without affecting business rules.

## Prerequisites

- JDK 21+
- Maven (or use the included wrapper `./mvnw`)
- A reachable PostgreSQL database

## Configuration

> ⚠️ **Never commit real credentials to the repository.** This project reads sensitive configuration from environment variables; `application.properties` should only reference them.

Required environment variables:

| Variable | Description | Example |
|---|---|---|
| `DB_URL` | PostgreSQL JDBC URL | `jdbc:postgresql://host:5432/bank` |
| `DB_USERNAME` | Database username | `bank_owner` |
| `DB_PASSWORD` | Database password | `********` |
| `JWT_SECRET` | Secret used to sign JWT tokens (256 bits minimum) | `********` |
| `JWT_EXPIRATION` | Token expiration time in ms | `3600000` |

`src/main/resources/application.properties`:

```properties
spring.application.name=bank
server.port=3000

spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

security.jwt.secret=${JWT_SECRET}
security.jwt.expiration=${JWT_EXPIRATION}
```

You can export these variables in your shell or use a `.env` / `application-local.properties` file **added to `.gitignore`** for local development.

## Running the app

```bash
# Clone the repository
git clone <repository-url>
cd bank

# Export environment variables (example on Linux/Mac)
export DB_URL=jdbc:postgresql://localhost:5432/bank
export DB_USERNAME=bank_user
export DB_PASSWORD=changeme
export JWT_SECRET=a-long-random-secret-key
export JWT_EXPIRATION=3600000

# Run with the Maven Wrapper
./mvnw spring-boot:run
```

The API will be available at `http://localhost:3000`.

## Authentication

The API uses **JWT** with stateless sessions. The flow is:

1. Register a user via `POST /api/auth/register`.
2. Authenticate via `POST /api/auth/login` to get an `accessToken`.
3. Send the token on every protected request via the header:

```
Authorization: Bearer <accessToken>
```

Current access rules:

- `/api/auth/**` → public.
- `/api/customers/**` → requires `ADMIN` role.
- Any other endpoint → requires authentication.

## Endpoints

All routes are prefixed with `/api`. Request and response bodies are JSON.

### Auth

#### Register a user
`POST /api/auth/register`

```json
{
  "username": "jdoe",
  "password": "P@ssw0rd123",
  "role": "ADMIN",
  "customerId": 1
}
```

**Response `200 OK`**
```json
{
  "id": 1,
  "username": "jdoe",
  "role": "ADMIN"
}
```

#### Log in
`POST /api/auth/login`

```json
{
  "username": "jdoe",
  "password": "P@ssw0rd123"
}
```

**Response `200 OK`**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiIs...",
  "tokenType": "Bearer",
  "userId": 1,
  "username": "jdoe",
  "role": "ADMIN"
}
```

### Customers

> Requires `ADMIN` role.

#### Create a customer
`POST /api/customers`

```json
{
  "firstName": "Juan",
  "middleName": "Carlos",
  "lastName": "Perez",
  "birthDate": "1990-05-20",
  "email": "juan.perez@example.com",
  "phoneNumber": "5512345678",
  "curp": "PECJ900520HDFRRN01",
  "rfc": "PECJ900520AB1",
  "customerType": "INDIVIDUAL"
}
```

`customerType` accepts: `INDIVIDUAL`, `BUSINESS`.

**Response:** `201 Created` with the created customer's data.

#### Get customer by ID
`GET /api/customers/{id}`

**Response:** `200 OK` with the customer's data.

#### List customers (paginated)
`GET /api/customers?page=0&size=20&sort=lastName`

**Response:** `200 OK` with a `Page` of customers.

#### Update email
`PATCH /api/customers/{id}/email`

```json
{ "newEmail": "new.email@example.com" }
```

#### Update phone number
`PATCH /api/customers/{id}/phone`

```json
{ "newPhoneNumber": "5587654321" }
```

#### Change customer status
`PATCH /api/customers/{id}/status/{status}`

`status` accepts: `ACTIVE`, `INACTIVE`, `BLOCKED`, `SUSPENDED`.

### Accounts

#### Create an account
`POST /api/accounts`

```json
{
  "accountType": "SAVING",
  "customerId": 1
}
```

`accountType` accepts: `SAVING`, `CHECKING`, `BUSINESS`.

**Response:** `201 Created` with the created account's data (includes the generated account number and CLABE).

#### Get account by ID
`GET /api/accounts/{id}`

#### List accounts (paginated)
`GET /api/accounts?page=0&size=2&sort=customerId`

#### Get account transactions (paginated)
`GET /api/accounts/{id}/transactions?page=0&size=2`

#### Deposit
`POST /api/accounts/{id}/deposit`

```json
{ "amount": 1500.00 }
```

#### Withdraw
`POST /api/accounts/{id}/withdraw`

```json
{ "amount": 500.00 }
```

**Possible business errors:** account closed, account not active, insufficient balance, negative amount.

## Error handling

Errors are returned with a consistent format managed by a `GlobalExceptionHandler`:

```json
{
  "status": 400,
  "message": "Error description",
  "timestamp": "2026-07-30T12:00:00"
}
```

Validation errors (`400 Bad Request`) include a per-field breakdown:

```json
{
  "status": 400,
  "errors": [
    { "field": "email", "message": "must be a well-formed email address" }
  ]
}
```

Status codes used: `400` (validation / business rule), `401` (unauthenticated), `403` (forbidden), `404` (not found), `409` (conflict, e.g. duplicate customer).

## Tests

```bash
./mvnw test
```

Includes unit tests for key use cases such as customer creation and finding a customer by ID.

## Roadmap / suggested next steps

- [ ] Interactive documentation with OpenAPI / Swagger.
- [ ] Additional role-based rules over `/api/accounts/**`.
- [ ] Fully externalize secrets (Vault, AWS Secrets Manager, etc.) before deploying to production.