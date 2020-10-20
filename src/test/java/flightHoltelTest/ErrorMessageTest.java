package flightHoltelTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.SetPropertiesFlightsHotels;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ErrorMessageTest extends BaseTest {
    @Test
    public void flightHotelCheckErrorMessage(){
        SetPropertiesFlightsHotels flightHotel = homePage.clickFlightsHotelCarButton();
        flightHotel.checkForPage();
        flightHotel.completeData("LAS","1", "LAX", "13");
        flightHotel.setHotelProperties("1","13");
        flightHotel.clickSearchButton();
        assertEquals(flightHotel.getErrorMessage(),"Your partial check-in and check-out dates must fall " +
                "within your arrival and departure dates. Please review your dates.","The message displayed is not the expected");
    }

}
