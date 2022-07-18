package com.everc.automation.test.product;

import com.everc.automation.framework.WebDriverSingleton;
import com.everc.automation.model.Product;
import com.everc.automation.page.actions.LandingPageActions;
import com.everc.automation.page.login.ProductModalPage;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.everc.automation.config.MyConfig.config;

public class ProductInfoTest {
    WebDriver driver;
    WebDriverWait wait;
    LandingPageActions landingPageActions;
    SoftAssertions softAssertions;
    ProductModalPage productModalPage;

    @BeforeEach
    public void init() {
        WebDriverSingleton wds = WebDriverSingleton.getInstanceOfWebDriverSingleton();
        driver = wds.getWebDriver(config.browser());
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        landingPageActions.init(driver,wait);
        landingPageActions = new LandingPageActions();
        softAssertions = new SoftAssertions();
        productModalPage = new ProductModalPage(driver,wait);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }

    @Test
    public void checkFirstProductInfo(){

        softAssertions.assertThat(landingPageActions.getProductCards(driver).size() != 0);
        landingPageActions.getProductCards(driver).get(0).click();

        Product product = productModalPage.getProduct();

        softAssertions.assertThat(product.getImage()).isEqualTo("https://juice-shot.herokuapp.com/assets/public/images/products/20th.jpeg");
        softAssertions.assertThat(product.getName()).isEqualTo("20th Anniversary Celebration Ticket");
        softAssertions.assertThat(product.getDescription()).isEqualTo("Get your free \uD83C\uDFAB for OWASP 20th Anniversary Celebration online conference! Hear from world renowned keynotes and special speakers, network with your peers and interact with our event sponsors. With an anticipated 10k+ attendees from around the world, you will not want to miss this live on-line event!");
        softAssertions.assertThat(product.getPrice()).isEqualTo("1e-20¤");
        softAssertions.assertThat(product.getReviews()).isEqualTo("Reviews\n" + "(1)");

        softAssertions.assertAll();
    }
}
