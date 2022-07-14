package com.everc.automation.test;

import com.everc.automation.framework.BasePage;
import com.everc.automation.framework.WebDriverSingleton;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest extends BasePage {

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
    public void canLogin() throws InterruptedException {

        String email = generateRandomEmail();

        signUp(driver, email, defaultPassword, defaultSecurityAnswer);

        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.id("navbarLoginButton")).click();

        Thread.sleep(1000);

        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("password")).sendKeys(defaultPassword);

        Assertions.assertTrue(driver.findElement(By.id("loginButton")).isEnabled());
        driver.findElement(By.id("loginButton")).click();

        Thread.sleep(5000);

        driver.findElement(By.id("navbarAccount")).click();
        Assertions.assertEquals(email, driver.findElement(By.cssSelector("[aria-label='Go to user profile'][role='menuitem'] span")).getText());
    }

}
