package com.jaemzware.craigslist.tests;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.craigslist.pageobjects.CraigslistClassifiedWizardPage;
import com.jaemzware.craigslist.pageobjects.CraigslistSearchPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CraigslistPostAdTest extends BaseSeleniumTest {


    @Test(groups={"craigslisttest","craigslistpostadtest"})
    public void PostAdTest() throws Exception{
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistClassifiedWizardPage wizardPage = searchPage.PostToClassified();
        wizardPage.pickRandomOptionAndContinue();

        Assert.assertTrue(wizardPage.IsElementEnabled(driver.findElement(By.xpath("//button[contains(text(),'continue')]"))));
    }
}
