package com.everc.automation.lesson2;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static com.everc.automation.lesson2.MyConfig.config;

public class Lesson2Test {

    @Test
    public void getConfigs(){
        System.out.println(config.browser());
        System.out.println(config.url());
    }

    @Test
    public void verifyEvercTitleAndUrl(){

        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        WebDriver driver = wds.getWebDriver("");

        driver.get("https://everc.com");

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(driver.getTitle()).isEqualTo("EverC");
        softAssertions.assertThat(driver.getCurrentUrl()).isEqualTo("everc.com");

        driver.close();

        softAssertions.assertAll();
    }

    @Test
    public void verifyRandomRange1to10(){

        Random rand = new Random();
        int checkMe = rand.nextInt();

        Assertions.assertTrue(checkMe >= 1 && checkMe <= 10);

    }

    @ParameterizedTest
    @ValueSource(strings = {"https://google.com","https://everc.com"})
    public void findUrlWithEvercTitle(String url){

        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        WebDriver driver = wds.getWebDriver("firefox");

        driver.get(url);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(driver.getTitle()).isEqualTo("EverC");

        driver.close();

        softAssertions.assertAll();
    }

}
