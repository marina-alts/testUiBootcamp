package com.everc.automation.page.actions;

import com.everc.automation.model.Customer;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.everc.automation.config.MyConfig.config;

public class LoginSignupActions {

    public void openSignupPage(WebDriver driver){
        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.id("navbarLoginButton")).click();
        driver.findElement(By.cssSelector("[href='#/register']")).click();
    }

    public static String generateRandomEmail(){
        String randomEmail = RandomStringUtils.random(10,true,true) + "@test.com";
        return randomEmail;
    }

    public void signUp(WebDriver driver, WebDriverWait wait, Customer customer){

        openSignupPage(driver);

        WebElement registerButton = driver.findElement(By.id("registerButton"));

        driver.findElement(By.id("emailControl")).sendKeys(customer.getEmail());
        driver.findElement(By.id("passwordControl")).sendKeys(customer.getPassword());
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(customer.getPassword());
        driver.findElement(By.cssSelector("[id^='mat-select-value']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='mat-option-text']")));

        driver.findElement(By.cssSelector("[class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(customer.getSecurityAnswer());

        wait.until(ExpectedConditions.elementToBeClickable(registerButton));

        Assertions.assertTrue(registerButton.isEnabled());

        registerButton.click();

    }

    public void signUp(WebDriver driver, WebDriverWait wait){

        openSignupPage(driver);

        WebElement registerButton = driver.findElement(By.id("registerButton"));

        driver.findElement(By.id("emailControl")).sendKeys(Customer.defaultEmail);
        driver.findElement(By.id("passwordControl")).sendKeys(Customer.defaultPassword);
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(Customer.defaultPassword);
        driver.findElement(By.cssSelector("[id^='mat-select-value']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='mat-option-text']")));

        driver.findElement(By.cssSelector("[class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(Customer.defaultSecurityAnswer);

        wait.until(ExpectedConditions.elementToBeClickable(registerButton));

        Assertions.assertTrue(registerButton.isEnabled());

        registerButton.click();

    }

    public static void init(WebDriver driver, WebDriverWait wait){

        driver.manage().window().maximize();
        driver.get(config.url());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Close Welcome Banner']")));
        driver.findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
    }
}
