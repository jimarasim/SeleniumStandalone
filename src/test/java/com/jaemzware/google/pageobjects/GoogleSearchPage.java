package com.jaemzware.google.pageobjects;

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
public class GoogleSearchPage extends BasePageObject {
    @FindBy(id="lst-ib")
    private WebElement searchTextBox;

    public GoogleSearchPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(searchTextBox));    }

    public GoogleResultsPage PerformSearch(String searchString){
        //type in a search string
        searchTextBox.sendKeys(searchString);

        //hit the enter key
        searchTextBox.sendKeys(Keys.RETURN);

        return new GoogleResultsPage(driver);
    }
}
