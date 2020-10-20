package CruisesTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.ChooseCruisePage;
import pages.SetPropertiesCruises;

import static org.testng.Assert.assertEquals;

public class CruisesTest extends BaseTest {
    @Test
    public void cruiserSetProperties(){
        SetPropertiesCruises cruises = homePage.clickCruisesButton();
        cruises.setCruiserProperties();
        cruises.adultsQuantity("1");
        cruises.clickSearchButton();
    }

    @Test(dependsOnMethods = "cruiserSetProperties")
    public void resultTest(){
       // cruiserSetProperties();
        ChooseCruisePage cruiser = new ChooseCruisePage(driver);
        cruiser.clickOKButton();
        cruiser.verifyPriceIsInAllResults();
        assertEquals(cruiser.cruiseLength10to14dais(),"10-14 Nights","The message on page is incorrect");
        cruiser.selectTheCruiserWithMoreDiscountsOrMostCheap();
    }

}
