package com.jaemzware;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistResultsPage extends BasePageObject {

    @FindBy(xpath="//li[@class='result-row']/p/a")
    List<WebElement> searchResults;

    @FindBy(xpath="//a[@title='next page']")
    WebElement nextPageLink;

    public CraigslistResultsPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(nextPageLink));
    }

    public CraigslistResultsPage PrintOutResults(){

        for(WebElement result:searchResults){
            System.out.println(result.getText());
        }

        return this;
    }

}
