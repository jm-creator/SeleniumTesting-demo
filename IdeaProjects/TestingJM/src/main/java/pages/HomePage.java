package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By flightButton = By.id("tab-flight-tab-hp");
    private By selectFlightsHotelButton = By.id("tab-package-tab-hp");



    public HomePage(WebDriver driver){
        this.driver= driver;
    }

    public Flights clickFromFlights() {
        driver.findElement(flightButton).click();
        return new Flights(driver);
    }

    public FlightsHotelsPage clickFlightsHotelCarButton(){
        driver.findElement(selectFlightsHotelButton).click();
        driver.findElement(By.xpath("//*[@id=\"gcw-packages-form-hp-package\"]/fieldset/div/div[2]")).click();
        return new FlightsHotelsPage(driver);
    }


}
