package com.jaemzware.amazon.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.Utilities;
import com.jaemzware.amazon.pageobjects.AmazonHomePage;
import com.jaemzware.amazon.pageobjects.AmazonProductPage;
import com.jaemzware.amazon.pageobjects.AmazonSearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AmazonSearchTest extends BaseSeleniumTest{

    @Test
    public void AmazonSearch() {
        AmazonHomePage homePage = new AmazonHomePage(driver);
        homePage = homePage.navigate();

        AmazonSearchResultsPage searchResultsPage = homePage.searchForTerm("record");
        AmazonProductPage productPage = searchResultsPage.clickFirstResult();

        Assert.assertTrue(!productPage.getProductTitle().isEmpty());
        System.out.println(productPage.getProductTitle());

        //add a screenshot to the report
        String screenshotFilename = Utilities.ScreenShot(driver);
        writer.println("<img src='"+screenshotFilename+"' />");

    }
}
