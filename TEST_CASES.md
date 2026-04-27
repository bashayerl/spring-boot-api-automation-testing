# Test Cases Documentation

## Project Name

User Management QA Automation Project

## Objective

This document describes the manual and automated test cases designed for the User Management application.  
The goal is to validate API functionality, UI behavior, database persistence, validation rules, and negative scenarios.

## Test Scope

The test coverage includes:

- User creation
- User retrieval
- User update
- User deletion
- User search
- Input validation
- Duplicate email validation
- Not found scenarios
- UI end-to-end flows
- Database verification
- Error message verification

## Test Types

| Test Type | Description |
|---|---|
| API Positive Tests | Validate successful API operations |
| API Negative Tests | Validate API behavior with invalid input or invalid IDs |
| UI E2E Positive Tests | Validate full user flows from the web interface |
| UI E2E Negative Tests | Validate error handling from the web interface |
| Database Verification | Confirm data is saved, updated, or deleted in the database |
| Regression Tests | Ensure existing features still work after changes |

---

# API Test Cases

## TC_API_001 - Add Valid User

| Field | Details |
|---|---|
| Test Case ID | TC_API_001 |
| Test Scenario | Add a valid user using API |
| Test Type | API Positive |
| Preconditions | Application is running and database is available |
| Test Data | Name: Ahmad, Email: Ahmad@Test |
| Steps | Send POST request to `/api/add` |
| Expected Result | API returns 200 OK and user data |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserAutomation |

---

## TC_API_002 - Add User With Missing Name

| Field | Details |
|---|---|
| Test Case ID | TC_API_002 |
| Test Scenario | Add user without name |
| Test Type | API Negative |
| Preconditions | Application is running |
| Test Data | Email: Reem@Test |
| Steps | Send POST request to `/api/add` without name |
| Expected Result | API returns 400 Bad Request |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUsersWithMissingName |

---

## TC_API_003 - Get User By ID

| Field | Details |
|---|---|
| Test Case ID | TC_API_003 |
| Test Scenario | Add user then retrieve by ID |
| Test Type | API Positive |
| Preconditions | User exists in database |
| Test Data | Name: Alle, Email: Alle@Test |
| Steps | Add user, then send GET request to `/api/users/{id}` |
| Expected Result | API returns 200 OK and correct user data |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserThenGetUserById |

---

## TC_API_004 - Update Existing User

| Field | Details |
|---|---|
| Test Case ID | TC_API_004 |
| Test Scenario | Update existing user |
| Test Type | API Positive |
| Preconditions | User exists in database |
| Test Data | Updated Name: Mohamad, Updated Email: mohamad@Test |
| Steps | Add user, then send PUT request to `/api/users/{id}` |
| Expected Result | API returns 200 OK and updated user data |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testUpdateUserAutomation |

---

## TC_API_005 - Delete Existing User

| Field | Details |
|---|---|
| Test Case ID | TC_API_005 |
| Test Scenario | Delete existing user |
| Test Type | API Positive |
| Preconditions | User exists in database |
| Test Data | Name: testD, Email: TestD@Test |
| Steps | Add user, then send DELETE request to `/api/users/{id}` |
| Expected Result | API returns 204 No Content and user is removed |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testDeleteUserAutomation |

---

## TC_API_006 - Add Duplicate Email

| Field | Details |
|---|---|
| Test Case ID | TC_API_006 |
| Test Scenario | Add user with duplicate email |
| Test Type | API Negative |
| Preconditions | A user with the same email already exists |
| Test Data | Email: duplicate@test.com |
| Steps | Add first user, then add second user with same email |
| Expected Result | API returns 400 Bad Request and database contains one user |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserWithDuplicateEmail |

---

## TC_API_007 - Add User With Invalid Email

| Field | Details |
|---|---|
| Test Case ID | TC_API_007 |
| Test Scenario | Add user with invalid email format |
| Test Type | API Negative |
| Preconditions | Application is running |
| Test Data | Name: Rayan, Email: not-an-email |
| Steps | Send POST request to `/api/add` |
| Expected Result | API returns 400 Bad Request |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserWithInvalidEmail |

---

## TC_API_008 - Add User With Empty Name

| Field | Details |
|---|---|
| Test Case ID | TC_API_008 |
| Test Scenario | Add user with empty name |
| Test Type | API Negative |
| Preconditions | Application is running |
| Test Data | Name: empty, Email: empty@Test |
| Steps | Send POST request to `/api/add` with empty name |
| Expected Result | API returns 400 Bad Request |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserWithEmptyName |

---

## TC_API_009 - Search Users By Name

| Field | Details |
|---|---|
| Test Case ID | TC_API_009 |
| Test Scenario | Search users by partial name |
| Test Type | API Positive |
| Preconditions | Users exist in database |
| Test Data | Search keyword: Bash |
| Steps | Add users, then send GET request to `/api/users/search?name=Bash` |
| Expected Result | API returns matching users |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testSearchUsersByName |

---

## TC_API_010 - Add User With Short Name

| Field | Details |
|---|---|
| Test Case ID | TC_API_010 |
| Test Scenario | Add user with name less than 2 characters |
| Test Type | API Negative |
| Preconditions | Application is running |
| Test Data | Name: A, Email: shortname@test.com |
| Steps | Send POST request to `/api/add` |
| Expected Result | API returns 400 Bad Request |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testAddUserWithShortName |

---

## TC_API_011 - Get Non Existing User

