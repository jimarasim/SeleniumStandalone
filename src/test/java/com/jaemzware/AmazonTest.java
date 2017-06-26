package com.jaemzware;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AmazonTest extends BaseSeleniumTest
{
    private final String accountsMenuId = "nav-link-accountList";
    private final String firstGiftXpath = "//*[@id=\"infinite-scroll-page-0\"]/ul/il[1]/a";
    private final String giftTitleId = "title";

    //THIS TEST DEMONSTRATES THE FOLLOWING SELENIUM FUNCTIONALITY:
    //. LOADING A WEB PAGE
    //. FINDING AN ELEMENT BY ITS ID
    //. HOVERING OVER AN ELEMENT
    //. WAITING FOR A LINK WITH SPECIFIC TEXT TO BE VISIBLE
    //. WAITING FOR AN ELEMENT WITH A SPECIFIC XPATH TO BE VISIBLE
    //. WAITING FOR AN ELEMENT WITH A SPECIFIC ID TO BE VISIBLE
    @Test
    public void SimpleSeleniumTestForAmazon() throws Exception{
        //load the amazon site
        driver.get("https://amazon.com");

        //assert the accounts menu exists
        Assert.assertTrue(driver.findElements(By.id(accountsMenuId)).size()==1);

        //get a webelement we can mouse over
        WebElement accountsMenu = driver.findElement(By.id(accountsMenuId));

        //hover over the accounts menu
        Actions action = new Actions(driver);
        action.moveToElement(accountsMenu).build().perform();

        //wait for the find a gift option to appear in the menu
        WebElement findAGiftElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.linkText("Find a Gift")));

        //move to the find a gift option carefully from the last action and click it
        action.moveToElement(findAGiftElement).click().build().perform();

        //wait for the first gift to appear
        WebElement firstGiftElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(firstGiftXpath)));

        //click the first gift
        firstGiftElement.click();

        //wait for the gift title to appear
        WebElement giftTitleElement = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.elementToBeClickable(By.id(giftTitleId)));

        System.out.println(giftTitleElement.getText());
    }

}
