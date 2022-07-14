package com.everc.automation.test;

import com.everc.automation.framework.BasePage;
import com.everc.automation.framework.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupTest extends BasePage {

    WebDriver driver;
    String defaultPassword = "Password123";
    String defaultSecurityAnswer = "test text";

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

        Assertions.assertEquals("https://juice-shot.herokuapp.com/#/login", driver.getCurrentUrl());
    }

}
