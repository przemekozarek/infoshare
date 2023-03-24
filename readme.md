# Spring Boot REST Service Project

This is a Spring Boot REST service project built with Maven and Java 11.

## Prerequisites

- Java 11
- Maven 3.6.x or higher

## Getting Started

1. Clone the repository:

    git clone https://github.com/przemekozarek/infoshare.git

    cd your-repo


2. Build the project:


    mvn clean install


3. Run the application:


    mvn spring-boot:run


Your REST service should now be running on \`http://localhost:8080\`.

## API Endpoints

Here is a list of available API endpoints:

### Create Transaction

- **Method**: \`POST\`
- **Path**: \`/transactions\`
- **Content-Type**: \`application/json\`

**Example Request**:


POST http://localhost:8080/transactions
Content-Type: application/json

{
"name": "Grocery store",
"value": "129.9",
"userId": "157572"
}


### Update Transaction

- **Method**: \`PUT\`
- **Path**: \`/transactions/{transactionId}\`
- **Content-Type**: \`application/json\`

**Example Request**:


PUT http://localhost:8080/transactions/1
Content-Type: application/json

{
"name": "Grocery stoora- long Island",
"value": "143.6",
"userId": "157572"
}


### Get Transactions by User ID

- **Method**: \`GET\`
- **Path**: \`/transactions/points/{userId}\`

**Example Request**:


GET http://localhost:8080/transactions/points/157572

