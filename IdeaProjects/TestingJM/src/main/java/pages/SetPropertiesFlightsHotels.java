package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SetPropertiesFlightsHotels {
    private final static Logger LOGGER = Logger.getLogger("SetPropertiesFlightsHotels");
    private WebDriver driver;
    private By flyingForm = By.id("package-origin-hp-package");
    private By flyingTo = By.id("package-destination-hp-package");
    private By departing = By.id("package-departing-hp-package");
    private By selectFlightsHotelButton = By.id("tab-package-tab-hp");
    private By returning = By.id("package-returning-hp-package");
    private By adultsRow = By.id("package-1-adults-hp-package");
    private By datePickerNextMonthButton = By.xpath("//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/button[2]");
    private By DatesDepartingDatePicker = By.xpath("//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By DatesReturningDatePicker = By.xpath("//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By searchButton = By.id("search-button-hp-package");
    private By onlyNeedHotelForPart = By.id("partialHotelBooking-hp-package");
    private By checkInBox = By.id("package-checkin-hp-package");
    private By nextMontCheckInDatePicker = By.xpath("//*[@id=\"package-checkin-wrapper-hp-package\"]/div/div/button[2]");
    private By datesCheckInDatePicker = By.xpath("//*[@id=\"package-checkin-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By datesCheckOutDatePicker = By.xpath("//*[@id=\"package-checkout-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By checkOutBox = By.id("package-checkout-hp-package");
    private By errorMessage = By.cssSelector("a.error-link");
    //----------------------------------------------------------------------------------------------------

    public SetPropertiesFlightsHotels(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkForPage() {
        return driver.findElement(selectFlightsHotelButton).isSelected();
    }

    public void completeData(String flyingFromCity, String departingDay, String flyingToCity, String returningDay) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        driver.findElement(flyingForm).sendKeys(flyingFromCity);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aria-option-0")));
        driver.findElement(flyingForm).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(flyingForm).sendKeys(Keys.ENTER);
        driver.findElement(flyingTo).sendKeys(flyingToCity);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("aria-option-0")));
        driver.findElement(flyingTo).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(flyingTo).sendKeys(Keys.ENTER);
        driver.findElement(departing).click();
        int contMonths = 0;
        while (true) {
            contMonths++;

            if (contMonths == 3) {
                break;
            } else {
                driver.findElement(datePickerNextMonthButton).click();
            }
        }
        List<WebElement> allDatesDepartingDatePicker = driver.findElements(DatesDepartingDatePicker);
        for (WebElement ele : allDatesDepartingDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(departingDay)) {
                ele.click();
                break;
            }
        }
        driver.findElement(returning).click();
        List<WebElement> allDatesReturningDatePicker = driver.findElements(DatesReturningDatePicker);
        for (WebElement ele : allDatesReturningDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(returningDay)) {
                ele.click();
                break;
            }
        }
    }

    public void setHotelProperties(String departingDay,String returningDay ){
        driver.findElement(onlyNeedHotelForPart).click();
        driver.findElement(checkInBox).click();
        int contMonths = 0;
        while (true) {
            contMonths++;

            if (contMonths == 2) {
                break;
            } else {
                driver.findElement(nextMontCheckInDatePicker).click();
            }
        }
        List<WebElement> allDatesDepartingDatePicker = driver.findElements(datesCheckInDatePicker);
        for (WebElement ele : allDatesDepartingDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(departingDay)) {
                ele.click();
                break;
            }
        }
        driver.findElement(checkOutBox).click();
        List<WebElement> allDatesReturningDatePicker = driver.findElements(datesCheckOutDatePicker);
        for (WebElement ele : allDatesReturningDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(returningDay)) {
                ele.click();
                break;
            }
        }
    }

    public ChoosingHotelPage clickSearchButton() {
        driver.findElement(adultsRow);
        WebElement dropdown = driver.findElement(adultsRow);
        Select selectAdults = new Select(dropdown);
        selectAdults.selectByVisibleText("1");
        driver.findElement(searchButton).click();
        return new ChoosingHotelPage(driver);
    }

    public String getErrorMessage(){
        String message = driver.findElement(errorMessage).getText();
        LOGGER.log(Level.INFO, "check for the error message is displayed: "+message);
        return message;
    }

}



