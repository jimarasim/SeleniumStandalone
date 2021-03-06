package com.jaemzware.pinterest.pageobjects;

import static com.jaemzware.Utilities.GetCommandlineCredentials;
import com.jaemzware.BasePageObject;
import java.util.HashMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class PinterestLoginPage extends BasePageObject {

    @FindBy(css="button.GoogleConnectButton.active")
    private WebElement pGoogleConnectButton;

    @FindBy(xpath="//button/div[contains(text(),'Log in')]")
    private WebElement loginButton;

    @FindBy(css="input[name=q]")
    private WebElement pSearch;

    @FindBy(css="input[type=email]")
    private WebElement googleEmail;

    @FindBy(css="input[type=password]")
    private WebElement googlePassword;

    @FindBy(xpath="//span[contains(text(),'Next')]")
    private WebElement nextButton;

    public PinterestLoginPage(WebDriver driver){
        super(driver);
    }

    public void LoginIfNot() throws Exception{

        //if the login button is found, we are not logged in
        if(IsElementEnabled(loginButton)){
            //retrieve credentials
            HashMap creds = GetCommandlineCredentials();
            
            //login with the google button
            pGoogleConnectButton.click();

            //wait for the second window to open up
            (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    return driver.getWindowHandles().size() == 2;
                }
            });

            //switch to the second window
            String mainWindowHandle = driver.getWindowHandle();
            Set<String> windowHandles = driver.getWindowHandles();
            for(String handle:windowHandles){
                if(handle!=mainWindowHandle){
                    driver.switchTo().window(handle);
                }
            }

            //login to google window.
            (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(googleEmail));
            googleEmail.sendKeys((String)creds.get("username"));
            nextButton.click();
            (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(googlePassword));
            googlePassword.sendKeys((String)creds.get("password"));
            nextButton.click();

            //switch back to main window
            driver.switchTo().window(mainWindowHandle);
            
            loginButton.click();

            (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(pSearch));
        }
    }
     
}
