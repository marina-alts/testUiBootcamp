package com.everc.automation.lesson3;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {

    public void openSignupPage(WebDriver driver){
        driver.findElement(By.id("navbarAccount")).click();
        driver.findElement(By.id("navbarLoginButton")).click();
        driver.findElement(By.cssSelector("[href='#/register']")).click();
    }

    public String generateRandomEmail(){
        String randomEmail = RandomStringUtils.random(10,true,true) + "@test.com";
        return randomEmail;
    }

    public void signUp(WebDriver driver, String email, String password, String securityAnswer) throws InterruptedException {

        openSignupPage(driver);

        WebElement registerButton = driver.findElement(By.id("registerButton"));

        driver.findElement(By.id("emailControl")).sendKeys(email);
        driver.findElement(By.id("passwordControl")).sendKeys(password);
        driver.findElement(By.id("repeatPasswordControl")).sendKeys(password);
        driver.findElement(By.cssSelector("[id^='mat-select-value']")).click();

        Thread.sleep(5000);

        driver.findElement(By.cssSelector("[class='mat-option-text']")).click();
        driver.findElement(By.id("securityAnswerControl")).sendKeys(securityAnswer);

        registerButton.click();
    }
}
