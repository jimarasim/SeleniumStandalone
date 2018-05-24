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
    
    @FindBy(css="a[data-alltitle='all for sale']")
    WebElement forSaleLink;

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

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(new CraigslistClassifiedWizardPage(driver).getContinueButton()));

        return new CraigslistClassifiedWizardPage(driver);
    }

    public CraigslistResultsPage ClickForSaleLink() {
        forSaleLink.click();
        
        CraigslistResultsPage resultsPage = new CraigslistResultsPage(driver);
        
        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(new CraigslistResultsPage(driver).GetFirstResult()));
        
        return resultsPage;
    }
}
