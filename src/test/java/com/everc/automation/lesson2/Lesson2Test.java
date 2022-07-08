package com.everc.automation.lesson2;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class Lesson2Test {

    @Test
    public void openBrowser(){

        WebDriver driver = WebDriverSingleton.getWebDriver("");

        driver.get("https://google.com");

        driver.close();
    }

}
