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

    public void deleteFirstUser() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        By deleteBtn = By.xpath("//button[text()='Delete']");

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