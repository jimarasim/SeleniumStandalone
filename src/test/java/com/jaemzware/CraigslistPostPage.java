package com.jaemzware;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistPostPage extends BasePageObject {

    @FindBy(id="display-date")
    WebElement displayDate;

    public CraigslistPostPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(displayDate));
    }
}
