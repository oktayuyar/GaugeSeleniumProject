package com.oktay.testAutomation.steps;

import com.oktay.testAutomation.util.BasePageUtil;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/*
 *  Created by oktayuyar on 2019-08-23
 */
public class StepImplementation extends BasePageUtil {

    @Step("<key> elementine tıkla")
    public void clickElement(String key) {
        WebElement element = findElement(key);
        clickToElement(element);
    }

    @Step("<key> alanına <text> yaz")
    public void sendKeys(String key, String text) {
        sendKeysToElement(key,text);
    }

    @Step("<key> elementinin göründüğünü kontrol et")
    public void elementIsHere(String key) {
        Assert.assertTrue(key + " elementi goruntulenemedi.", isDisplayed(key));
    }

    @Step("<key> alanında <text> yazdığı kontrol edilir")
    public void checkUsername(String key, String text) {
        Assert.assertTrue(text + " kullanıcısı uygulamaya giriş yapamadı!.",
                findElement(key).getText().equals(text));
    }

    @Step("Siteye giriş yapıldığı kontrol edilir")
    public void checkSuccessfulLogin() {
        Assert.assertTrue("Amazon.com.tr sitesine başarılı şekilde giriş ypılamadı!",
                getTitle().contains("Amazon.com.tr: Elektronik,"));
    }

    @Step("Siteye giriş yapamadığı kontrol edilir")
    public void checkUnSuccessfulLogin() {
        Assert.assertTrue("Amazon.com.tr sitesine başarılı şekilde giriş ypılamadı!",
                getTitle().contains("Amazon Giriş Yap"));
    }

}
