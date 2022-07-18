package com.everc.automation.page.login;

import com.everc.automation.model.Customer;
import com.everc.automation.page.AbstractPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPage {


    private By accountName = By.cssSelector("[aria-label='Go to user profile'][aria-disabled=false] span");
    private By loginButton =  By.id("loginButton");
    private By passwordInput =  By.id("password");
    private By emailInput =  By.id("email");
    private By navbarAccountButton =  By.id("navbarAccount");
    private By navbarLoginButton =  By.id("navbarLoginButton");

    public LoginPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        super(webDriver,webDriverWait);
    }

    public void enterEmail(String username) {
        WebElement emailInput = driver.findElement(By.name("email"));
        emailInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);
    }

    public void clickOnLoginButton() {
        WebElement loginSubmitButton = driver.findElement(loginButton);
        loginSubmitButton.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.id("navbarAccount")));
    }

    public WebElement getLoginButton() {
        WebElement loginSubmitButton = driver.findElement(loginButton);
        return loginSubmitButton;
    }

    public void clickOnAccountButton() {
        WebElement accountButton = driver.findElement(By.id("navbarAccount"));
        accountButton.click();
    }

    public void clickOnLoginNavbarButton() {
        WebElement loginButton = driver.findElement(By.id("navbarLoginButton"));;
        loginButton.click();
        wait.until(ExpectedConditions.urlContains("login"));
    }

    public String getUserAccountName() {
        WebElement userAccountName = driver.findElement(accountName);
        return userAccountName.getText();
    }

    public By getUserAccount() {
        By userAccountName = accountName;
        return userAccountName;
    }

    public void loginAs(Customer customer) {
        clickOnAccountButton();
        clickOnLoginNavbarButton();
        enterEmail(customer.getEmail());
        enterPassword(customer.getPassword());
        clickOnLoginButton();
    }

    public void loginAs() {
        clickOnAccountButton();
        clickOnLoginNavbarButton();
        enterEmail(Customer.defaultEmail);
        enterPassword(Customer.defaultPassword);
        clickOnLoginButton();
    }
}