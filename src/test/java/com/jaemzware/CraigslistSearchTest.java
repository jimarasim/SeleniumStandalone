package com.jaemzware;

import org.junit.Test;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistSearchTest extends BaseSeleniumTest {
    @Test
    public void SearchAndPrintResults(){
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("skateboard");
        resultsPage.PrintOutResults();
    }
}
