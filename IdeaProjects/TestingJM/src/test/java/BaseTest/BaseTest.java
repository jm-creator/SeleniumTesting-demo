package BaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomePage;
import utils.WindowManager;


import java.util.concurrent.TimeUnit;


public class BaseTest {
    protected HomePage homePage;
    protected WebDriver driver;

    @BeforeClass
    public void initialize() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(
                10, TimeUnit.SECONDS);

        driver.get("https://www.travelocity.com");
    }

    @AfterTest
    public void TeardownTest() {
        // driver.quit();
    }
    public WindowManager getWindowManager(){
        return new WindowManager(driver);
    }
}


