package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistResultsPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.testng.annotations.Test;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistSearchTest extends BaseSeleniumTest {
    @Test(groups={"craigslisttest","craigslistsearchtest"})
    public void SearchAndPrintResults(){
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("skateboard");
        resultsPage.PrintOutResults();
    }
}
