package com.everc.automation.page.actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static com.everc.automation.config.MyConfig.config;

public class LandingPageActions {
    private By productCard = By.className("mat-figure");

    public List<WebElement> getProductCards (WebDriver driver){
        List<WebElement> productCardList = new ArrayList<>();
        productCardList = driver.findElements(productCard);
        return productCardList;
    }

    public static void init(WebDriver driver, WebDriverWait wait){
        driver.manage().window().maximize();
        driver.get(config.url());
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[aria-label='Close Welcome Banner']")));
        driver.findElement(By.cssSelector("[aria-label='Close Welcome Banner']")).click();
    }
}
