package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SetPropertiesCruises {

    private WebDriver driver;
    private By goingToDropDown = By.id("cruise-destination-hp-cruise");
    private By departDayBox = By.id("cruise-start-date-hp-cruise");
    private By datePickerNextMonthButton = By.xpath("//*[@id=\"cruise-start-date-wrapper-hp-cruise\"]/div/div/button[2]");
    private By allDaysMonthDataPicker = By.xpath("//*[@id=\"cruise-start-date-wrapper-hp-cruise\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By searchButton = By.xpath("//*[@id=\"gcw-cruises-form-hp-cruise\"]/div[3]/label/button");
    private By adultsRow = By.id("cruise-adults-hp-cruise");

   public SetPropertiesCruises(WebDriver driver){
       this.driver = driver;
   }

   public void setCruiserProperties(){
       WebDriverWait wait = new WebDriverWait(driver, 30);
       wait.until(ExpectedConditions.elementToBeClickable(goingToDropDown));
       WebElement dropdown = driver.findElement(goingToDropDown);
       Select selectSortedType = new Select(dropdown);
       selectSortedType.selectByVisibleText("Europe");
       driver.findElement(departDayBox).click();
       int contMonths = 0;
       while (true) {
           contMonths++;
           if (contMonths == 2) {
               break;
           } else {
               driver.findElement(datePickerNextMonthButton).click();
           }
       }
       List<WebElement> allDatesDepartingDatePicker = driver.findElements(allDaysMonthDataPicker);
       for (WebElement ele : allDatesDepartingDatePicker) {
           String date_text = ele.getText();
           String date[] = date_text.split("\n");
           if (date[1].equals("1")) {
               ele.click();
               break;
           }
       }
   }

    public void adultsQuantity(String adults) {
        driver.findElement(adultsRow);
        WebElement dropdown = driver.findElement(adultsRow);
        Select selectAdults = new Select(dropdown);
        selectAdults.selectByVisibleText(adults);
    }

   public ChooseCruisePage clickSearchButton(){
        driver.findElement(searchButton).click();
        return new ChooseCruisePage(driver);
   }
}
