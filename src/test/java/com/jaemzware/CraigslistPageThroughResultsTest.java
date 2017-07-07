package com.jaemzware;

import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by jameskarasim on 7/5/17.
 */
public class CraigslistPageThroughResultsTest extends BaseSeleniumTest {
    @Test
    public void CraigslistPageThroughResults(){
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("skateboard");
        List<String> allResultUrls = resultsPage.GetResultUrls();

        for(String s:allResultUrls){
            System.out.println(s);
        }

    }
}
