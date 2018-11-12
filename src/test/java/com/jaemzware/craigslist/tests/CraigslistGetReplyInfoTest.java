/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistPostPage;
import com.jaemzware.craigslist.pageobjects.CraigslistResultsPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author jameskarasim
 */
public class CraigslistGetReplyInfoTest extends BaseSeleniumTest {
    
    //GETS EMAIL ADDRESSES FROM 10 DIFFERENT POSTINGS
    @Test(groups={"craigslisttest","craigslistgetreplyinfotest"})
    public void GetReplyInfo() throws Exception{
        driver.get("https://seattle.craigslist.org");
        
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.ClickForSaleLink();
        CraigslistPostPage postPage = resultsPage.ClickFirstResult();

        int maxResults = 10;
        if(System.getProperty("aNumber")!=null && !System.getProperty("aNumber").isEmpty()){
            maxResults = Integer.parseInt(System.getProperty("aNumber"));
        }

        for(int i=0;i<maxResults;i++) {
            String replyInfo = postPage.GetReplyInfo();
            String src = postPage.GetMainImageSrc();

            System.out.println(replyInfo);
            writer.println(replyInfo + "<br />");
            writer.println("<img src='"+src+"' /><br />");
            
            postPage.ClickNextLink();
        }
    }
}
