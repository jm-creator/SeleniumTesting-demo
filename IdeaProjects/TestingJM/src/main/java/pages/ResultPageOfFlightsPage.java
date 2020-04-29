package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class ResultPageOfFlightsPage {

    private WebDriver driver;
    private By dropDownSortedBy = By.id("sortDropdown");


//-----------------------------------------------------------

    public ResultPageOfFlightsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPage() {
        WebDriverWait wait = new WebDriverWait(driver, 40);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("change-flight-legend")));
    }

    public Boolean checkForSelectButton() {
        int contTrue = 0;
        List<WebElement> allSearchResults = driver.findElements(By.cssSelector("[data-test-id=offer-listing]"));
        for (WebElement eachResult : allSearchResults) {
            if (eachResult.findElement(By.cssSelector("[data-test-id~=select-button]")).isEnabled())
                contTrue = contTrue + 1;
            else System.out.println("select button is not enabled on object:" + eachResult.toString());
        }
        return allSearchResults.size() == contTrue;
    }

    public Boolean checkForFlightsDetailLink() {
        int contTrue = 0;
        List<WebElement> allSearchResults = driver.findElements(By.cssSelector("[data-test-id=offer-listing]"));
        for (WebElement eachResult : allSearchResults) {
            if (eachResult.findElement(By.cssSelector("[data-test-id~=flight-details-link]")).isEnabled())
                contTrue = contTrue + 1;
            else System.out.println("Check flights detail link is not enabled on object:" + eachResult.toString());
        }
        return allSearchResults.size() == contTrue;
    }

    public Boolean checkForDurationDetail() {
        int contTrue = 0;
        List<WebElement> allSearchResults = driver.findElements(By.cssSelector("[data-test-id=offer-listing]"));
        for (WebElement eachResult : allSearchResults) {
            if (eachResult.findElement(By.cssSelector("[data-test-id~=duration]")).isEnabled())
                contTrue = contTrue + 1;
            else System.out.println("Duration detail is not enabled on object:" + eachResult.toString());
        }
        return allSearchResults.size() == contTrue;
    }


    public boolean sortByDurationSorted() {
        WebElement dropdown = driver.findElement(dropDownSortedBy);
        Select selectSortedType = new Select(dropdown);
        selectSortedType.selectByVisibleText("Duration (Shortest)");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> allSearchResults = driver.findElements(By.cssSelector("[data-test-id~=listing-main]"));
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<String> TimeValueResults = new ArrayList<>();
        for (WebElement eachResult : allSearchResults) {
            TimeValueResults.add(eachResult.findElement(By.cssSelector("span.duration-emphasis")).getText());
        }
        System.out.println(TimeValueResults);
        int index = 0;
        int cont = 0;
        String myRegex = "[^0-9]";
        String myRegex1 = "\\d\\dh\\s\\dm|\\dh\\s\\dm";
        for (String s : TimeValueResults) {
            if (s.matches(myRegex1))
                TimeValueResults.set(cont, s.replaceAll("h", "0"));
            cont++;
        }
        for (String s : TimeValueResults) {
            TimeValueResults.set(index, s.replaceAll(myRegex, ""));
            index++;
        }
        System.out.println(TimeValueResults);
        for (int i = 1; i < TimeValueResults.size()-1 ; i++) {
            if (TimeValueResults.get(i - 1).compareTo(TimeValueResults.get(i)) > 0) {
                System.out.println("estos"+TimeValueResults.get(i - 1)+"<"+TimeValueResults.get(i));
                return false;
            }
        }
        return true;
    }

    //  TimeValueResults.stream().map(Integer::parseInt).collect(Collectors.toList());
    //    return Ordering.natural().isOrdered(TimeValueResults);}
    //  return TimeValueResults.stream().sorted().collect(Collectors.toList()).equals(TimeValueResults);}


    public TripDetailsPage clickSelectButtonFirsDepartureOptionAndReturn3rdOption() {
        WebElement dropdown = driver.findElement(dropDownSortedBy);
        Select selectSortedType = new Select(dropdown);
        selectSortedType.selectByVisibleText("Duration (Shortest)");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> allSearchResults = driver.findElements(By.cssSelector("[data-test-id~=select-button]"));

        allSearchResults.get(0).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allSearchResults.get(2).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebElement> allSearchResults1 = driver.findElements(By.cssSelector("[data-test-id~=select-button]"));
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allSearchResults1.get(6).click();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        allSearchResults1.get(8).click();
        return new TripDetailsPage(driver);
    }
}






















