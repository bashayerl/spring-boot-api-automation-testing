# Test Plan

## Project Name

User Management QA Automation Project

## 1. Introduction

This test plan defines the testing strategy for the User Management application.  
The application provides user management features including create, read, update, delete, search, validation, and duplicate email prevention.

The goal of this test plan is to ensure that the application works correctly from both API and UI perspectives and that all core business rules are validated through automated tests.

---

## 2. Objectives

The main objectives of testing are:

- Verify that users can be created successfully.
- Verify that users can be retrieved by ID and listed.
- Verify that users can be updated successfully.
- Verify that users can be deleted successfully.
- Verify that users can be searched by name.
- Verify that validation rules are enforced.
- Verify that duplicate emails are not allowed.
- Verify that invalid requests return proper error responses.
- Verify that UI workflows work correctly through Selenium.
- Verify that data is correctly persisted in the database.
- Generate automated test reports with screenshots.

---

## 3. Scope

### In Scope

The following features are included in the testing scope:

- Add user
- Get all users
- Get user by ID
- Update user
- Delete user
- Search user by name
- Name validation
- Email validation
- Duplicate email validation
- API error handling
- UI validation messages
- Database verification
- ExtentReports generation

### Out of Scope

The following are not covered in the current test scope:

- Authentication and authorization
- Role-based access control
- Performance testing
- Load testing
- Security penetration testing
- Cross-browser testing beyond Chrome
- Mobile responsiveness testing
- Accessibility testing

---

## 4. Test Approach

The project uses a layered testing approach:

### 4.1 API Testing

API tests are implemented using:

- JUnit 5
- Spring Boot Test
- MockMvc

API tests validate REST endpoints directly without using a browser.

### 4.2 UI End-to-End Testing

UI E2E tests are implemented using:

- Selenium WebDriver
- ChromeDriver
- WebDriverManager
- JUnit 5

These tests simulate real user behavior in the browser.

### 4.3 Database Verification

Some tests verify database state after user actions, such as:

- User count after adding records
- User existence after creation
- User removal after deletion
- Database remains unchanged after invalid actions

### 4.4 Reporting

Test execution reports are generated using ExtentReports.

Reports include:

- Test name
- Test category
- Execution steps
- Screenshots
- System information
- Failure details

---

## 5. Test Types

| Test Type | Description |
|---|---|
| API Positive Testing | Validates successful API operations |
| API Negative Testing | Validates invalid inputs and error handling |
| UI E2E Testing | Validates complete user workflows through the browser |
| Validation Testing | Validates input rules for name and email |
| Database Testing | Verifies database state after operations |
| Regression Testing | Ensures existing features still work after changes |

---

## 6. Test Environment

| Item | Description |
|---|---|
| Operating System | Windows 11 |
| Java Version | Java 17 |
| Build Tool | Maven |
| Backend Framework | Spring Boot |
| Database | MySQL / H2 |
| Browser | Chrome |
| UI Automation Tool | Selenium WebDriver |
| API Testing Tool | MockMvc |
| Test Framework | JUnit 5 |
| Reporting Tool | ExtentReports |

---

## 7. Test Data

Sample test data used in automation:

| Name | Email | Purpose |
|---|---|---|
| Ahmad | Ahmad@Test | Valid API add user test |
| Alle | Alle@Test | Get user and update tests |
| Mohamad | mohamad@Test | Update user test |
| Bashayer | bashayer@test.com | Search test |
| First User | first@test.com | Duplicate update test |
| Second User | second@test.com | Duplicate update test |
| Invalid Email User | not-an-email | Invalid email UI test |
| Duplicate User | duplicate.ui@test.com | Duplicate email UI test |

---

## 8. Entry Criteria

Testing can start when:

- Application code is available.
- Required dependencies are installed.
- Database connection is configured.
- Application can start successfully.
- Test environment is ready.
- Test data is identified.

---

## 9. Exit Criteria

Testing is considered complete when:

- All planned automated tests are executed.
- All critical and high severity defects are fixed.
- API tests pass successfully.
- UI E2E tests pass successfully.
- Extent report is generated.
- Test cases are documented.
- No blocker issues remain.

---

## 10. Pass / Fail Criteria

### Pass Criteria

A test passes when:

- Actual result matches expected result.
- Correct HTTP status code is returned.
- UI displays the expected message.
- Database state is correct.
- No unexpected exception occurs.

### Fail Criteria

A test fails when:

- Actual result does not match expected result.
- Incorrect HTTP status code is returned.
- UI message is missing or incorrect.
- Database state is incorrect.
- Unexpected exception occurs.
- Selenium cannot locate required UI elements.

---

## 11. Test Execution

### Run API Tests

Run:
bash ./mvnw test
On Windows:
bash .\mvnw.cmd test
Or run the test class directly from the IDE:
text DemoApplicationTests
### Run UI E2E Tests

Run from the IDE:
text UserE2ETest
Important:

- Do not manually run the application on the same port while running E2E tests.
- E2E tests start the Spring Boot context automatically.

---

## 12. Test Reports

ExtentReports are generated after running Selenium E2E tests.

Report location:---

## 13. Risks and Mitigation

| Risk | Impact | Mitigation |
|---|---|---|
| Browser driver mismatch | E2E tests may fail | Use WebDriverManager |
| Database connection failure | Tests may fail | Verify DB configuration before execution |
| Port conflict on 8080 | Application or tests may fail | Stop running app before E2E tests |
| Slow UI rendering | Selenium assertions may fail | Use explicit waits |
| Duplicate test data | Tests may fail | Clean database before each test |

---

## 14. Defect Management

If a defect is found:

1. Reproduce the issue.
2. Capture screenshot or console error.
3. Document steps to reproduce.
4. Identify expected and actual results.
5. Assign severity and priority.
6. Fix the issue.
7. Re-run related tests.
8. Confirm regression tests pass.

---

## 15. Deliverables

The QA deliverables include:

- Automated API tests
- Automated UI E2E tests
- Test case documentation
- Test plan documentation
- ExtentReports HTML report
- Screenshots for test results
- README documentation

---

## 16. Future Testing Enhancements

Potential future improvements:

- Add cross-browser testing
- Add GitHub Actions CI pipeline
- Add performance testing
- Add security testing
- Add accessibility testing
- Add test data factory
- Add visual regression testing
- Add API contract testing

---

## 17. Approval

| Role | Name | Status |
|---|---|---|
| QA Engineer | Bashayer | Approved |
