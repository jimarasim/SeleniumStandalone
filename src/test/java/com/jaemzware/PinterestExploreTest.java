package com.jaemzware;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PinterestExploreTest extends BaseSeleniumTest {

    @Test(groups={"pinteresttest","pinterestexploretest"})
    public void exploreTest(){
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //click explore button and verify articles are present
        home.clickExploreButton();

        Assert.assertTrue(home.IsExploreHeadingEnabled());
        Assert.assertTrue(home.articleListCount() > 2);
        Assert.assertTrue(home.resultsListCount() > 2);
    }
}
