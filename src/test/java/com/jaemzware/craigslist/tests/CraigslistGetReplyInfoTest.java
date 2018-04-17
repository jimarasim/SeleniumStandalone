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
    public void GetReplyEmail() throws Exception{
        driver.get("https://seattle.craigslist.org");
        
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistResultsPage resultsPage = searchPage.ClickForSaleLink();
        CraigslistPostPage postPage = resultsPage.ClickFirstResult();
        
        for(int i=0;i<10;i++) {
            String email = postPage.GetReplyEmail();

            System.out.println(email);
            
            postPage.ClickNextLink();
        }
    }
}
