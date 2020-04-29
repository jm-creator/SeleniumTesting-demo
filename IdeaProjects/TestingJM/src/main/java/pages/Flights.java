package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;


public class Flights {
    protected WebDriver driver;

    private By flightOrigin = By.id("flight-origin-hp-flight");
    private By flightDestination = By.id("flight-destination-hp-flight");
    private By searchButton = By.xpath("//*[@id=\"gcw-flights-form-hp-flight\"]/div[8]/label");
    private By getoutday = By.id("flight-departing-hp-flight");
    private By getbackDay = By.id("flight-returning-hp-flight");
    private By adultsRow = By.id("flight-adults-hp-flight");

//---------------------------------------------------------------

    public Flights(WebDriver driver) {
        this.driver = driver;
    }

    public void setFlightOrigin(String flightOriginCity) {
        driver.findElement(flightOrigin).sendKeys(flightOriginCity);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       driver.findElement(flightOrigin).sendKeys(Keys.DOWN);
       driver.findElement(flightOrigin).sendKeys(Keys.ENTER);
    }

    public void setFlightDestination(String flightDestinationCity) {
        driver.findElement(flightDestination).sendKeys(flightDestinationCity);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(flightDestination).sendKeys(Keys.DOWN);
        driver.findElement(flightDestination).sendKeys(Keys.ENTER);
    }

    public void setGetoutday(String date) {
        driver.findElement(getoutday).clear();
        driver.findElement(getoutday).sendKeys(date);
    }

    public void setbackoutday(String date) {
        driver.findElement(getbackDay).clear();
        driver.findElement(getbackDay).sendKeys(date);
    }

    public void adultsCuantity(String cuantity){
        driver.findElement(adultsRow).sendKeys(cuantity);
}

    public ResultPageOfFlightsPage searchButtonClick() {
        driver.findElement(searchButton).click();
    return new ResultPageOfFlightsPage(driver);
    }

}


