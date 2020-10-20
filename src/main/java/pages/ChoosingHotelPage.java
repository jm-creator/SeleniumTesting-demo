package pages;


import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChoosingHotelPage {
    private final static Logger LOGGER = Logger.getLogger("ChoosingHotelPage");
    private WebDriver driver;
    private By priceSortButton = By.cssSelector("li>[data-opt-group=Price]");
    private By hotelResultTitle = By.id("hotelResultTitle");
    private By price = By.cssSelector(".actualPrice.price.fakeLink.covidUrgencyMsg");
    private By hotelSearchResults = By.cssSelector("[class=flex-link-wrap]");
    private By hotelStars = By.cssSelector("li>strong>span.visuallyhidden");
    private String hotelName;
    private String actualHotelPrice;
    private String hotelStarsText;
    //-----------------------------------------------------------------------------------------

    public String getHotelName() {
        return hotelName;
    }

    public String getActualHotelPrice() {
        return actualHotelPrice;
    }

    public String getHotelStarsText(){
        return hotelStarsText;
    }

    public ChoosingHotelPage(WebDriver driver) {
        this.driver = driver;
    }


    public void waitForResultPage() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hotelResultTitle));
    }

    public boolean verifyChoosingHotelPage(){
        LOGGER.log(Level.INFO,"verify page by check for elements enabled and displayed");
        return driver.findElement(By.cssSelector("h1.section-header-main")).getText().contains("Start by choosing your hotel")
                && driver.findElement(By.id("inpHotelNameMirror")).isEnabled()
                && driver.findElement(By.id("lodgingType-legend")).isDisplayed()
                && driver.findElement(By.id("star-legend")).isDisplayed()
                && driver.findElement(By.id("neighborhood-legend")).isDisplayed();
    }

    public boolean CheckOrderByPrice() {
        LOGGER.log(Level.INFO, "order results by price and check order");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        driver.findElement(priceSortButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(price));
        wait.until(ExpectedConditions.visibilityOfElementLocated(price));
        List<String> prices = driver.findElements(price).stream().map(WebElement::getText).collect(Collectors.toList());
        String myRegex = "[^0-9]";
        prices = prices.stream().map(s -> s.replaceAll(myRegex,"")).collect(Collectors.toList());
        List<Integer> intList = prices.stream().map(Integer::valueOf).collect(Collectors.toList());
        LOGGER.log(Level.INFO, "List of Prices: "+intList);
        return Ordering.natural().isOrdered(intList);
    }


    public void selectTheFirstResultWith3Stars() {
        driver.findElement(priceSortButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccol")));
        wait.until(ExpectedConditions.elementToBeClickable(price));
        List<WebElement> allSearchResults = driver.findElements(hotelSearchResults);
            for (WebElement ele : allSearchResults) {
                if (ele.findElement(hotelStars).getText().equals("3.0 out of 5.0")) {
                    hotelName = ele.findElement(By.cssSelector("h4.hotelName.fakeLink")).getText();
                    actualHotelPrice = ele.findElement(price).getText();
                    hotelStarsText = ele.findElement(hotelStars).getText();
                    ele.click();
                    break;
                }
            }
         LOGGER.log(Level.INFO,"info hotel selected: name = "+ hotelName+" - price = "+ actualHotelPrice+
                 " - stars = "+hotelStarsText);
        }
    }
