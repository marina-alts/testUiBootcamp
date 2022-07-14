package com.everc.automation.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverSingleton {

    private static WebDriverSingleton instanceofWebDriver = null;

    private WebDriver webDriver;

    private WebDriverSingleton() {}

    public static WebDriverSingleton getInstanceOfWebDriverSingleton() {

        if (instanceofWebDriver == null) {
            instanceofWebDriver = new WebDriverSingleton();
            }

        return instanceofWebDriver;

    }

    public WebDriver getWebDriver(String browser) {

        switch (browser) {
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    break;
            }


        return webDriver;

    }
}
