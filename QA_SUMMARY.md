# QA Summary

## Project Name

User Management QA Automation Project

---

## QA Objective

The objective of the QA work in this project is to validate the User Management application through automated API tests, UI end-to-end tests, database verification, negative testing, and test reporting.

This project demonstrates a complete QA automation workflow including:

- Test planning
- Test case design
- API testing
- UI automation
- Negative testing
- Database verification
- Defect documentation
- Test reporting

---

## Application Under Test

The application is a Spring Boot User Management system that supports:

- Add user
- View users
- Get user by ID
- Update user
- Delete user
- Search user by name
- Validate user input
- Prevent duplicate emails

---

## QA Scope

| Area | Covered |
|---|---|
| API Testing | Yes |
| UI E2E Testing | Yes |
| Positive Testing | Yes |
| Negative Testing | Yes |
| Validation Testing | Yes |
| Database Verification | Yes |
| Error Message Verification | Yes |
| Test Reporting | Yes |
| Screenshots | Yes |
| Test Documentation | Yes |

---

## Automated Test Coverage

| Test Layer | Description | Tool |
|---|---|---|
| API Tests | Validate REST endpoints and business rules | MockMvc |
| UI E2E Tests | Validate complete user flows in browser | Selenium WebDriver |
| Database Verification | Verify database state after actions | Spring Data JPA |
| Reporting | Generate HTML reports with screenshots | ExtentReports |

---

## API Test Coverage

The API tests cover:

- Add valid user
- Get user by ID
- Update user
- Delete user
- Search user by name
- Add user with missing name
- Add user with empty name
- Add user with short name
- Add user with invalid email
- Add user with duplicate email
- Update user with duplicate email
- Get non-existing user
- Update non-existing user
- Delete non-existing user
- Search non-existing name

API Test Class:

```text
DemoApplicationTests
```
