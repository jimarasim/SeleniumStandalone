package com.jaemzware.amazon.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonGiftResultsPage extends BasePageObject {

    @FindBy(xpath="//figure[@class=\"asin-container\"]/a")
    WebElement firstGift;

    public AmazonGiftResultsPage(WebDriver driver) {
        super(driver);
    }

    public AmazonGiftResultsPage waitForFirstGiftToAppear() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(firstGift));

        return this;
    }

    public AmazonProductPage clickFirstGift() {
        firstGift.click();

        AmazonProductPage productPage = new AmazonProductPage(driver);
        productPage = productPage.waitForTitleToAppear();

        return productPage;
    }
}
