package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UsersPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By submitButton = By.id("submit");
    private By searchField = By.id("searchName");
    private By searchButton = By.xpath("//button[normalize-space()='Search']");
    private By resetButton = By.xpath("//button[normalize-space()='Reset']");
    private By userTableBody = By.id("userBody");
    private By messageBox = By.id("messageBox");

    public UsersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void addUser(String name, String email) {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        driver.findElement(nameField).clear();
        driver.findElement(emailField).clear();

        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(submitButton).click();
    }

    public void searchUser(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        driver.findElement(searchButton).click();
    }

    public void resetSearch() {
        wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        driver.findElement(resetButton).click();
    }

    public void waitUntilUserIsVisibleInTable(String name) {
        WebDriverWait tableWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        tableWait.until(d -> driver.findElement(userTableBody).getText().contains(name));
    }

    public void waitUntilUserIsNotVisibleInTable(String name) {
        WebDriverWait tableWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        tableWait.until(d -> !driver.findElement(userTableBody).getText().contains(name));
    }

    public boolean isUserVisible(String name) {
        return driver.findElement(userTableBody).getText().contains(name);
    }

    public boolean isUserNotVisible(String name) {
        return !driver.findElement(userTableBody).getText().contains(name);
    }

    public void waitUntilMessageContains(String expectedMessage) {
        WebDriverWait messageWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        messageWait.until(d -> driver.findElement(messageBox).getText().contains(expectedMessage));
    }

    public String getMessageText() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(messageBox));
        return driver.findElement(messageBox).getText();
    }

    public boolean isMessageVisible(String expectedMessage) {
        return driver.findElement(messageBox).getText().contains(expectedMessage);
    }

    public void deleteFirstUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By deleteBtn = By.xpath("//button[normalize-space()='Delete']");

        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn));
        driver.findElement(deleteBtn).click();

        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    public void handleAlert() {
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            longWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (TimeoutException ignored) {
            // No alert appeared, continue the test
        }
    }
}