package com.everc.automation.test.login;

import com.everc.automation.framework.WebDriverSingleton;
import com.everc.automation.model.Customer;
import com.everc.automation.page.actions.LoginSignupActions;
import com.everc.automation.page.login.LoginPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginTest {

    LoginPage loginPage;
    WebDriver driver;
    WebDriverWait wait;
    LoginSignupActions loginActions;
    SoftAssertions softAssertions;

    @BeforeEach
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver(config.browser());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginSignupActions.init(driver,wait);
        loginActions = new LoginSignupActions();
        loginPage = new LoginPage(driver,wait);
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void canLogin(){

        loginActions.signUp(driver, wait);

        loginPage.clickOnAccountButton();
        loginPage.clickOnLoginNavbarButton();

        softAssertions.assertThat(!loginPage.getLoginButton().isEnabled());

        loginPage.enterEmail(Customer.defaultEmail);
        loginPage.enterPassword(Customer.defaultPassword);

        softAssertions.assertThat(loginPage.getLoginButton().isEnabled());

        loginPage.clickOnLoginButton();
        loginPage.clickOnAccountButton();

        wait.until(ExpectedConditions.textToBe(loginPage.getUserAccount(), Customer.defaultEmail));

        softAssertions.assertThat(loginPage.getUserAccountName()).isEqualTo(Customer.defaultEmail);

        softAssertions.assertAll();
    }

}
