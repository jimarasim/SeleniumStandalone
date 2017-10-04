package com.jaemzware;

import org.junit.Assert;
import org.testng.annotations.Test;

public class PinterestPinTest extends BaseSeleniumTest{
    @Test(groups={"pinteresttest", "pinterestpintest"})
    public void pinTest() throws Exception{
        PinterestLoginPage login = new PinterestLoginPage(driver);
        PinterestHomePage home = new PinterestHomePage(driver);

        //go to main site url and login if not already (default)
        driver.get(home.URL);
        login.LoginIfNot();

        //pick a random result and pin it to the Test board
        PinterestViewerPage viewer = home.pickRandomResult();

        viewer.pinToTestBoard();

        //get the alt of the image to compare it to the latest pinned item on the profile page
        String pinnedImageSrc = viewer.getResultImageSrc();

        //verify the result was pinned to the test board
        home = viewer.closeViewer();

        PinterestProfilePage profilePage = home.clickProfileButton();

        profilePage.clickPinsButton();

        viewer = profilePage.clickFirstResult();

        String viewerImageSrc = viewer.getResultImageSrc();

        System.out.println(viewerImageSrc + " " + pinnedImageSrc);

        Assert.assertTrue(viewerImageSrc.equals(pinnedImageSrc));
    }
}