package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistPostPage;
import com.jaemzware.craigslist.pageobjects.CraigslistResultsPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.testng.annotations.Test;

import java.util.List;

public class CraigslistSearchUrlReportTest extends BaseSeleniumTest {
    @Test(groups={"craigslisttest","craigslistsearchurlreporttest"})
    public void CraigslistSearchUrlReport() throws Exception{
        //check if a search term was specified with -DaString
        String searchUrl = System.getProperty("aString");
        if(searchUrl==null || searchUrl.isEmpty()) {
            throw new Exception("PLEASE SPECIFY A SEARCH URL WITH -DaString");
        }

        driver.get(searchUrl);
        CraigslistResultsPage resultsPage = new CraigslistResultsPage(driver);
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
