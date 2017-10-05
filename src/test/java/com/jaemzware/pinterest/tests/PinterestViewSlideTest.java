package com.jaemzware.pinterest.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.pinterest.pageobjects.PinterestHomePage;
import com.jaemzware.pinterest.pageobjects.PinterestLoginPage;
import com.jaemzware.pinterest.pageobjects.PinterestViewerPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PinterestViewSlideTest extends BaseSeleniumTest {

    @Test(groups={"pinteresttest","pinterestviewslidetest"})
    public void viewSlideTest(){
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //go to first pin then slide through 5 pins, asserting page elements
        PinterestViewerPage viewerPage = home.clickFirstResult();

        for(int i=0;i<5;i++){
            viewerPage.clickNextPin();
            Assert.assertTrue(viewerPage.IsCloseButtonEnabled());
            Assert.assertTrue(viewerPage.IsSaveButtonEnabled());
        }
    }
}
