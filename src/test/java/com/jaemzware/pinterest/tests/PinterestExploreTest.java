package com.jaemzware.pinterest.tests;

import static com.jaemzware.Utilities.ScreenShot;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.pinterest.pageobjects.PinterestHomePage;
import com.jaemzware.pinterest.pageobjects.PinterestLoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PinterestExploreTest extends BaseSeleniumTest {

    //THIS TEST VERIFIES THE INTEGRITY OF THE EXPLORE PAGE
    @Test(groups={"pinteresttest","pinterestexploretest"})
    public void exploreTest() throws Exception{
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //click explore button and verify articles are present
        home.clickExploreButton();

        //save off a screenshot and embed it in the htm report
        String screenshotFilename = ScreenShot(driver);
        writer.write("<br /><img src='"+screenshotFilename+"' alt='after clicking explore button' /><br />");

        Assert.assertTrue(home.IsExploreHeadingEnabled());
        Assert.assertTrue(home.articleListCount() > 2);
        Assert.assertTrue(home.resultsListCount() > 2);
    }
}
