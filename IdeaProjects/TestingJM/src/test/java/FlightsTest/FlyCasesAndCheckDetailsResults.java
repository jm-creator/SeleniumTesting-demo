package FlightsTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.Flights;
import pages.ResultPageOfFlightsPage;
import pages.TripDetailsPage;

import static org.testng.Assert.assertTrue;

public class FlyCasesAndCheckDetailsResults extends BaseTest {

    private void setFlightSearchProperties() {
        Flights flyOption = homePage.clickFromFlights();
        flyOption.setFlightOrigin("LAS");
        flyOption.setFlightDestination("LAX");
        // another method, coming soon...
        flyOption.setbackoutday("11/01/2020");
        flyOption.setGetoutday("10/01/2020");
        flyOption.adultsCuantity("1");
        flyOption.searchButtonClick().waitForPage();
    }

    @Test
    public void checks_for_selectButton_FlightsDetailLink_DurationDetail_in_AllResults() {
        setFlightSearchProperties();
        //after the method was here
        ResultPageOfFlightsPage resultPage = new ResultPageOfFlightsPage(driver);
        resultPage.waitForPage();
        assertTrue(resultPage.checkForSelectButton(), "The select button is not enable on some result is not enable on some options");
        assertTrue(resultPage.checkForFlightsDetailLink(), "The flight detail link is not enable on some results");
        assertTrue(resultPage.checkForDurationDetail(), "The duration detail is not enable on some result ");
    }

    @Test
    public void check_for_resultOrder_duration_shorter() {
        setFlightSearchProperties();
        ResultPageOfFlightsPage resultPage = new ResultPageOfFlightsPage(driver);
        resultPage.sortByDurationSorted();
        assertTrue(resultPage.sortByDurationSorted(), "The results are not order by Duration");
    }

    @Test
    public void selectOptions() {
        setFlightSearchProperties();
        ResultPageOfFlightsPage resultPage = new ResultPageOfFlightsPage(driver);
        resultPage.clickSelectButtonFirsDepartureOptionAndReturn3rdOption();
        getWindowManager().switchToNewTab();
        TripDetailsPage tripDetailsPage = new TripDetailsPage(driver);
        assertTrue(tripDetailsPage.checkForDepartureAndReturnInfo(),"check results are not enabled");
        assertTrue(tripDetailsPage.checkForTotalMessage(),"total message is not displayed");
        assertTrue(tripDetailsPage.checkPriceGuaranteeText(),"the guarantee text is not displayed");
        tripDetailsPage.clickContinueBooking();
    }



}

