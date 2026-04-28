# User Management QA Automation Project

A Spring Boot User Management application focused on QA Automation.  
The project includes REST API testing, UI End-to-End testing, validation testing, negative testing, database verification, ExtentReports, and QA documentation.

---

## Project Overview

This project is a full-stack User Management system built with Spring Boot.

It supports:

- Add users
- View users
- Get user by ID
- Update users
- Delete users
- Search users by name
- Validate user input
- Prevent duplicate emails

The main focus of this project is to demonstrate a complete QA Automation workflow, including:

- API automation testing
- UI automation testing
- Negative testing
- Database verification
- Test reporting
- Test documentation
- Bug reporting documentation
- CI test execution using GitHub Actions

---

## Technologies Used

| Category | Technologies |
|---|---|
| Language | Java 17 |
| Backend | Spring Boot, Spring MVC |
| Persistence | Spring Data JPA |
| Database | MySQL, H2 for tests |
| Validation | Jakarta Validation |
| Frontend | HTML, CSS, JavaScript, Bootstrap |
| API Testing | JUnit 5, MockMvc |
| UI Testing | Selenium WebDriver |
| Test Reports | ExtentReports |
| Browser Driver | WebDriverManager |
| Build Tool | Maven |
| API Documentation | Swagger / OpenAPI |
| CI/CD | GitHub Actions |

---

## Features

- CRUD operations for users
- Search users by name
- Input validation
- Duplicate email validation
- DTO-based request and response handling
- Service layer architecture
- Global exception handling
- Bootstrap-based UI
- API tests
- Selenium E2E tests
- Negative test scenarios
- Database verification
- ExtentReports with screenshots
- QA documentation files

---

## Project Structure

demo/
├── .github/
│   └── workflows/
│       └── main.yml                    # GitHub Actions CI workflow
│
├── .mvn/
│   └── wrapper/                        # Maven Wrapper files
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/bashayer/demo/
│   │   │       ├── config/
│   │   │       │   └── OpenApiConfig.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   └── ControllerT1.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── UserRequest.java
│   │   │       │   └── UserResponse.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── ApiErrorResponse.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   └── User.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   └── UserRepository.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   └── UserService.java
│   │   │       │
│   │   │       └── DemoApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   └── index.html         
│   │       │
│   │       ├── templates/
│   │       │
│   │       └── application.properties  
│   │
│   └── test/
│       ├── java/
│       │   ├── com/bashayer/demo/
│       │   │   ├── DemoApplicationTests.java  
│       │   │   └── UserE2ETest.java           
│       │   │
│       │   └── pages/
│       │       └── UsersPage.java            
│       │
│       └── resources/
│           └── application-test.properties    
│
├── BUG_REPORT_TEMPLATE.md              
├── QA_SUMMARY.md                       
├── README.md                           
├── TEST_CASES.md                      
├── TEST_PLAN.md                        
├── pom.xml                           
├── mvnw                               
├── mvnw.cmd                           
└── .gitattributes


---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/add` | Add a new user |
| PUT | `/api/users/{id}` | Update user |
| DELETE | `/api/users/{id}` | Delete user |
| GET | `/api/users/search?name=value` | Search users by name |

---

## Application URLs

After running the application:

| Page | URL |
|---|---|
| Web UI | `http://localhost:8080/index.html` |
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| API Docs | `http://localhost:8080/v3/api-docs` |

---

## Database Configuration

The application uses MySQL for local runtime.

Example `application.properties`:


---

## Testing Overview

The project includes two main automated test layers:

| Test Layer | Test Class | Tool |
|---|---|---|
| API Tests | `DemoApplicationTests` | MockMvc |
| UI E2E Tests | `UserE2ETest` | Selenium WebDriver |

---

## API Test Coverage

The API tests cover:

- Add valid user
- Get user by ID
- Update user
- Delete user
- Search users by name
- Add user with missing name
- Add user with empty name
- Add user with short name
- Add user with invalid email
- Add user with duplicate email
- Update user with duplicate email
- Get non-existing user
- Update non-existing user
- Delete non-existing user
- Search with no matching results

---

## UI E2E Test Coverage

The Selenium E2E tests cover:

- Add users from UI
- Verify users are saved in database
- Search users from UI
- Verify filtered search results
- Add duplicate email from UI
- Verify duplicate email error message
- Add invalid email from UI
- Verify validation error message
- Capture screenshots
- Generate Extent Report

---

## QA Highlights

- QA-focused project structure
- Positive and negative API tests
- UI End-to-End test automation
- Page Object Model usage
- Database verification
- ExtentReports integration
- Screenshots on test execution
- Test Plan and Test Cases documentation
- Bug Report Template
- CI-ready workflow with MySQL Service

---

## Future Enhancements

- Run Selenium tests in GitHub Actions
- Upload Extent Report as GitHub Actions artifact
- Add cross-browser testing
- Add performance testing
- Add security testing
- Add accessibility testing
- Add code coverage reports
- Add Allure Reports
- Add Docker support

---

## Author

Bashayer

