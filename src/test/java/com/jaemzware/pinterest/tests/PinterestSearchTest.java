package com.jaemzware.pinterest.tests;

import static com.jaemzware.Utilities.ScreenShot;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.pinterest.pageobjects.PinterestHomePage;
import com.jaemzware.pinterest.pageobjects.PinterestLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PinterestSearchTest extends BaseSeleniumTest {
    
    //THIS TEST ENTERS A SEARCH TERM AND VERIFIES THE INTEGRITY OF THE RESULTANT PAGE
    @Test(groups={"pinteresttest", "pinterestsearchtest"})
    public void searchTest() throws Exception{
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //search for term
        home.searchForTerm("memes");

        //save a screenshot to the htm report
        String screenshotFilename = ScreenShot(driver);
        writer.write("<br /><img src='"+screenshotFilename+"' alt='after searching memes' /><br />");

        Assert.assertTrue(home.resultsListCount() > 2);
        Assert.assertTrue(home.IsHomeButtonEnabled());
        Assert.assertTrue(home.IsPButtonEnabled());
        Assert.assertTrue(home.IsExploreButtonEnabled());
        Assert.assertTrue(home.IsProfileButtonEnabled());
        Assert.assertTrue(home.IsAddPinButtonEnabled());
        Assert.assertTrue(home.IsMoreButtonEnabled());
        Assert.assertTrue(home.IsSearchImprovementsWrapperEnabled());
    }
}
