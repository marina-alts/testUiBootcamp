package com.everc.automation.lesson3;

import com.everc.automation.lesson2.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Lesson3Test extends BasePage {

    WebDriver driver;
    String defaultPassword = "Password123";
    String defaultSecurityAnswer = "test text";

    @BeforeAll
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver("");
        driver.manage().window().setSize(new Dimension(1600,900));
        driver.get("https://juice-shot.herokuapp.com/#/");
        driver.findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
    }

    @AfterAll
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
        driver.findElement(By.cssSelector("[class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(defaultSecurityAnswer);

        Assertions.assertTrue(registerButton.isEnabled());

        registerButton.click();

        Thread.sleep(5000);

        Assertions.assertEquals("https://juice-shot.herokuapp.com/#/login",driver.getCurrentUrl());
    }

    @Test
    public void validateSignupEmailField() throws InterruptedException {

        String emptyEmailMessage = "Please provide an email address.";
        String wrongEmailMessage = "Email address is not valid.";

        openSignupPage(driver);

        WebElement emailField = driver.findElement(By.id("emailControl"));
        WebElement formTitle = driver.findElement(By.xpath("//h1"));

        List<String> validEmails = new ArrayList<>();
        validEmails.add(generateRandomEmail());
        validEmails.add("t+" + generateRandomEmail());
        validEmails.add(generateRandomEmail() + ".com");
        validEmails.add("t." + generateRandomEmail());

        List<String> invalidEmails = new ArrayList<>();
        invalidEmails.add(" ");
        invalidEmails.add("123");
        invalidEmails.add("qwe");
        invalidEmails.add("t " + generateRandomEmail());
        invalidEmails.add("t[" + generateRandomEmail());

        //empty email error for empty email
        emailField.sendKeys("");
        formTitle.click();
        Thread.sleep(1000);
        Assertions.assertEquals(emptyEmailMessage, driver.findElement(By.cssSelector("[id^='mat-error']")).getText());
        emailField.clear();

        //no errors for valid emails
        for (String email:validEmails) {
            emailField.sendKeys(email);
            formTitle.click();
            Thread.sleep(1000);
            Assertions.assertTrue(driver.findElements(By.cssSelector("[id^='mat-error']")).size() == 0);
            emailField.clear();
        }

        //expected error for valid emails
        for (String email:invalidEmails) {
            emailField.sendKeys(email);
            formTitle.click();
            Thread.sleep(1000);
            Assertions.assertEquals(wrongEmailMessage, driver.findElement(By.cssSelector("[id^='mat-error']")).getText());
            emailField.clear();
        }

    }

    @Test
    public void canLogin() throws InterruptedException {

        String email = generateRandomEmail();

        signUp(driver,email, defaultPassword, defaultSecurityAnswer);

        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.id("navbarLoginButton")).click();

        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(defaultPassword);

        Assertions.assertTrue(driver.findElement(By.id("loginButton")).isEnabled());

        driver.findElement(By.id("loginButton")).click();

        Thread.sleep(5000);

        driver.findElement(By.id("navbarAccount")).click();

        Assertions.assertEquals(email,driver.findElement(By.cssSelector("[aria-label='Go to user profile'][role='menuitem'] span")).getText());
    }

}
