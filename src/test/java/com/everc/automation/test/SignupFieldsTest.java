package com.everc.automation.test;

import com.everc.automation.page.actions.LoginActions;
import com.everc.automation.framework.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupFieldsTest extends LoginActions {

    WebDriver driver;
    WebDriverWait wait;
    String emptyEmailMessage = "Please provide an email address.";
    String wrongEmailMessage = "Email address is not valid.";
    String defautEmail = generateRandomEmail();

    @BeforeEach
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver(config.browser());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        init(driver,wait);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void validateSignupEmptyEmailField() throws InterruptedException {

        openSignupPage(driver);

        WebElement emailField = driver.findElement(By.id("emailControl"));
        WebElement formTitle = driver.findElement(By.xpath("//h1"));

        //empty email error for empty email
        emailField.sendKeys("");
        formTitle.click();
        Thread.sleep(1000);
        Assertions.assertEquals(emptyEmailMessage, driver.findElement(By.cssSelector("[id^='mat-error']")).getText());
        emailField.clear();

    }

    @ParameterizedTest
    @ValueSource(strings = {"test@test.com","t+test@test.com","test@test.com.com","t.test@test.com"})
    public void validateSignupEmailFieldValidEmails(String email) throws InterruptedException {

        openSignupPage(driver);

        WebElement emailField = driver.findElement(By.id("emailControl"));
        WebElement formTitle = driver.findElement(By.xpath("//h1"));

        emailField.sendKeys(email);
        formTitle.click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElements(By.cssSelector("[id^='mat-error']")).size() == 0);
        emailField.clear();

    }

    @Test
    public void validateSignupEmailFieldInvalidEmails() throws InterruptedException {

        openSignupPage(driver);

        WebElement emailField = driver.findElement(By.id("emailControl"));
        WebElement formTitle = driver.findElement(By.xpath("//h1"));

        List<String> invalidEmails = new ArrayList<>();
        invalidEmails.add(" ");
        invalidEmails.add("123");
        invalidEmails.add("qwe");
        invalidEmails.add("t " + defautEmail);
        invalidEmails.add("t[" + defautEmail);

        //expected error for invalid emails
        for (String email : invalidEmails) {
            emailField.sendKeys(email);
            formTitle.click();
            Thread.sleep(1000);
            Assertions.assertEquals(wrongEmailMessage, driver.findElement(By.cssSelector("[id^='mat-error']")).getText());
            emailField.clear();
        }

    }

}
