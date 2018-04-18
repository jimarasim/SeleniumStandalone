package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistPostPage;
import com.jaemzware.craigslist.pageobjects.CraigslistResultsPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by jameskarasim on 7/5/17.
 * 
 *  mvn -Dgroups=craigslistpagethroughresultstest -DaString="Pink Floyd" -DaNumber=10 test
 */
public class CraigslistPageThroughResultsTest extends BaseSeleniumTest {


    @Test(groups={"craigslisttest","craigslistpagethroughresultstest"})
    public void CraigslistPageThroughResults() throws Exception{
        //check if a search term was specified with -DaString
        String searchTerm = System.getProperty("aString");
        if(searchTerm==null || searchTerm.isEmpty()) {
            searchTerm = "skateboard";
        }
              
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.PerformSearch(searchTerm);
        List<String> allResultUrls = resultsPage.GetResultUrls();

        //check if max results was specified with -DaNumber
        int maxResults = 0;
        if(System.getProperty("aNumber")!=null && !System.getProperty("aNumber").isEmpty()){
            maxResults = Integer.parseInt(System.getProperty("aNumber"));
        }

        CraigslistPostPage postPage = resultsPage.ClickFirstResult();

        //do something with the results
        for(int i=0;i<allResultUrls.size();i++){

            //break if max results was specified
            if(maxResults > 0 && i >= maxResults){
                break;
            }

            postPage.GetAndWriteDetails(allResultUrls.get(i), writer);


        }
    }
}
