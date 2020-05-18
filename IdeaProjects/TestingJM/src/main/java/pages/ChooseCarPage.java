package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChooseCarPage {
    WebDriver driver;
    private By searchResult = By.id("search-results");
    private By selectButton = By.cssSelector("a.btn.btn-secondary.btn-action.ember-view");

    public ChooseCarPage(WebDriver driver){
        this.driver = driver;
    }

    public void selectCar(){
        WebDriverWait wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));
        driver.findElement(selectButton).click();
    }
}
