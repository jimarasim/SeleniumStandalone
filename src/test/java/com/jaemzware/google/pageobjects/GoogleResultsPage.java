package com.jaemzware.google.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class GoogleResultsPage extends BasePageObject {

    @FindBy(xpath = "//div[@id='rso']//h3/a")
    private List<WebElement> searchResults;

    @FindBy(xpath = "//div[@id='rso'//h3/a/[1]")
    private WebElement firstResult;

    @FindBy(id="resultStats")
    private WebElement resultsText;

    public GoogleResultsPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(resultsText));
    }

    public GoogleResultsPage PrintOutResults(){
        //print out the urls
        for(WebElement e:searchResults){
            System.out.println(e.getText() + ": " + e.getAttribute("href"));
        }

        return this;
    }

    public BasePageObject ClickFirstResult(){
        //get the first result's url
        String firstResultUrl = searchResults.get(0).getAttribute("href");

        //click the first result
        searchResults.get(0).click();

        //wait for the page to appear
        Boolean stalenessResult = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.urlContains(firstResultUrl));

        return new BasePageObject(driver);
    }

}