| Field | Details |
|---|---|
| Test Case ID | TC_API_011 |
| Test Scenario | Get user with non-existing ID |
| Test Type | API Negative |
| Preconditions | User ID does not exist |
| Test Data | ID: 9999 |
| Steps | Send GET request to `/api/users/9999` |
| Expected Result | API returns 404 Not Found |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testGetNonExistingUserReturnsNotFound |

---

## TC_API_012 - Update Non Existing User

| Field | Details |
|---|---|
| Test Case ID | TC_API_012 |
| Test Scenario | Update user with non-existing ID |
| Test Type | API Negative |
| Preconditions | User ID does not exist |
| Test Data | ID: 9999 |
| Steps | Send PUT request to `/api/users/9999` |
| Expected Result | API returns 404 Not Found |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testUpdateNonExistingUserReturnsNotFound |

---

## TC_API_013 - Delete Non Existing User

| Field | Details |
|---|---|
| Test Case ID | TC_API_013 |
| Test Scenario | Delete user with non-existing ID |
| Test Type | API Negative |
| Preconditions | User ID does not exist |
| Test Data | ID: 9999 |
| Steps | Send DELETE request to `/api/users/9999` |
| Expected Result | API returns 404 Not Found |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testDeleteNonExistingUserReturnsNotFound |

---

## TC_API_014 - Search Non Existing Name

| Field | Details |
|---|---|
| Test Case ID | TC_API_014 |
| Test Scenario | Search for a name that does not exist |
| Test Type | API Negative |
| Preconditions | User does not match search keyword |
| Test Data | Search keyword: NotFoundName |
| Steps | Send GET request to `/api/users/search?name=NotFoundName` |
| Expected Result | API returns empty list |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testSearchUsersByNonExistingNameReturnsEmptyList |

---

## TC_API_015 - Update User With Duplicate Email

| Field | Details |
|---|---|
| Test Case ID | TC_API_015 |
| Test Scenario | Update user email to an existing email |
| Test Type | API Negative |
| Preconditions | Two users exist in database |
| Test Data | First user updated with second user's email |
| Steps | Add two users, update first user using second user's email |
| Expected Result | API returns 400 Bad Request |
| Automation Status | Automated |
| Test Class | DemoApplicationTests |
| Test Method | testUpdateUserWithDuplicateEmail |

---

# UI E2E Test Cases

## TC_UI_001 - User Lifecycle From UI

| Field | Details |
|---|---|
| Test Case ID | TC_UI_001 |
| Test Scenario | Add users from web UI and verify database |
| Test Type | UI E2E Positive |
| Preconditions | Application is running on port 8080 |
| Test Data | Bashayer Test, Ahmad Test |
| Steps | Open page, add two users, verify database count and records |
| Expected Result | Users are added and stored in database |
| Automation Status | Automated |
| Test Class | UserE2ETest |
| Test Method | testUserLifecycle |

---

## TC_UI_002 - Search User From UI

| Field | Details |
|---|---|
| Test Case ID | TC_UI_002 |
| Test Scenario | Search user by name from web UI |
| Test Type | UI E2E Positive |
| Preconditions | Users exist in database |
| Test Data | Search keyword: Bash |
| Steps | Add two users, search for Bash, verify only matching user is displayed |
| Expected Result | Matching user is visible and non-matching user is hidden |
| Automation Status | Automated |
| Test Class | UserE2ETest |
| Test Method | testSearchUserFromUI |

---

## TC_UI_003 - Duplicate Email From UI

| Field | Details |
|---|---|
| Test Case ID | TC_UI_003 |
| Test Scenario | Add duplicate email from web UI |
| Test Type | UI E2E Negative |
| Preconditions | A user with the same email already exists |
| Test Data | Email: duplicate.ui@test.com |
| Steps | Add first user, then add second user with same email |
| Expected Result | Error message is displayed and database still contains one user |
| Automation Status | Automated |
| Test Class | UserE2ETest |
| Test Method | testDuplicateEmailFromUI |

---

## TC_UI_004 - Invalid Email From UI

| Field | Details |
|---|---|
| Test Case ID | TC_UI_004 |
| Test Scenario | Add invalid email from web UI |
| Test Type | UI E2E Negative |
| Preconditions | Application is running |
| Test Data | Email: not-an-email |
| Steps | Enter invalid email and submit form |
| Expected Result | Validation error message is displayed and database remains empty |
| Automation Status | Automated |
| Test Class | UserE2ETest |
| Test Method | testInvalidEmailFromUI |

---

# Test Execution Summary

| Test Layer | Number of Automated Test Cases |
|---|---:|
| API Tests | 15 |
| UI E2E Tests | 4 |
| Total Automated Tests | 19 |

---

# QA Coverage Summary

| Area | Covered |
|---|---|
| CRUD Operations | Yes |
| Validation | Yes |
| Negative Testing | Yes |
| Duplicate Data Handling | Yes |
| Search Functionality | Yes |
| Database Verification | Yes |
| UI End-to-End Flow | Yes |
| Error Message Verification | Yes |
| Test Reporting | Yes |
| Screenshots | Yes |

---

# Tools Used

| Tool | Purpose |
|---|---|
| JUnit 5 | Test framework |
| MockMvc | API testing |
| Selenium WebDriver | UI automation |
| WebDriverManager | Browser driver management |
| ExtentReports | Test reporting |
| MySQL / H2 | Database |
| Spring Boot Test | Integration testing |

---

# Notes

- API tests should be executed before UI E2E tests when debugging failures.
- UI E2E tests should not run while the application is manually started on the same port.
- Extent report is generated under `target/SparkReport.html`.
- Screenshots are generated under `target/screenshots/`.