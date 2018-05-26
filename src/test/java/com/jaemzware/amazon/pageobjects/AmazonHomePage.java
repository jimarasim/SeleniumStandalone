package com.jaemzware.amazon.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonHomePage extends BasePageObject{

    @FindBy(id = "nav-link-accountList")
    WebElement accountsMenu;

    @FindBy(linkText = "Find a Gift")
    WebElement findGiftMenuItem;

    @FindBy(id="twotabsearchtextbox")
    WebElement searchTextBox;

    public AmazonHomePage(WebDriver driver) {
        super(driver);
    }

    public AmazonGiftResultsPage clickFindGiftMenuItem() {
        //hover over the accounts menu
        Actions action = new Actions(driver);
        action.moveToElement(accountsMenu).build().perform();

        //wait for the find a gift option to appear in the menu
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(findGiftMenuItem));

        //move to the find a gift option carefully from the last action and click it
        action.moveToElement(findGiftMenuItem).click().build().perform();

        //wait for the first gift to appear
        AmazonGiftResultsPage giftsPage = new AmazonGiftResultsPage(driver);
        giftsPage = giftsPage.waitForFirstGiftToAppear();

        return giftsPage;
    }

    public AmazonHomePage navigate() {
        driver.get("https://amazon.com");

        return this;
    }

    public AmazonSearchResultsPage searchForTerm(String searchTerm) {
        searchTextBox.sendKeys(searchTerm);
        searchTextBox.sendKeys(Keys.RETURN);

        AmazonSearchResultsPage searchResultsPage = new AmazonSearchResultsPage(driver);
        searchResultsPage.waitForFirstResult();
        return searchResultsPage;
    }

}
