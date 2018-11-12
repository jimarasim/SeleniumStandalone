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
        wizardPage = wizardPage.pickSeattle();
        wizardPage = wizardPage.pickForSaleByOwnerAndContinue();
        wizardPage = wizardPage.pickAutoAndContinue();
        try {
            wizardPage = wizardPage.fillOutPartialAutoPostingAndContinue();
        } catch(Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        Assert.assertTrue(wizardPage.getErrorTextElement().getText().contains("Some required information is missing or incorrect."));
    }
}
