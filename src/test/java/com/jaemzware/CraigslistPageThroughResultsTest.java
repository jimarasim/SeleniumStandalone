package com.jaemzware;

import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by jameskarasim on 7/5/17.
 */
public class CraigslistPageThroughResultsTest extends BaseSeleniumTest {


    @Test
    public void CraigslistPageThroughResults() throws Exception{
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch("skate");
        List<String> allResultUrls = resultsPage.GetResultUrls();

        //check if max results was specified with -DaNumber
        int maxResults = 0;
        if(System.getProperty("aNumber")!=null){
            maxResults = Integer.parseInt(System.getProperty("aNumber"));
        }

        CraigslistPostPage postPage = resultsPage.ClickFirstResult();

        //do something with the results
        for(int i=0;i<allResultUrls.size();i++){

            //break if max results was specified
            if(maxResults > 0 && i > maxResults){
                break;
            }

            postPage.GetAndWriteDetails(allResultUrls.get(i), writer);


        }
    }
}
