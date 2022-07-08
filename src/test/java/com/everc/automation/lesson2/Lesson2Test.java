package com.everc.automation.lesson2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Lesson2Test {

    @Test
    public void verifyEvercTitle(){

        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();

        WebDriver driver = wds.getWebDriver("");

        driver.get("https://google.com");

        driver.close();
    }

    @Test
    public void verifyRandomRange(){

        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();

        WebDriver driver = wds.getWebDriver("edge");

        driver.get("https://google.com");

        driver.close();
    }

    @Test
    public void findNumber5(){

        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();

        WebDriver driver = wds.getWebDriver("firefox");

        driver.get("https://google.com");

        driver.close();
    }

}
