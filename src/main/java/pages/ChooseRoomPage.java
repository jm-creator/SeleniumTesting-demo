package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ChooseRoomPage {
    private final static Logger LOGGER = Logger.getLogger("ChooseRoomPage");
    private WebDriver driver;
    private By roomsSelectButton = By.cssSelector("a.modal-book-button.btn.btn-secondary.btn-sub-action.book-button");
    private By noThanksButton = By.xpath("//*[@id=\"covid-alert-refundability-0\"]/div/div[1]/div[2]/div[1]/a");
    private By hotelName = By.id("hotel-name");
    private By firstResult  = By.cssSelector("article.segment.no-target.room.cf.room-above-fold");
    private By hotelPrice = By.cssSelector("a.price.link-to-rooms");
    private By starsHotel = By.cssSelector("#license-plate>div>strong.star-rating.rating-secondary.star.rating");

    //---------------------------------------------------------------------

    public ChooseRoomPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean checkHotelNameIsTheSelected(String hotelName){
        LOGGER.log(Level.INFO, "check: hotel name is the selected");
        return driver.findElement(this.hotelName).getText().equals(hotelName);
    }

    public boolean checkHotelActualPriceIsTheSameToChoosingHotelPage(String hotelPrice){
        LOGGER.log(Level.INFO, "check: price is the selected");
        return driver.findElement(this.hotelPrice).getText().equals(hotelPrice);
    }

    public boolean checkTheStarsHotelAreTheSameThatPreviousPage(String starsHotel){
        LOGGER.log(Level.INFO, "check hotel stars is the selected");
        return driver.findElement(this.starsHotel).getText().equals(starsHotel);
    }

    public void selectTheFirstResult(){
        WebDriverWait wait = new WebDriverWait(driver, 40);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ads-column")));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(roomsSelectButton));
        driver.findElement(firstResult).findElement(roomsSelectButton).click();
        driver.findElement(noThanksButton).click();
    }
}
