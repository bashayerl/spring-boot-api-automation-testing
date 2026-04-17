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
            try {
                driver.switchTo().alert().accept();
            } catch (Exception ignored) {
            }

            String screenshotPath = captureScreenshot("Error_" + System.currentTimeMillis());
            test.addScreenCaptureFromPath(screenshotPath);

            test.fail("Test failed");
            test.fail("Exception type: " + e.getClass().getSimpleName());
            test.fail("Exception message: " + e.getMessage());

            throw e;
        }
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