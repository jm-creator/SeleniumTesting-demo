package utils;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class WindowManager {
    private WebDriver driver;
    private WebDriver.Navigation navigate;

    public WindowManager(WebDriver driver){
        this.driver = driver;
        navigate = driver.navigate();
    }
    public void goBack(){
        navigate.back();
    }

    public void goForward(){
        navigate.forward();
    }

    public void refreshPage(){
        navigate.refresh();
    }
    public void switchToNewTab(){
        ArrayList<String> tabs2 = new ArrayList<> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
    }
}
