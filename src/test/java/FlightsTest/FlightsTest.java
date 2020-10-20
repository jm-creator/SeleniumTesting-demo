package FlightsTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.SetPropertiesFlights;
import pages.ChooseFlightsPage;
import pages.TripDetailsPage;

import static org.testng.Assert.assertTrue;

public class FlightsTest extends BaseTest {
    @Test
    public void setFlightSearchProperties() {
        SetPropertiesFlights flyOption = homePage.clickFromFlights();
        flyOption.completeFlyingData("LAS", "1", "LAX", "20");
        flyOption.adultsQuantity("1");
        flyOption.searchButtonClick().waitForPage();
    }

    @Test(dependsOnMethods = "setFlightSearchProperties")
    public void check_for_resultOrder_duration_shorter() {
        //   setFlightSearchProperties();
        ChooseFlightsPage resultPage = new ChooseFlightsPage(driver);
        assertTrue(resultPage.sortByDurationSorted(), "The results are not order by Duration");
    }


    @Test(dependsOnMethods = "setFlightSearchProperties")
    public void checks_for_selectButton_FlightsDetailLink_DurationDetail_in_AllResults() {
     //   setFlightSearchProperties();
        ChooseFlightsPage resultPage = new ChooseFlightsPage(driver);
        resultPage.waitForPage();
        assertTrue(resultPage.checkForSelectButtonStream(), "The select button is not enable on some result is not enable on some options");
        assertTrue(resultPage.checkForFlightsDetailLinkStream(), "The flight detail link is not enable on some results");
        assertTrue(resultPage.checkForDurationDetailStream(), "The duration detail is not enable on some result ");
    }


    @Test(dependsOnMethods = "setFlightSearchProperties")
    public void selectOptions()  {
     //   setFlightSearchProperties();
        ChooseFlightsPage resultPage = new ChooseFlightsPage(driver);
        resultPage.clickSelectButtonFirsDepartureOptionAndReturn3rdOptionFlight();
        getWindowManager().switchToNewTab();
        TripDetailsPage tripDetailsPage = new TripDetailsPage(driver);
        assertTrue(tripDetailsPage.checkForDepartureAndReturnInfo(), "check results are not enabled");
        assertTrue(tripDetailsPage.checkForTotalMessage(), "total message is not displayed");
        assertTrue(tripDetailsPage.checkPriceGuaranteeText(), "the guarantee text is not displayed");
        tripDetailsPage.clickContinueBooking();
    }

}

