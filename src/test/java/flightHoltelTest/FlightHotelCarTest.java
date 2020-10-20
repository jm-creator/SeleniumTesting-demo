package flightHoltelTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.*;

import static org.testng.Assert.assertTrue;

public class FlightHotelCarTest extends BaseTest {


    @Test
    public void flightHotelSetPropertiesSearch(){
        SetPropertiesFlightsHotels flightHotel = homePage.clickFlightsHotelCarButton();
        flightHotel.checkForPage();
        flightHotel.completeData("LAS","1", "LAX", "13");
        flightHotel.clickSearchButton();
    }

    @Test(dependsOnMethods = "flightHotelSetPropertiesSearch")
    public void CheckOrderByPrice(){
        //flightHotelSetPropertiesSearch();
        ChoosingHotelPage choosingHotelPage = new ChoosingHotelPage(driver);
        choosingHotelPage.waitForResultPage();
        assertTrue(choosingHotelPage.CheckOrderByPrice(),"some price is not sort");
        assertTrue(choosingHotelPage.verifyChoosingHotelPage() , "failed verification ChooseHotelPage");
    }

    @Test(dependsOnMethods = "flightHotelSetPropertiesSearch")
    public void verifyWhosTravelingPage(){
        //flightHotelSetPropertiesSearch();
        ChoosingHotelPage choosingPage =new ChoosingHotelPage(driver);
        choosingPage.waitForResultPage();
        choosingPage.selectTheFirstResultWith3Stars();
        getWindowManager().switchToNewTab();
        ChooseRoomPage chooseRoomPage = new ChooseRoomPage(driver);
       assertTrue( chooseRoomPage.checkHotelNameIsTheSelected(choosingPage.getHotelName()), "The hotel name in ChooseRoomPage" +
               "is not the same that previous page");
       assertTrue(chooseRoomPage.checkHotelActualPriceIsTheSameToChoosingHotelPage(choosingPage.getActualHotelPrice()), "The actual price" +
               "in ChooseRoomPage not is the same to selected on previous page");
        assertTrue(chooseRoomPage.checkTheStarsHotelAreTheSameThatPreviousPage(choosingPage.getHotelStarsText()), "The actual page stars not the " +
               "same on previous page");
        chooseRoomPage.selectTheFirstResult();
        ChooseFlightsPage chooseFlightsPage = new ChooseFlightsPage(driver);
        chooseFlightsPage.clickSelectButtonFirsDepartureOptionAndReturn3rdOptionHotelFlightCar();
        ChooseCarPage chooseCarPage = new ChooseCarPage(driver);
        chooseCarPage.selectCar();
        WhosTravelingPage whosTravelingPage =new WhosTravelingPage(driver);
        whosTravelingPage.waitForPage();
        assertTrue(whosTravelingPage.verifyPageIsOpenedByFiveValidations(), "failed to verification Wh'osTravelingPage");
    }
}
