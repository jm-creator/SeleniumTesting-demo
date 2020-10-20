package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WhosTravelingPage {
    private WebDriver driver;

    public WhosTravelingPage(WebDriver driver){
        this.driver = driver;
    }

    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header.segment.segment-title.no-target.trip-preferences.module-header>h2.title-main")));
    }

    public boolean verifyPageIsOpenedByFiveValidations() {
        return (driver.findElement(By.cssSelector("header.segment.segment-title.no-target.trip-preferences.module-header>h2.title-main")).getText().contains("Who's flying?")
                && driver.findElement(By.id("firstname[0]")).isEnabled()
                && driver.findElement(By.id("lastname[0]")).isEnabled()
                && driver.findElement(By.id("maleRadio0")).isEnabled()
                && driver.findElement(By.id("femaleRadio0")).isEnabled());
    }


}
