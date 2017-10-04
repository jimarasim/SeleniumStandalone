package com.jaemzware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PinterestViewerPage extends BasePageObject {

    @FindBy(xpath="//div[contains(text(),'Save')]")
    private WebElement saveButton;

    @FindBy(xpath="//div[@role='button']//p[contains(text(),'Test')]")
    private WebElement testBoardButton;

    @FindBy(xpath="//div[contains(text(),'Saved to')]")
    private WebElement savedToMessage;

    @FindBy(css="div.flashlightEnabledContent > div > div > img")
    private WebElement resultImage;

    @FindBy(css="button.Button.borderless.close.visible")
    private WebElement closeButton;

    public PinterestViewerPage(WebDriver driver){
        super(driver);
    }

    public WebElement getSaveButton(){
        return saveButton;
    }

    public String getResultImageSrc(){
        return resultImage.getAttribute("src");
    }

    public void pinToTestBoard(){
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

}
