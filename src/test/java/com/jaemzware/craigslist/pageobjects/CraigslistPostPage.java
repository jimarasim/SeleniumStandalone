package com.jaemzware.craigslist.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.PrintWriter;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistPostPage extends BasePageObject {

    @FindBy(id="display-date")
    WebElement displayDate;

    @FindBy(id="titletextonly")
    WebElement title;

    @FindBy(id="postingbody")
    WebElement body;

    @FindBy(xpath="//div[contains(@class,'slide first visible')]/img")
    WebElement image;

    public CraigslistPostPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(displayDate));
    }

    public void PrintResultDetails(){
        System.out.println("DISPLAY DATE:"+displayDate.getText());
        System.out.println("TITLE:"+title.getText());
        System.out.println("BODY:"+body.getText());

        if(IsElementEnabled(image)){
            System.out.println("IMAGE SRC:"+image.getAttribute("src"));
        }
    }


    public void GetAndWriteDetails(String href, PrintWriter writer){
        driver.get(href);
        writer.println("<a target='_blank' href='"+href+"'>"+href+"</a>");
        writer.println("DISPLAY DATE:"+displayDate.getText()+"<br />");
        writer.println("TITLE:"+title.getText()+"<br />");
        writer.println("BODY:"+body.getText()+"<br />");

        if(IsElementEnabled(image)){
            writer.println("<img src='"+image.getAttribute("src")+"'><br />");
        }
        writer.println("<hr />");
    }
}
