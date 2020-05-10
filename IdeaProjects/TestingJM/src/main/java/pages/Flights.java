package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class Flights {
    protected WebDriver driver;

    private By flightOrigin = By.id("flight-origin-hp-flight");
    private By flightDestination = By.id("flight-destination-hp-flight");
    private By searchButton = By.xpath("//*[@id=\"gcw-flights-form-hp-flight\"]/div[8]/label");
    private By departingBox = By.id("flight-departing-hp-flight");
    private By returningBox = By.id("flight-returning-hp-flight");
    private By adultsRow = By.id("flight-adults-hp-flight");
    private By datePickerNextMonthButton = By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/button[2]");
    private By allDaysMonthDataPicker = By.xpath("//*[@id=\"flight-departing-wrapper-hp-flight\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By allDaysMonthsDataPickerReturn = By.xpath("//*[@id=\"flight-returning-wrapper-hp-flight\"]/div/div[2]/div[2]/table/tbody/tr/td/button");
    private By visibilityDataPickerText = By.id("currency-banner-content");

//---------------------------------------------------------------

    public Flights(WebDriver driver) {
        this.driver = driver;
    }

    public void completeFlyingData(String flyingFromCity, String departingDay, String flyingToCity, String returningDay) {
        driver.findElement(flightOrigin).sendKeys(flyingFromCity);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(flightOrigin).sendKeys(Keys.DOWN);
        driver.findElement(flightOrigin).sendKeys(Keys.ENTER);
        driver.findElement(flightDestination).sendKeys(flyingToCity);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(flightDestination).sendKeys(Keys.DOWN);
        driver.findElement(flightDestination).sendKeys(Keys.ENTER);
        driver.findElement(departingBox).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(visibilityDataPickerText));
        int contMonths = 0;
        while (true) {
            contMonths++;
            if (contMonths == 3) {
                break;
            } else {
                driver.findElement(datePickerNextMonthButton).click();
            }
        }
        List<WebElement> allDatesDepartingDatePicker = driver.findElements(allDaysMonthDataPicker);
        for (WebElement ele : allDatesDepartingDatePicker) {
            String date_text = ele.getText();
            String date[] = date_text.split("\n");
            if (date[1].equals(departingDay)) {
                ele.click();
                break;
            }
        }
        driver.findElement(returningBox).click();

        List<WebElement> allDatesReturningDatePicker = driver.findElements(allDaysMonthsDataPickerReturn);
        for (WebElement ele : allDatesReturningDatePicker) {
            String date_text = ele.getText();
            String date[] = date_text.split("\n");
            if (date[1].equals(returningDay)) {
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

    public ChooseFlightsPage searchButtonClick() {
        driver.findElement(searchButton).click();
        return new ChooseFlightsPage(driver);
    }

}


