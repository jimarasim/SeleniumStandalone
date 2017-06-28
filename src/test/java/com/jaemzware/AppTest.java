package com.jaemzware;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseSeleniumTest
{
    //THIS TEST DEMONSTRATES USING THE PAGE OBJECT MODEL TO PERFORM A GOOGLE SEARCH
    @Test
    public void SimpleSeleniumTestForGoogle() throws Exception{
        driver.get("https://google.com");

        GoogleSearchPage searchPage = new GoogleSearchPage(driver);

        GoogleResultsPage resultsPage = searchPage.PerformSearch("computer systems");

        resultsPage.PrintOutResults();

        resultsPage.ClickFirstResult();
    }

}
