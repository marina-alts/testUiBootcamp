public class Lesson1 {

        @Test
        void openPage(){

                System.setProperty("webdriver.chrome.driver", "chromedriver");
                //webdriver instance
                WebDriver driver = new ChromeDriver();
                // implicit wait
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                //url launch
                driver.get("https://www.tutorialspoint.com/index.htm");
                //element identify
                WebElement elm = driver.findElement(By.tagName("input"));
                //perform action - input text
                elm.sendKeys("Selenium");
                String s = elm.getAttribute("value");
                //validate result with Assertion
                Assert.assertEquals(s, "Selenium");
                //quit browser
                driver.quit();
        }
}
