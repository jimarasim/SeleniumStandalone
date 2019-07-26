package com.jaemzware.amazon.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchResultsPage extends BasePageObject{

    @FindBy(css="div.s-result-list a.a-link-normal")
    WebElement firstResult;

    public AmazonSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public AmazonSearchResultsPage waitForFirstResult() {
        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(firstResult));

        return this;
    }

    public AmazonProductPage clickFirstResult() {
        firstResult.click();

        AmazonProductPage productPage = new AmazonProductPage(driver);
        productPage.waitForTitleToAppear();

        return productPage;
    }
}
