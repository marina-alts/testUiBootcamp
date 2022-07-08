package com.everc.automation.lesson2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverSingleton {

    private static WebDriver webDriver;

    private WebDriverSingleton() {}

    public static WebDriver getWebDriver(String browser) {

        if (webDriver == null) {
            switch (browser) {
                case "edge":
                    webDriver = new EdgeDriver();
                    break;
                case "firefox":
                    webDriver = new FirefoxDriver();
                    break;
                default:
                    webDriver = new ChromeDriver();
                    break;
            }
        }

        return webDriver;

    }
}
