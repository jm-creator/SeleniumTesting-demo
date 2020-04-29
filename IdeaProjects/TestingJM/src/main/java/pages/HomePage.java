package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
    private By flightButton = By.id("tab-flight-tab-hp");



    public HomePage(WebDriver driver){
        this.driver= driver;
    }

    public Flights clickFromFlights() {
        driver.findElement(flightButton).click();
        return new Flights(driver);
    }


    }
