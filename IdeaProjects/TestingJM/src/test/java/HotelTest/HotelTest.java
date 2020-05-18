package HotelTest;

import BaseTest.BaseTest;
import org.testng.annotations.Test;
import pages.SetPropertiesHotels;
import pages.SearchHotelPage;

import static org.testng.Assert.assertTrue;


public class HotelTest extends BaseTest {

    @Test
    public void setSearchHotelsProperties(){
        SetPropertiesHotels hotelsPage = homePage.clickHotelButton();
        hotelsPage.completeSearch("Montevideo", "1","20");
        hotelsPage.clickSearchButton();
    }

    @Test
    public void testForDiscountMessage(){
        SearchHotelPage searchHotelPage = new SearchHotelPage(driver);
        setSearchHotelsProperties();
        assertTrue(searchHotelPage.checkForDiscountMessage(),"the message is not available");
    }
}
