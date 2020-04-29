package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TripDetailsPage {
    private WebDriver driver;
    private By departureInfo = By.id("flightDetailsToggle-0");
    private By returnInfo = By.id("flightDetailsToggle-1");
    private By tripTotal = By.xpath("/html/body/main/div/div[2]/section[1]/div/div[2]/div/div[1]/span[2]");
    private By continueBookingButton = By.id("bookButton");
    private By textGuarantee = By.xpath("/html/body/main/div/div[2]/section[1]/div/div[2]/div/div[2]");
    public TripDetailsPage(WebDriver driver) {
        this.driver = driver;

    }

    public boolean checkForTotalMessage(){
        if (driver.findElement(tripTotal).isDisplayed())
            return true;
        return false;
    }

    public boolean checkForDepartureAndReturnInfo() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if ( driver.findElement(departureInfo).isEnabled() & driver.findElement(returnInfo).isEnabled())
            return true;
        return false;
    }

    public boolean checkPriceGuaranteeText(){
       if( driver.findElement(textGuarantee).isDisplayed() )
           return true;
       return false;
    }

    public void clickContinueBooking(){
        driver.findElement(continueBookingButton).click();
    }
}