package com.jaemzware;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class BasePageObject {
    protected WebDriver driver;

    public BasePageObject(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver,this);
    }

    public BasePageObject VerifyCurrentPageUrl(String url){
        Assert.assertEquals(url,driver.getCurrentUrl());

        return this;
    }
}
