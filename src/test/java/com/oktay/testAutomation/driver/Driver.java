package com.oktay.testAutomation.driver;

import com.thoughtworks.gauge.AfterSuite;
import com.thoughtworks.gauge.BeforeSuite;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/*
 *  Created by oktayuyar on 2019-08-23
 */
public class Driver {

    // Holds the WebDriver instance
    public static WebDriver webDriver;
    String APP_URL = "https://www.amazon.com.tr";

    // Initialize a webDriver instance of required browser
    // Since this does not have a significance in the application's business domain, the BeforeSuite hook is used to instantiate the webDriver
    @BeforeSuite
    public void initializeDriver(){
        webDriver = DriverFactory.getDriver();
        webDriver.manage().window().fullscreen();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.get(APP_URL);
    }

    // Close the webDriver instance
    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }

}
