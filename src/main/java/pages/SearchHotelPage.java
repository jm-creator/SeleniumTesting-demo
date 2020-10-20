package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchHotelPage {

    private WebDriver driver;

    public SearchHotelPage(WebDriver driver){
        this.driver = driver;
    }


    public boolean checkForDiscountMessage(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.uitk-cell.all-cell-3-4.all-x-gutter-six")));
        return driver.findElement(By.cssSelector("div>a.all-r-margin-six")).isEnabled();
    }
}
