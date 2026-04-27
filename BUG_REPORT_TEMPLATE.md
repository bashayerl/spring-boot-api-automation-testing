# Bug Report Template

## 1. Bug Summary

| Field | Details |
|---|---|
| Bug ID | BUG-001 |
| Bug Title |  |
| Reported By | Bashayer |
| Reported Date | YYYY-MM-DD |
| Application / Module | User Management |
| Test Type | Manual / Automated |
| Bug Status | New |

---

## 2. Environment

| Item | Details |
|---|---|
| Operating System | Windows 11 |
| Browser | Chrome |
| Java Version | Java 17 |
| Application URL | http://localhost:8080/index.html |
| API Base URL | http://localhost:8080/api |
| Database | MySQL / H2 |
| Build Tool | Maven |
| Test Framework | JUnit 5 / Selenium / MockMvc |

---

## 3. Severity

| Severity | Description |
|---|---|
| Critical | Application crash or main functionality is completely blocked |
| High | Major feature is not working |
| Medium | Feature works partially or validation is incorrect |
| Low | Minor UI issue, typo, or cosmetic issue |

---

## 4. Priority

| Priority | Description |
|---|---|
| High | Must be fixed immediately |
| Medium | Should be fixed soon |
| Low | Can be fixed later |

---

## 5. Bug Description

Describe the issue clearly


---

## 6. Preconditions

List the conditions required before reproducing the bug:

---

## 7. Steps to Reproduce

---

## 8. Expected Result

Describe what should happen:

---

## 9. Actual Result

Describe what actually happened:

---

## 10. Test Data

| Field | Value |
|---|---|
| Name |  |
| Email |  |
| User ID |  |
| Search Keyword |  |

Example:

| Field | Value |
|---|---|
| Name | Second User |
| Email | duplicate@test.com |
| User ID | N/A |
| Search Keyword | N/A |

---

## 11. Evidence

Attach screenshots, logs, reports, or console errors.

| Evidence Type | Location / Details |
|---|---|
| Screenshot | target/screenshots/Error_XXXX.png |
| Extent Report | target/SparkReport.html |
| Console Error |  |
| API Response |  |
| Browser Network Status |  |

---

## 12. Reproducibility

---

## 13. Automation Status

If automated, provide details:

| Field | Details |
|---|---|
| Test Class |  |
| Test Method |  |
| Test Result | Passed / Failed |

Example:

| Field | Details |
|---|---|
| Test Class | UserE2ETest |
| Test Method | testDuplicateEmailFromUI |
| Test Result | Failed |

---

## 14. API Details

If the bug is related to an API, fill this section.

| Field | Details |
|---|---|
| Endpoint |  |
| Method | GET / POST / PUT / DELETE |
| Request Body |  |
| Expected Status Code |  |
| Actual Status Code |  |
| Response Body |  |

Example:

| Field | Details |
|---|---|
| Endpoint | /api/add |
| Method | POST |
| Request Body | {"name":"Second User","email":"duplicate@test.com"} |
| Expected Status Code | 400 |
| Actual Status Code | 200 |
| Response Body | User was created successfully |

---

## 15. Database Impact

Describe whether the database was affected.

Database verification:

| Check | Expected | Actual |
|---|---|---|
| User count | 1 |  |
| User exists | No / Yes |  |
| Duplicate email exists | No |  |

---

## 16. Root Cause

To be filled after investigation.
---

## 17. Suggested Fix

---

## 18. Retest Notes

To be filled after the fix.

---

## 19. Bug Lifecycle Status

| Status | Description |
|---|---|
| New | Bug has been reported |
| Open | Bug is accepted and under review |
| In Progress | Developer is fixing the issue |
| Fixed | Bug has been fixed |
| Retest | Ready for QA retesting |
| Closed | Bug is verified and closed |
| Reopened | Bug still exists after fix |


---

## 20. Final QA Status


