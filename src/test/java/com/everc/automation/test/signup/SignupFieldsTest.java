package com.everc.automation.test.signup;

import com.everc.automation.framework.WebDriverSingleton;
import com.everc.automation.page.actions.LoginSignupActions;
import com.everc.automation.page.login.SignupPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static com.everc.automation.config.MyConfig.config;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SignupFieldsTest{

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
    public void validateSignupEmptyEmailField(){

        loginActions.openSignupPage(driver);

        signupPage.enterEmail("");
        signupPage.getSignupTitle().click();

        List<WebElement> listOfErrors = signupPage.getErrors();

        softAssertions.assertThat(listOfErrors.size()==1);
        softAssertions.assertThat(listOfErrors.get(0).getText()).isEqualTo(SignupPage.emptyEmailMessage);

        softAssertions.assertAll();

    }

    @ParameterizedTest
    @ValueSource(strings = {"test@test.com","t+test@test.com","test@test.com.com","t.test@test.com"})
    public void validateSignupEmailFieldValidEmails(String email) throws InterruptedException {

        loginActions.openSignupPage(driver);

        signupPage.enterEmail(email);
        signupPage.getSignupTitle().click();

        Assertions.assertTrue(signupPage.getErrors().size()==0);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ","123","qwe@","t test@test.com","t[test@test.com"})
    public void validateSignupEmailFieldInvalidEmails(String email) {

        loginActions.openSignupPage(driver);

        signupPage.enterEmail(email);
        signupPage.getSignupTitle().click();

        List<WebElement> listOfErrors = signupPage.getErrors();

        softAssertions.assertThat(listOfErrors.size()==1);
        softAssertions.assertThat(listOfErrors.get(0).getText()).isEqualTo(SignupPage.wrongEmailMessage);

        softAssertions.assertAll();

    }

}
