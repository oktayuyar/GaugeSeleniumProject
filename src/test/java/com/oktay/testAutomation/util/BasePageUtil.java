package com.oktay.testAutomation.util;

import com.misli.testAutomation.helper.ElementHelper;
import com.misli.testAutomation.helper.StoreHelper;
import com.misli.testAutomation.model.ElementInfo;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static com.oktay.testAutomation.driver.Driver.webDriver;

/*
 *  Created by oktayuyar on 2019-08-23
 */
public class BasePageUtil {

    public WebDriverWait wait = new WebDriverWait(webDriver, 20);
    final static Logger logger = Logger.getLogger(BasePageUtil.class);


    public String getTitle() {
        try {
            logger.info("getTitle method called: getting current page title ");
            return webDriver.getTitle();
        } catch (Exception ex) {
            logger.error("Can not get current page title!");
            throw ex;
        }
    }

    public WebElement findElement(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        try {
            logger.info("findElement method called:  finding " + key);
            return wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception ex) {
            logger.error(key + " element can not find! " + by.toString());
            throw ex;
        }
    }

    public List<WebElement> findElements(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);
        List<WebElement> elements;

        try {
            logger.info("findElements method called:  finding " + key);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            elements = webDriver.findElements(by);
        } catch (Exception ex) {
            logger.error(key + " elements can not find! " + by.toString());
            throw ex;
        }
        return elements;
    }

    public void scrollToElement(WebElement element) {
        logger.info("scrollToElement method called:  scrolling to: " + element);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", element);
    }

    public void clickToElement(WebElement element) {
        try {
            scrollToElement(element);
            logger.info("clickElement method called:  clicking " + element);
            element.click();
        } catch (
                Exception ex) {
            logger.error(element + " element can not clicked!");
            throw ex;
        }
    }

    public void sendKeysToElement(String key, String value) {
        try {
            WebElement element = findElement(key);
            logger.info("sendKeysToElement method called:  sending " +value +" to :"+ key);
            element.sendKeys(value);
        } catch (Exception ex) {
            logger.error("Error while send text to element.", ex);
            throw ex;
        }
    }

    public void selectMatchesFromBetList(String key, int count) {
        List<WebElement> elementList = findElements(key);

        for (int i = 0; i < count ; i++) {
            clickToElement(elementList.get(i));
        }
    }

    public boolean isSelected(String key) {
        try {
            WebElement element = findElement(key);
            logger.info("isSelected method called:  checking " + key);
            return element.isSelected();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isEnabled(String key) {
        try {
            WebElement element = findElement(key);
            logger.info("isEnabled method called:  checking " + key);
            return element.isEnabled();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isDisplayed(String key) {
        try {
            WebElement element = findElement(key);
            logger.info("isDisplayed method called:  checking " + key);
            return element.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isElementPresent(String key) {

        ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
        By by = ElementHelper.getElementInfoToBy(elementInfo);

        try {
            logger.info("isElementPresent method called:  checking " + by.toString());
            webDriver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            logger.error(by.toString() + " element can not present!");
            return false;
        }
    }

    public void waitForLoadJavaScript() {
        new WebDriverWait(webDriver, 20).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void waitByMilliSeconds(long milliseconds) {
        try {
            logger.info("waitByMilliSeconds method called:  waiting " + milliseconds/1000 +"  seconds.");
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

