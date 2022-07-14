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
class SignupTest extends BasePage {

    WebDriver driver;
    String defaultPassword = "Password123";
    String defaultSecurityAnswer = "test text";
    String emptyEmailMessage = "Please provide an email address.";
    String wrongEmailMessage = "Email address is not valid.";

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
    public void canSignUp() throws InterruptedException {

        openSignupPage(driver);
        String email = generateRandomEmail();

        WebElement registerButton = driver.findElement(By.id("registerButton"));

        Assertions.assertFalse(registerButton.isEnabled());

        driver.findElement(By.id("emailControl")).sendKeys(email);
        driver.findElement(By.id("passwordControl")).sendKeys(defaultPassword);
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(defaultPassword);
        driver.findElement(By.cssSelector("[id^='mat-select-value']")).click();

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("[class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(defaultSecurityAnswer);

        Assertions.assertTrue(registerButton.isEnabled());

        registerButton.click();

        Thread.sleep(5000);

        Assertions.assertEquals("https://juice-shot.herokuapp.com/#/login",driver.getCurrentUrl());
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
        validEmails.add(generateRandomEmail());
        validEmails.add("t+" + generateRandomEmail());
        validEmails.add(generateRandomEmail() + ".com");
        validEmails.add("t." + generateRandomEmail());

        //no errors for valid emails
        for (String email:validEmails) {
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
        invalidEmails.add("t " + generateRandomEmail());
        invalidEmails.add("t[" + generateRandomEmail());

        //expected error for invalid emails
        for (String email:invalidEmails) {
            emailField.sendKeys(email);
            formTitle.click();
            Thread.sleep(1000);
            Assertions.assertEquals(wrongEmailMessage, driver.findElement(By.cssSelector("[id^='mat-error']")).getText());
            emailField.clear();
        }

    }

}
