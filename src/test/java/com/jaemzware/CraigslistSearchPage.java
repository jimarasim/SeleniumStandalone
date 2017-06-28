package com.jaemzware;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistSearchPage extends BasePageObject {

    @FindBy(id="query")
    WebElement searchTextBox;

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


}
