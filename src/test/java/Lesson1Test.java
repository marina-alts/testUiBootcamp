import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Lesson1Test {

        @Test
        void openPage(){

                System.setProperty("webdriver.chrome.driver", "chromedriver");

                WebDriver driver = new ChromeDriver();

                driver.get("google.com");

                driver.close();
        }
}
