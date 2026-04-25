# Spring Boot User Management Demo

A simple Spring Boot application for user management with a RESTful API and a basic web frontend.

## Overview
This project demonstrates a full-stack Java application using:
- **Backend:** Spring Boot (Web, Data JPA, Validation)
- **Frontend:** HTML/JavaScript (static)
- **Database:** MySQL
- **Testing:** JUnit 5, Selenium (E2E), ExtentReports
- **API Documentation:** SpringDoc OpenAPI (Swagger UI)

## Requirements
- **Java:** JDK 17 or higher (configured for Java 25 in Maven)
- **Maven:** 3.6+
- **Database:** MySQL server running locally

## Setup & Run

### 1. Database Configuration
By default, the application connects to a MySQL database at `localhost:3306/demo_db`.
Ensure you have MySQL running and update `src/main/resources/application.properties` with your credentials if different:
```properties
spring.datasource.username=root
spring.datasource.password=1234
```

### 2. Run the Application
You can run the application using the Maven wrapper:
```bash
./mvnw spring-boot:run
```
The application will be available at `http://localhost:8080`.

### 3. Accessing the Application
- **Web Frontend:** [http://localhost:8080/index.html](http://localhost:8080/index.html)
- **API Documentation (Swagger UI):** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **H2 Console (if enabled):** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Available Scripts

- `mvn clean compile`: Compiles the source code.
- `mvn test`: Runs unit and integration tests.
- `mvn package`: Packages the application into a JAR file in the `target/` directory.
- `mvn spring-boot:run`: Starts the Spring Boot application.

## Testing
The project includes:
- **Unit/Integration Tests:** Located in `src/test/java/com/bashayer/demo/`.
- **E2E Tests:** Selenium-based tests in `UserE2ETest.java` (runs in headless mode by default).

### Test Reports
After running tests, reports are generated at:
- `target/SparkReport.html`: ExtentReports HTML report.
- `target/screenshots/`: Screenshots captured during E2E test failures.

## Project Structure
```text
demo/
├── src/
│   ├── main/
│   │   ├── java/com/bashayer/demo/
│   │   │   ├── controller/   # REST Controllers (API endpoints)
│   │   │   ├── model/        # JPA Entities
│   │   │   └── repository/   # Spring Data JPA Repositories
│   │   └── resources/
│   │       ├── static/       # Frontend assets (index.html)
│   │       └── application.properties # Configuration
│   └── test/
│       └── java/
│           ├── com/bashayer/demo/ # Backend tests
│           └── pages/             # Page Object Model for E2E tests
├── pom.xml                        # Maven dependencies and build config
└── README.md                      # Project documentation
```

## Environment Variables
The following properties in `application.properties` can be overridden via environment variables:
- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `SERVER_PORT`

## License
TODO: Add license information.
