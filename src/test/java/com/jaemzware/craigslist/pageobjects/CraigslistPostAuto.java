package com.jaemzware.craigslist.pageobjects;

import com.jaemzware.BaseSeleniumTest;
import org.testng.annotations.Test;

public class CraigslistPostAuto extends BaseSeleniumTest{

    @Test(groups={"craigslisttest","craigslistpostautotest"})
    public void CraigslistPostAutoTest() throws Exception {
        driver.get("https://seattle.craigslist.org");
        CraigslistSearchPage searchPage = new CraigslistSearchPage(driver);
        CraigslistClassifiedWizardPage wizardPage = searchPage.PostToClassified();
        wizardPage = wizardPage.pickForSaleByOwnerAndContinue();
        wizardPage = wizardPage.pickAutoAndContinue();
        wizardPage = wizardPage.pickSeattle();
        wizardPage = wizardPage.fillOutAutoPostingAndContinue();

        Thread.sleep(2000);
    }

}
