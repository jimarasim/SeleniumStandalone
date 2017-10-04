package com.jaemzware;

import org.junit.Assert;
import org.testng.annotations.Test;

public class PinterestSearchTest extends BaseSeleniumTest{
    @Test(groups={"pinteresttest"})
    public void searchTest() throws Exception{
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //search for term
        home.SearchForTerm("memes");

        Assert.assertTrue(home.ResultsListCount() > 4);
    }
}
