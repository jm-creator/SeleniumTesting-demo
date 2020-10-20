package pages;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChooseFlightsPage {
    private final static Logger LOGGER = Logger.getLogger("ChooseFlightsPage");
    private WebDriver driver;
    private By dropDownSortedBy = By.id("sortDropdown");
    private By searchResults = By.id("flight-listing-container");
    private By eachResult = By.cssSelector("[data-test-id=offer-listing]");
    private By selectButtonResult = By.cssSelector("[data-test-id=select-button]");
    private By flightsDetailLink = By.cssSelector("[data-test-id=flight-details-link]");
    private By durationDetail = By.cssSelector("[data-test-id=duration]");
    private By durationFlight = By.cssSelector("span.duration-emphasis");
    private By firstSelectButton = By.cssSelector("button.btn-secondary.btn-action.t-select-btn");
    private By firstSecondSelectButton = By.cssSelector("#basic-economy-tray-content-1>div>div>div>button.btn-secondary.btn-action.t-select-btn");
    private By thirdSecondSelectButton = By.cssSelector("#basic-economy-tray-content-3>div>div>div>button.btn-secondary.btn-action.t-select-btn");
    private By noThanksLink = By.id("forcedChoiceNoThanks");

//-----------------------------------------------------------

    public ChooseFlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResults));
    }

    public Boolean checkForSelectButtonStream() {
        List<WebElement> allSearchResults = driver.findElements(eachResult);
        LOGGER.log(Level.INFO, "checking for select button enabled on all results");
        return  allSearchResults.stream()
                .allMatch(s -> s.findElement(selectButtonResult).isEnabled());
    }

    public Boolean checkForFlightsDetailLinkStream(){
        List<WebElement> allSearchResults = driver.findElements(eachResult);
        LOGGER.log(Level.INFO, "checking for flights details links enabled on all results");
        return allSearchResults.stream().
                allMatch(s -> s.findElement(flightsDetailLink).isEnabled());
    }

    public Boolean checkForDurationDetailStream(){
        List<WebElement> allSearchResults = driver.findElements(eachResult);
        LOGGER.log(Level.INFO, "checking for duration details enabled on all results");
        return allSearchResults.stream()
                .allMatch(s -> s.findElement(durationDetail).isEnabled());
    }

    public boolean sortByDurationSorted() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.elementToBeClickable(dropDownSortedBy));
        WebElement dropdown = driver.findElement(dropDownSortedBy);
        Select selectSortedType = new Select(dropdown);
        selectSortedType.selectByVisibleText("Duration (Shortest)");
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(durationFlight)));
        List<String> TimeValueResults = driver.findElements(durationFlight).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        String myRegex = "[^0-9]";
        String myRegex1 = "\\d\\dh\\s\\dm|\\dh\\s\\dm";
        TimeValueResults = TimeValueResults.stream()
                .map(s -> {if (s.matches(myRegex1)){
                          String s1= s.replaceAll("h","0");
                          return s1.replaceAll(myRegex,"");}
                          return s.replaceAll(myRegex,"");})
                .collect(Collectors.toList());
        LOGGER.log(Level.INFO, "List of times results sorted by duration time: "+TimeValueResults);
        List<Integer> intList = new ArrayList<>();
        intList.addAll(TimeValueResults.stream()
                .map(Integer::valueOf).collect(Collectors.toList()));
        return Ordering.natural().isOrdered(intList);
    }

    public TripDetailsPage clickSelectButtonFirsDepartureOptionAndReturn3rdOptionFlight() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.badge.badge-success.badge-notification." +
                "flights-flexible-change-badge")));
        driver.findElement(firstSelectButton).click();
      //if the test run on suite below lines must be commented...
      //  wait.until(ExpectedConditions.elementToBeClickable(firstSecondSelectButton));
      //  driver.findElement(firstSecondSelectButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.badge.badge-success.badge-notification." +
                "flights-flexible-change-badge")));
        List<WebElement> allSearchResults = driver.findElements(eachResult);
        allSearchResults.get(3).findElement(firstSelectButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdSecondSelectButton));
        driver.findElement(thirdSecondSelectButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(noThanksLink));
        driver.findElement(noThanksLink).click();
        return new TripDetailsPage(driver);
    }


    public TripDetailsPage clickSelectButtonFirsDepartureOptionAndReturn3rdOptionHotelFlightCar() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.badge.badge-success.badge-notification." +
                "flights-flexible-change-badge")));
        driver.findElement(firstSelectButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(firstSecondSelectButton));
        driver.findElement(firstSecondSelectButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.badge.badge-success.badge-notification." +
                "flights-flexible-change-badge")));
        List<WebElement> allSearchResults = driver.findElements(eachResult);
        allSearchResults.get(2).findElement(firstSelectButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(thirdSecondSelectButton));
        driver.findElement(thirdSecondSelectButton).click();
        return new TripDetailsPage(driver);
    }
}






















