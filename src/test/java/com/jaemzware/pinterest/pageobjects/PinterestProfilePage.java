package com.jaemzware.pinterest.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PinterestProfilePage extends BasePageObject {
    @FindBy(css="button[aria-label='Edit settings']")
    private WebElement settingsButton;

    @FindBy(xpath="//div[contains(text(),'Pins')]")
    WebElement pinsButton;

    @FindBy(xpath="//div[contains(text(),'Create Pin')]")
    WebElement createPinButton;

    @FindBy(css="div[data-grid-item=true]:nth-child(2)")
    private WebElement firstResult;

    @FindBy(css="div.cardWrapper")
    private WebElement firstBoard;

    public PinterestProfilePage(WebDriver driver){
        super(driver);
    }

    public WebElement getFirstResult() {
        return firstResult;
    }

    public WebElement getFirstBoard() {
        return firstBoard;
    }

    public void clickPinsButton(){
        pinsButton.click();

        (new WebDriverWait(driver,20)).until(ExpectedConditions.elementToBeClickable(createPinButton));
    }

    public PinterestViewerPage clickFirstResult(){
        firstResult.click();

        PinterestViewerPage viewerPage = new PinterestViewerPage(driver);

        (new WebDriverWait(driver, 20)).until(ExpectedConditions.elementToBeClickable(viewerPage.getSaveButton()));

        return viewerPage;
    }
}
