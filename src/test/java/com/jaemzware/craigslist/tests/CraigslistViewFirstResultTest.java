package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistPostPage;
import com.jaemzware.craigslist.pageobjects.CraigslistResultsPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.testng.annotations.Test;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistViewFirstResultTest extends BaseSeleniumTest {
    @Test(groups={"craigslisttest","craigslistviewfirstresulttest"})
    public void ViewFirstResultTest() throws Exception{
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("2011 honda civic");
        CraigslistPostPage postPage = resultsPage.ClickFirstResult();
        postPage.PrintResultDetails();
    }
}
