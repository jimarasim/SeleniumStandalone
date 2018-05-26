package com.jaemzware.amazon.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.amazon.pageobjects.AmazonGiftsPage;
import com.jaemzware.amazon.pageobjects.AmazonHomePage;
import com.jaemzware.amazon.pageobjects.AmazonProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AmazonFindAGiftTest extends BaseSeleniumTest
{
    private final String giftTitleId = "title";

    //THIS TEST DEMONSTRATES THE FOLLOWING SELENIUM FUNCTIONALITY:
    //. LOADING A WEB PAGE
    //. FINDING AN ELEMENT BY ITS ID
    //. HOVERING OVER AN ELEMENT
    //. WAITING FOR A LINK WITH SPECIFIC TEXT TO BE VISIBLE
    //. WAITING FOR AN ELEMENT WITH A SPECIFIC XPATH TO BE VISIBLE
    //. WAITING FOR AN ELEMENT WITH A SPECIFIC ID TO BE VISIBLE
    @Test
    public void FindAGift() throws Exception{
        //load the amazon site
        driver.get("https://amazon.com");

        AmazonHomePage homePage = new AmazonHomePage(driver);
        AmazonGiftsPage giftsPage = homePage.clickFindGiftMenuItem();
        AmazonProductPage productPage = giftsPage.clickFirstGift();

        Assert.assertTrue(!productPage.getProductTitle().isEmpty());

        System.out.println(productPage.getProductTitle());
    }

}
