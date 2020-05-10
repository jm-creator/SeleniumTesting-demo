package pages;


import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ChoosingHotelPage {
    private WebDriver driver;
    private By priceSortButton = By.xpath("//*[@id=\"sortContainer\"]/div/div/div[2]/div/fieldset/ul/li[3]/button");
    private By hotelResultTitle = By.id("hotelResultTitle");
    private By resultsContainer = By.xpath("//*[@id=\"resultsContainer\"]/section");
    private By searchResults = By.cssSelector("[class=flex-link-wrap]");
    private By price = By.cssSelector("li.actualPrice.price.fakeLink.covidUrgencyMsg");
    private By resultHotelContainer = By.id("resultsContainer");
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
        return driver.findElement(By.cssSelector("h1.section-header-main")).getText().contains("Start by choosing your hotel")
                && driver.findElement(By.id("inpHotelNameMirror")).isEnabled()
                && driver.findElement(By.id("lodgingType-legend")).isDisplayed()
                && driver.findElement(By.id("star-legend")).isDisplayed()
                && driver.findElement(By.id("neighborhood-legend")).isDisplayed();
    }

    public boolean CheckOrderByPrice() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        driver.findElement(priceSortButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultsContainer));
        List<String> prices = driver.findElements(price).stream().map(WebElement::getText).collect(Collectors.toList());
        String myRegex = "[^0-9]";
        prices = prices.stream().map(s -> s.replaceAll(myRegex,"")).collect(Collectors.toList());
        System.out.println(prices);
        List<Integer> intList = prices.stream().map(Integer::valueOf).collect(Collectors.toList());
        return Ordering.natural().isOrdered(intList);
    }

    public ChooseRoomPage selectTheFirstResultWith3Stars() {
        driver.findElement(priceSortButton).click();
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ccol")));
        List<WebElement> allSearchResults = driver.findElements(hotelSearchResults);
            for (WebElement ele : allSearchResults) {
                if (ele.findElement(hotelStars).getText().equals("3.0 out of 5.0")) {
                    hotelName = ele.findElement(By.cssSelector("h4.hotelName.fakeLink")).getText();
                    actualHotelPrice = ele.findElement(By.cssSelector("li.actualPrice.price.fakeLink.covidUrgencyMsg")).getText();
                    hotelStarsText = ele.findElement(hotelStars).getText();
                    ele.click();
                    break;
                }
            }
            return new ChooseRoomPage(driver);
        }
    }