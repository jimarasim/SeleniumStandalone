package com.jaemzware.craigslist.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistSearchPage extends BasePageObject {

    @FindBy(id="query")
    WebElement searchTextBox;

    @FindBy(id="post")
    WebElement postToClassifieds;

    public CraigslistSearchPage(WebDriver driver){
        super(driver);
    }

    public CraigslistResultsPage PerformSearch(String searchString){
        //type in a search string
        searchTextBox.sendKeys(searchString);

        //hit the enter key
        searchTextBox.sendKeys(Keys.RETURN);

        return new CraigslistResultsPage(driver);
    }

    public CraigslistClassifiedWizardPage PostToClassified() {
        postToClassifieds.click();

        (new WebDriverWait(driver,10000)).until(ExpectedConditions.elementToBeClickable(new CraigslistClassifiedWizardPage(driver).getContinueButton()));

        return new CraigslistClassifiedWizardPage(driver);
    }


}
