package com.jaemzware.amazon.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.Utilities;
import com.jaemzware.amazon.pageobjects.AmazonHomePage;
import com.jaemzware.amazon.pageobjects.AmazonProductPage;
import com.jaemzware.amazon.pageobjects.AmazonSearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonAddToCartTest extends BaseSeleniumTest{

    @Test
    public void AmazonSearch() throws InterruptedException {
        AmazonHomePage homePage = new AmazonHomePage(driver);
        homePage = homePage.navigate();

        AmazonSearchResultsPage searchResultsPage = homePage.searchForTerm("macbook pro");
        AmazonProductPage productPage = searchResultsPage.clickFirstResult();

        Assert.assertTrue(!productPage.getProductTitle().isEmpty());
        System.out.println(productPage.getProductTitle());

        //add a screenshot to the report
        String screenshotFilename = Utilities.ScreenShot(driver);
        writer.println("<img src='"+screenshotFilename+"' />");

        //select quantity
        productPage.selectQuantity("2");
        productPage.clickAddToCartButton();
        productPage.clickContinueButton();

        Thread.sleep(5000);


    }
}
