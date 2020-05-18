package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class SetPropertiesHotels {

    private WebDriver driver;
    private By goingTo = By.id("hotel-destination-hp-hotel");
    private By adultsRow = By.id("hotel-1-adults-hp-hotel");
    private By checkInBox = By.id("hotel-checkin-hp-hotel");
    private By checkOutBox = By.id("hotel-checkout-hp-hotel");
    private By searchButton = By.cssSelector("#gcw-hotel-form-hp-hotel>div>label>button.btn-primary.btn-action.gcw-submit ");
    private By checkOutDatePickerDays = By.xpath("//*[@id=\"hotel-checkout-wrapper-hp-hotel\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By checkInDatePickerDays = By.xpath("//*[@id=\"hotel-checkin-wrapper-hp-hotel\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By datePickerNextMonthButton = By.cssSelector("button.datepicker-paging.datepicker-next.btn-paging.btn-secondary.next");

    public SetPropertiesHotels(WebDriver driver){
        this.driver=driver;
    }

    public void completeSearch(String city, String checkInDay, String checkOutDay) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(goingTo).sendKeys(city);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aria-option-0")));
        driver.findElement(goingTo).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(goingTo).sendKeys(Keys.ENTER);
        driver.findElement(checkInBox).click();
        int contMonths = 0;
        while (true) {
            contMonths++;

            if (contMonths == 3) {
                break;
            } else {
                driver.findElement(datePickerNextMonthButton).click();
            }
        }
        List<WebElement> allDatesDepartingDatePicker = driver.findElements(checkInDatePickerDays);
        for (WebElement ele : allDatesDepartingDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(checkInDay)) {
                ele.click();
                break;
            }
        }
        driver.findElement(checkOutBox).click();
        List<WebElement> allDatesReturningDatePicker = driver.findElements(checkOutDatePickerDays);
        for (WebElement ele : allDatesReturningDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(checkOutDay)) {
                ele.click();
                break;
            }
        }
    }

    public SearchHotelPage clickSearchButton() {
        driver.findElement(adultsRow);
        WebElement dropdown = driver.findElement(adultsRow);
        Select selectAdults = new Select(dropdown);
        selectAdults.selectByVisibleText("1");
        driver.findElement(searchButton).click();
        return new SearchHotelPage(driver);
    }
    }

