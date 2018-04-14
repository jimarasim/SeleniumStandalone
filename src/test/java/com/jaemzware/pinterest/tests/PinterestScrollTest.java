package com.jaemzware.pinterest.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.pinterest.pageobjects.PinterestHomePage;
import com.jaemzware.pinterest.pageobjects.PinterestLoginPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PinterestScrollTest extends BaseSeleniumTest{

    //THIS TEST SCROLLS DOWN THE PIN FEED, VERIFYING INTEGRITY OF ALL ELEMENTS ALONG THE WAY
    @Test(groups={"pinteresttest","pinterestscrolltest"})
     public void scrollTest() throws Exception{
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //scroll the page slowly, and ensure the static elements remain on the page
        Object innerHeight = ((JavascriptExecutor) driver).executeScript("return window.innerHeight");

        for(int i=0;i<10;i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + innerHeight.toString() + ")");

            Thread.sleep(3000);

            Assert.assertTrue(home.resultsListCount() > 2);
            Assert.assertTrue(home.IsHomeButtonEnabled());
            Assert.assertTrue(home.IsPButtonEnabled());
            Assert.assertTrue(home.IsExploreButtonEnabled());
            Assert.assertTrue(home.IsProfileButtonEnabled());
            Assert.assertTrue(home.IsAddPinButtonEnabled());
            Assert.assertTrue(home.IsMoreButtonEnabled());
        }

    }
}
