package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By flightButton = By.id("tab-flight-tab-hp");
    private By selectFlightsHotelButton = By.id("tab-package-tab-hp");
    private By cruisesButton = By.id("tab-cruise-tab-hp");


    public HomePage(WebDriver driver){
        this.driver= driver;
    }

    public SetPropertiesFlights clickFromFlights() {
        driver.findElement(flightButton).click();
        return new SetPropertiesFlights(driver);
    }

    public SetPropertiesFlightsHotels clickFlightsHotelCarButton(){
        driver.findElement(selectFlightsHotelButton).click();
        driver.findElement(By.xpath("//*[@id=\"gcw-packages-form-hp-package\"]/fieldset/div/div[2]")).click();
        return new SetPropertiesFlightsHotels(driver);
    }

    public SetPropertiesHotels clickHotelButton(){
        driver.findElement(By.id("tab-hotel-tab-hp")).click();
        return new SetPropertiesHotels(driver);
    }

    public SetPropertiesCruises clickCruisesButton(){
        driver.findElement(cruisesButton).click();
        return new SetPropertiesCruises(driver);
    }
}
