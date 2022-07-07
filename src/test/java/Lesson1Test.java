import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Lesson1Test {

        @Test
        void openPage(){

                //System.setProperty("webdriver.chrome.driver", "D:\\chromedriver_win32\\chromedriver.exe");

                WebDriverManager.chromedriver().setup();

                WebDriver driver = new ChromeDriver();

                driver.get("https://google.com");

                driver.close();
        }
}
