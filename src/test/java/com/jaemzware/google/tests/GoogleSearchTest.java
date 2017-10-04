package com.jaemzware.google.tests;

import com.jaemzware.BasePageObject;
import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.google.pageobjects.GoogleResultsPage;
import com.jaemzware.google.pageobjects.GoogleSearchPage;
import org.testng.annotations.Test;

/**
 * Unit test for simple App.
 */
public class GoogleSearchTest extends BaseSeleniumTest
{
    //THIS TEST DEMONSTRATES USING THE PAGE OBJECT MODEL TO PERFORM A GOOGLE SEARCH
    @Test(groups={"googletest"})
    public void SimpleSeleniumTestForGoogle() throws Exception{
        driver.get("https://google.com");

        GoogleSearchPage searchPage = new GoogleSearchPage(driver);

        GoogleResultsPage resultsPage = searchPage.PerformSearch("computer systems");

        resultsPage.PrintOutResults();

        BasePageObject firstPage = resultsPage.ClickFirstResult();

        firstPage.PrintElements();
    }

}
