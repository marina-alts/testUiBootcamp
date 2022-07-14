package com.everc.automation.test;

import com.everc.automation.framework.BasePage;
import com.everc.automation.framework.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupFieldsTest extends BasePage {

    WebDriver driver;
    String emptyEmailMessage = "Please provide an email address.";
    String wrongEmailMessage = "Email address is not valid.";
    String defautEmail = generateRandomEmail();

    @BeforeEach
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver(config.browser());
        driver.manage().window().maximize();
        driver.get(config.url());
        driver.findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
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

    @Test
    public void validateSignupEmailFieldValidEmails() throws InterruptedException {

        openSignupPage(driver);

        WebElement emailField = driver.findElement(By.id("emailControl"));
        WebElement formTitle = driver.findElement(By.xpath("//h1"));

        List<String> validEmails = new ArrayList<>();
        validEmails.add(defautEmail);
        validEmails.add("t+" + defautEmail);
        validEmails.add(defautEmail + ".com");
        validEmails.add("t." + defautEmail);

        //no errors for valid emails
        for (String email : validEmails) {
            emailField.sendKeys(email);
            formTitle.click();
            Thread.sleep(1000);
            Assertions.assertTrue(driver.findElements(By.cssSelector("[id^='mat-error']")).size() == 0);
            emailField.clear();
        }
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
