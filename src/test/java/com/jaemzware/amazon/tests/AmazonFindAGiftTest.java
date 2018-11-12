package com.jaemzware.amazon.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.Utilities;
import com.jaemzware.amazon.pageobjects.AmazonGiftResultsPage;
import com.jaemzware.amazon.pageobjects.AmazonHomePage;
import com.jaemzware.amazon.pageobjects.AmazonProductPage;
import org.testng.Assert;
import org.testng.annotations.*;

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
        AmazonHomePage homePage = new AmazonHomePage(driver);
        homePage = homePage.navigate();
        AmazonGiftResultsPage giftsPage = homePage.clickFindGiftMenuItem();
        AmazonProductPage productPage = giftsPage.clickFirstGift();

        Assert.assertTrue(!productPage.getProductTitle().isEmpty());

        System.out.println(productPage.getProductTitle());

        String screenshotFilename = Utilities.ScreenShot(driver);
        writer.println("<img src='"+screenshotFilename+"' />");
    }

}
