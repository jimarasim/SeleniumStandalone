package com.jaemzware;

import org.testng.annotations.Test;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistViewFirstResultTest extends BaseSeleniumTest{
    @Test
    public void ViewFirstResultTest() throws Exception{
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("2011 honda civic");
        CraigslistPostPage postPage = resultsPage.ClickFirstResult();
        postPage.PrintResultDetails();
    }
}