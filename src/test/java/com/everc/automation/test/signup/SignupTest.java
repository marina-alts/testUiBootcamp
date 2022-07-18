package com.everc.automation.test.signup;

import com.everc.automation.framework.WebDriverSingleton;
import com.everc.automation.page.actions.LoginSignupActions;
import com.everc.automation.page.login.SignupPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupTest {

    SignupPage signupPage;
    WebDriver driver;
    WebDriverWait wait;
    LoginSignupActions loginActions;
    SoftAssertions softAssertions;

    @BeforeEach
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver(config.browser());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        loginActions.init(driver,wait);
        loginActions = new LoginSignupActions();
        signupPage = new SignupPage(driver,wait);
        softAssertions = new SoftAssertions();
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void canSignUp(){

        loginActions.openSignupPage(driver);

        WebElement registerButton = signupPage.getRegisterButton();

        softAssertions.assertThat(!registerButton.isEnabled());

        signupPage.fillAllSignupFields();

        softAssertions.assertThat(registerButton.isEnabled());

        signupPage.clickOnRegisterButton();

        softAssertions.assertThat(driver.getCurrentUrl()).isEqualTo("https://juice-shot.herokuapp.com/#/login");
        softAssertions.assertAll();
    }

}
