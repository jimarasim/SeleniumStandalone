package com.jaemzware.amazon.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductPage extends BasePageObject{

    @FindBy(id="title")
    WebElement productTitle;

    public AmazonProductPage(WebDriver driver) {
        super(driver);
    }

    public AmazonProductPage waitForTitleToAppear() {
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(productTitle));

        return this;
    }

    public String getProductTitle() {
        return productTitle.getText();
    }
}
