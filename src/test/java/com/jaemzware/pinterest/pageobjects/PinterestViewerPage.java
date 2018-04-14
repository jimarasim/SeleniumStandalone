package com.jaemzware.pinterest.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PinterestViewerPage extends BasePageObject {

    @FindBy(css="div.SaveButton")
    private WebElement saveButton;

    @FindBy(xpath="//div[@role='button']//div[contains(text(),'Test')]")
    private WebElement testBoardButton;

    @FindBy(xpath="//div[contains(text(),'Saved to')]")
    private WebElement savedToMessage;

    @FindBy(css="div.closeupContainer img")
    private WebElement resultImage;

    @FindBy(css="button[aria-label='Go back']")
    private WebElement closeButton;

    @FindBy(css="button[aria-label='View next item']")
    private WebElement nextPinButton;

    public PinterestViewerPage(WebDriver driver){
        super(driver);
    }

    public boolean IsSaveButtonEnabled(){return IsElementEnabled(saveButton);}
    public boolean IsCloseButtonEnabled(){return IsElementEnabled(closeButton);}

    public WebElement getSaveButton(){
        return saveButton;
    }

    public String getResultImageSrc(){
        return resultImage.getAttribute("src");
    }

    public void pinToTestBoard(){
        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(saveButton));
        
        saveButton.click();

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(testBoardButton));

        testBoardButton.click();

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(savedToMessage));
    }

    public void closeViewer(){
        closeButton.click();

        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver d) {
                return !IsElementEnabled(saveButton);
            }
        });
    }

    public void clickNextPin(){
        nextPinButton.click();

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(resultImage));
    }

}
