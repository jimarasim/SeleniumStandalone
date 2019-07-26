package com.jaemzware.amazon.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductPage extends BasePageObject{

    @FindBy(id="title")
    WebElement productTitle;

    @FindBy(id="quantity")
    WebElement quantitySelect;

    @FindBy(id="add-to-cart-button")
    WebElement addToCartbutton;

    @FindBy(css="div.a-popover-modal")
    WebElement popOver;

    @FindBy(css="div.a-popover-modal input.a-button-input[type='submit']")
    WebElement popOverContinue;

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

    public void selectQuantity(String quantity) {
        Select quantityDropDown = new Select(quantitySelect);
        quantityDropDown.selectByValue(quantity);
    }

    public void clickAddToCartButton() {
        addToCartbutton.click();

        (new WebDriverWait(driver,10)).until(ExpectedConditions.visibilityOf(popOver));

    }

    public void clickContinueButton() {
        popOverContinue.click();

    }
}
