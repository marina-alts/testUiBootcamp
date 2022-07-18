package com.everc.automation.page.login;

import com.everc.automation.model.Customer;
import com.everc.automation.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage extends AbstractPage {


    private By accountName = By.cssSelector("[aria-label='Go to user profile'][aria-disabled=false] span");
    private By registerButton =  By.id("registerButton");
    private By emailInput =  By.id("emailControl");
    private By passwordInput =  By.id("passwordControl");
    private By repeatPasswordInput =  By.id("repeatPasswordControl");
    private By securityAnswerDropdown =  By.cssSelector("[id^='mat-select-value']");
    private By securityAnswerDropdownOption =  By.cssSelector("[class='mat-option-text']");
    private By securityAnswer =  By.id("securityAnswerControl");
    private By navbarAccountButton =  By.id("navbarAccount");
    private By navbarLoginButton =  By.id("navbarLoginButton");

    public SignupPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver,webDriverWait);
    }

    public WebElement getRegisterButton() {
        return driver.findElement(registerButton);
    }

    public void enterEmail(String email) {
        WebElement emailField = driver.findElement(emailInput);
        emailField.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordField = driver.findElement(passwordInput);
        passwordField.sendKeys(password);
        WebElement repeatPasswordField = driver.findElement(repeatPasswordInput);
        repeatPasswordField.sendKeys(password);
    }

    public void enterSecurityQuestionAnswer(String answer) {
        driver.findElement(securityAnswerDropdown).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(securityAnswerDropdownOption));

        driver.findElement(securityAnswerDropdownOption).click();
        driver.findElement(securityAnswer).sendKeys(answer);
    }

    public void clickOnRegisterButton() {
        WebElement loginSubmitButton = driver.findElement(registerButton);
        loginSubmitButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
    }

    public void clickOnAccountButton() {
        WebElement accountButton = driver.findElement(navbarAccountButton);
        accountButton.click();
    }

    public void clickOnLoginNavbarButton() {
        WebElement loginButton = driver.findElement(navbarLoginButton);;
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
    }

    public String getUserAccountName() {
        WebElement userAccountName = driver.findElement(accountName);
        return userAccountName.getText();
    }

    public void fillAllSignupFields(){
        enterEmail(Customer.defaultEmail);
        enterPassword(Customer.defaultPassword);
        enterSecurityQuestionAnswer(Customer.defaultSecurityAnswer);
    }

    public void fillAllSignupFields(Customer customer){
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        enterSecurityQuestionAnswer(customer.getSecurityAnswer());
        wait.until(ExpectedConditions.elementToBeClickable(registerButton));
    }
}