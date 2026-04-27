package com.bashayer.demo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bashayer.demo.model.User;
import com.bashayer.demo.repository.UserRepository;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pages.UsersPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserE2ETest {

    @Autowired
    private UserRepository userRepository;

    private static ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;

    @BeforeAll
    static void setupReport() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/SparkReport.html");

        spark.config().setDocumentTitle("User Management E2E Report");
        spark.config().setReportName("Selenium End-to-End Test Report");
        spark.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "User Management");
        extent.setSystemInfo("Tester", "Bashayer");
        extent.setSystemInfo("Test Type", "End-to-End Testing");
        extent.setSystemInfo("Browser", "Chrome Headless");
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
        extent.setSystemInfo("Operating System", System.getProperty("os.name"));
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        userRepository.deleteAll();
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);

        test = extent.createTest(testInfo.getDisplayName());
        test.assignAuthor("Bashayer");
        test.assignCategory("E2E");
        test.assignCategory("User Management");
    }

    @Test
    @DisplayName("اختبار دورة حياة المستخدم مع التحقق من قاعدة البيانات")
    void testUserLifecycle() {
        try {
            UsersPage userPage = new UsersPage(driver);

            test.info("Opening User Management page");
            driver.get("http://localhost:8080/index.html");

            test.info("Adding first user: Bashayer Test");
            userPage.addUser("Bashayer Test", "test@test.com");
            userPage.handleAlert();

            test.info("Adding second user: Ahmad Test");
            userPage.addUser("Ahmad Test", "Ahmad@test.com");
            userPage.handleAlert();

            test.info("Waiting until users are saved in the database");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(d -> userRepository.count() == 2);

            test.info("Verifying users count in the database");
            long count = userRepository.count();
            assertEquals(2, count, "يجب أن يكون عدد المستخدمين في قاعدة البيانات 2");

            test.info("Fetching users from the database");
            List<User> users = userRepository.findAll();

            test.info("Verifying first user data in the database");
            boolean bashayerExists = users.stream()
                    .anyMatch(user -> user.getName().equals("Bashayer Test")
                            && user.getEmail().equals("test@test.com"));

            test.info("Verifying second user data in the database");
            boolean ahmadExists = users.stream()
                    .anyMatch(user -> user.getName().equals("Ahmad Test")
                            && user.getEmail().equals("Ahmad@test.com"));

            assertTrue(bashayerExists, "يجب أن يكون المستخدم Bashayer Test موجودًا في قاعدة البيانات");
            assertTrue(ahmadExists, "يجب أن يكون المستخدم Ahmad Test موجودًا في قاعدة البيانات");

            String successScreenshot = captureScreenshot("Success_" + System.currentTimeMillis());
            test.addScreenCaptureFromPath(successScreenshot);

            test.pass("Users were added and verified successfully in the database");

        } catch (Exception e) {
            handleTestFailure("User lifecycle test failed", "Error_", e);
            throw e;
        }
    }

    @Test
    @DisplayName("اختبار البحث عن المستخدم من الواجهة")
    void testSearchUserFromUI() {
        try {
            test.assignCategory("Search");

            UsersPage userPage = new UsersPage(driver);

            test.info("Opening User Management page");
            driver.get("http://localhost:8080/index.html");

            test.info("Adding first user: Bashayer Test");
            userPage.addUser("Bashayer Test", "bashayer.search@test.com");
            userPage.handleAlert();

            test.info("Adding second user: Ahmad Test");
            userPage.addUser("Ahmad Test", "ahmad.search@test.com");
            userPage.handleAlert();

            test.info("Waiting until users are saved in the database");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(d -> userRepository.count() == 2);

            test.info("Searching for user by name: Bash");
            userPage.searchUser("Bash");

            test.info("Waiting until Bashayer Test appears in search results");
            userPage.waitUntilUserIsVisibleInTable("Bashayer Test");

            test.info("Waiting until Ahmad Test disappears from search results");
            userPage.waitUntilUserIsNotVisibleInTable("Ahmad Test");

            test.info("Verifying Bashayer Test is visible after search");
            assertTrue(
                    userPage.isUserVisible("Bashayer Test"),
                    "Bashayer Test should be visible after search"
            );

            test.info("Verifying Ahmad Test is not visible after search");
            assertTrue(
                    userPage.isUserNotVisible("Ahmad Test"),
                    "Ahmad Test should not be visible after search"
            );

            String successScreenshot = captureScreenshot("Search_Success_" + System.currentTimeMillis());
            test.addScreenCaptureFromPath(successScreenshot);

            test.pass("Search user test passed successfully");

        } catch (Exception e) {
            handleTestFailure("Search user test failed", "Search_Error_", e);
            throw e;
        }
    }

    @Test
    @DisplayName("اختبار منع إضافة إيميل مكرر من الواجهة")
    void testDuplicateEmailFromUI() {
        try {
            test.assignCategory("Negative UI Test");
            test.assignCategory("Duplicate Email");

            UsersPage userPage = new UsersPage(driver);

            test.info("Opening User Management page");
            driver.get("http://localhost:8080/index.html");

            test.info("Adding first user with email: duplicate.ui@test.com");
            userPage.addUser("First User", "duplicate.ui@test.com");
            userPage.handleAlert();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(d -> userRepository.count() == 1);

            test.info("Trying to add second user with the same email");
            userPage.addUser("Second User", "duplicate.ui@test.com");
            userPage.handleAlert();

            test.info("Waiting for duplicate email error message");
            userPage.waitUntilMessageContains("Email already exists");

            test.info("Verifying duplicate email error message is displayed");
            assertTrue(
                    userPage.isMessageVisible("Email already exists"),
                    "Duplicate email message should be visible"
            );

            test.info("Verifying database still contains only one user");
            assertEquals(1, userRepository.count(), "Database should contain only one user");

            String successScreenshot = captureScreenshot("Duplicate_Email_Success_" + System.currentTimeMillis());
            test.addScreenCaptureFromPath(successScreenshot);

            test.pass("Duplicate email UI validation test passed successfully");

        } catch (Exception e) {
            handleTestFailure("Duplicate email UI test failed", "Duplicate_Email_Error_", e);
            throw e;
        }
    }

    @Test
    @DisplayName("اختبار منع إضافة إيميل غير صحيح من الواجهة")
    void testInvalidEmailFromUI() {
        try {
            test.assignCategory("Negative UI Test");
            test.assignCategory("Invalid Email");

            UsersPage userPage = new UsersPage(driver);

            test.info("Opening User Management page");
            driver.get("http://localhost:8080/index.html");

            test.info("Trying to add user with invalid email");
            userPage.addUser("Invalid Email User", "not-an-email");
            userPage.handleAlert();

            test.info("Waiting for invalid email error message");
            userPage.waitUntilMessageContains("يجب إدخال البريد الإلكتروني بصيغة صحيحة");

            test.info("Verifying invalid email error message is displayed");
            assertTrue(
                    userPage.isMessageVisible("يجب إدخال البريد الإلكتروني بصيغة صحيحة"),
                    "Invalid email message should be visible"
            );

            test.info("Verifying database is still empty");
            assertEquals(0, userRepository.count(), "Database should still be empty");

            String successScreenshot = captureScreenshot("Invalid_Email_Success_" + System.currentTimeMillis());
            test.addScreenCaptureFromPath(successScreenshot);

            test.pass("Invalid email UI validation test passed successfully");

        } catch (Exception e) {
            handleTestFailure("Invalid email UI test failed", "Invalid_Email_Error_", e);
            throw e;
        }
    }

    private void handleTestFailure(String message, String screenshotPrefix, Exception e) {
        try {
            driver.switchTo().alert().accept();
        } catch (Exception ignored) {
        }

        String screenshotPath = captureScreenshot(screenshotPrefix + System.currentTimeMillis());
        test.addScreenCaptureFromPath(screenshotPath);

        test.fail(message);
        test.fail("Exception type: " + e.getClass().getSimpleName());
        test.fail("Exception message: " + e.getMessage());
    }

    public String captureScreenshot(String name) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        new File("target/screenshots/").mkdirs();

        String path = System.getProperty("user.dir") + "/target/screenshots/" + name + ".png";

        try {
            FileUtils.copyFile(src, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterAll
    static void tearDownReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}