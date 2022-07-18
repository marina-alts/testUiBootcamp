package com.everc.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractPage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private By closeBannerButton = By.cssSelector("[aria-label='Close Welcome Banner']");

    public AbstractPage(WebDriver webDriver, WebDriverWait webDriverWait) {
        this.driver = webDriver;
        this.wait = webDriverWait;
    }

    public void closeBanner() {
        WebElement banner = wait.until(ExpectedConditions.elementToBeClickable(closeBannerButton));
        banner.click();
    }

}