package com.jaemzware;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class BasePageObject {
    protected WebDriver driver;

    @FindBy(xpath="//*")
    private List<WebElement> allPageElements;

    public BasePageObject(WebDriver driver){
        this.driver = driver;

        PageFactory.initElements(driver,this);  //THIS BINDS THE FINDBYs TO THEIR WEBELEMENTS. THEY DON'T GET FOUND UNTIL THEY ARE USED. THIS REDUCES THE EFFORT OF USING A LOT OF DRIVER.FINDBY CALLS
    }

    public BasePageObject PrintElements(){

        for(WebElement element:allPageElements){
            System.out.println("ELEMENT: "+element.getTagName()+" TEXT:"+element.getText());
        }

        return this;
    }

    public boolean IsElementEnabled(WebElement element){
        try {
            if(element.isEnabled() && element.isDisplayed()) {
                return true;
            }
            else{
                return false;
            }
        }catch(NoSuchElementException nsex){
            return false;
        }
    }
}
