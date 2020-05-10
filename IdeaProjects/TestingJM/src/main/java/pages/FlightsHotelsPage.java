package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FlightsHotelsPage {

    private WebDriver driver;
    private By flyingForm = By.id("package-origin-hp-package");
    private By flyingTo = By.id("package-destination-hp-package");
    private By departing = By.id("package-departing-hp-package");
    private By selectFlightsHotelButton = By.id("tab-package-tab-hp");
    private By returning = By.id("package-returning-hp-package");
    private By adultsRow = By.id("package-1-adults-hp-package");
    private By datePickerNextMonthButton = By.xpath("//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/button[2]");
    private By DatesDepartingDatePicker = By.xpath("//*[@id=\"package-departing-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By DatesReturningDatePicker = By.xpath("//*[@id=\"package-returning-wrapper-hp-package\"]/div/div/div[2]/table/tbody/tr/td/button");
    private By searchButton = By.id("search-button-hp-package");

    //----------------------------------------------------------------------------------------------------

    public FlightsHotelsPage(WebDriver driver) {
        this.driver = driver;
    }


    public boolean checkForPage() {
        return driver.findElement(selectFlightsHotelButton).isSelected();
    }

    public void completeData(String flyingFromCity, String departingDay, String flyingToCity, String returningDay) {
        driver.findElement(flyingForm).sendKeys(flyingFromCity);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(flyingForm).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(flyingForm).sendKeys(Keys.ENTER);
        driver.findElement(flyingTo).sendKeys(flyingToCity);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(flyingTo).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(flyingTo).sendKeys(Keys.ENTER);
        driver.findElement(departing).click();
        int contMonths = 0;
        while (true) {
            contMonths++;

            if (contMonths == 3) {
                break;
            } else {
                driver.findElement(datePickerNextMonthButton).click();
            }
        }
        List<WebElement> allDatesDepartingDatePicker = driver.findElements(DatesDepartingDatePicker);
        for (WebElement ele : allDatesDepartingDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(departingDay)) {
                ele.click();
                break;
            }
        }
        driver.findElement(returning).click();
        List<WebElement> allDatesReturningDatePicker = driver.findElements(DatesReturningDatePicker);
        for (WebElement ele : allDatesReturningDatePicker) {
            String date_text = ele.getText();
            String[] date = date_text.split("\n");
            if (date[1].equals(returningDay)) {
                ele.click();
                break;
            }
        }
    }

    public ChoosingHotelPage clickSearchButton() {
        driver.findElement(adultsRow);
        WebElement dropdown = driver.findElement(adultsRow);
        Select selectAdults = new Select(dropdown);
        selectAdults.selectByVisibleText("1");
        driver.findElement(searchButton).click();
        return new ChoosingHotelPage(driver);
    }


}


