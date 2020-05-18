package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ChooseCruisePage {
    private int cruiserPrice;
    private String cruiserName;
    private final static Logger LOGGER = Logger.getLogger("ChooseCruisePage");
    private WebDriver driver;
    private By okButton = By.cssSelector("button.btn-secondary.btn-action");
    private By price = By.cssSelector("[class=card-price]");
    private By discount = By.cssSelector("span.strikeout-price-card");

    public ChooseCruisePage(WebDriver driver){
        this.driver = driver;
    }

    public void clickOKButton(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(okButton));
        driver.findElement(okButton).click();
    }

    public String cruiseLength10to14dais(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.modal-body")));
        driver.findElement((By.id("ember498"))).click();
        return driver.findElement(By.cssSelector("label#length-10-14-ember498-label>span.inline-label")).getText();
    }

    public boolean verifyPriceIsInAllResults() {
        List<WebElement> allResults = driver.findElements(By.cssSelector("div.flex-content"));
        List<String> discounts = driver.findElements(discount).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> prices = driver.findElements(price).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        if (discounts.isEmpty()) {
            LOGGER.log(Level.INFO, "- the result page don't have any discounts " +
                    " - results = " + allResults.size() + " - results with price = " + prices.size());
        } else {
            LOGGER.log(Level.INFO, "results = " + allResults.size() + " - results with price = " + prices.size() + " - results " +
                    " with discounts = " + discounts.size());
        }
        return (allResults.size() == prices.size());

    }

    public void selectTheCruiserWithMoreDiscountsOrMostCheap() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        List<String> discounts = driver.findElements(discount).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        List<String> prices = driver.findElements(price).stream()
                .map(WebElement::getText).collect(Collectors.toList());
        String myRegex = "[^0-9]";
        LOGGER.log(Level.INFO, "List of prices Before Regex"+prices);
        if (discounts.isEmpty()) {
            LOGGER.log(Level.INFO, "current page don't have discounts in any cruiser displayed, the most cheap cruiser is selected");
            prices = prices.stream().map(s -> s.replaceAll(myRegex, "")).collect(Collectors.toList());
            LOGGER.log(Level.INFO, "List of prices after Regex"+prices);
            List<Integer> intListOfPrices = new ArrayList<>();
            intListOfPrices.addAll(prices.stream()
                    .map(Integer::valueOf).collect(Collectors.toList()));
            int mostCheapCruiser = Integer.MAX_VALUE;
            for (int i = 0; i < intListOfPrices.size(); i++) {
                if (mostCheapCruiser > intListOfPrices.get(i)) {
                    mostCheapCruiser = intListOfPrices.get(i);
                }
            }
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.flex-content")));
            List<WebElement> allResults = driver.findElements(By.cssSelector("div.flex-content"));
            LOGGER.log(Level.INFO, "The most cheap cruiser price on array is : " + mostCheapCruiser);
            cruiserPrice = mostCheapCruiser;
            for(WebElement result:allResults){
               int prices1 = Integer.valueOf(result.findElement(By.cssSelector("span.card-price")).getText().replaceAll(myRegex,""));
               if(prices1 == mostCheapCruiser){
                   result.findElement(By.cssSelector("a.btn.btn-secondary.btn-action.select-sailing-button")).click();
                   break;
               }
            }
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.flex-content")));
            List<WebElement> allResults = driver.findElements(By.cssSelector("div.flex-content"));
            List<Integer> discountList = new ArrayList<>();
            for (int i = 0; i > allResults.size(); i++) {
                if (allResults.get(i).findElement(discount).isEnabled()) {
                    discountList.add(Integer.parseInt(allResults.get(i).findElement(discount).getText().replaceAll(myRegex, "")) -
                            Integer.parseInt(allResults.get(i).findElement(price).getText().replaceAll(myRegex, "")));
                }
                    int bestDiscount = Integer.MAX_VALUE;
                    for (i = 0; i < discountList.size(); i++) {
                        if (bestDiscount < discountList.get(i)) {
                            bestDiscount = discountList.get(i);
                        }
                        LOGGER.log(Level.INFO, "The best discount on page is : " + bestDiscount);
                        for(WebElement result:allResults){
                            int discount1 = Integer.valueOf(result.findElement(By.cssSelector("span.strikeout-price-card")).getText().replaceAll(myRegex,""));
                            if(discount1 == bestDiscount){
                                result.findElement(By.cssSelector("a.btn.btn-secondary.btn-action.select-sailing-button")).click();
                                break;
                            }
                        }

                    }
            }
        }
    }
}


