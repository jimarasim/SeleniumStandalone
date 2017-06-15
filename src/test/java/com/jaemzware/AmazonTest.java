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
public class AmazonTest
{
    private WebDriver driver = null;
    private final String accountsMenuId = "nav-link-accountList";

    @BeforeClass
    public static void BeforeClass(){
        //DOWNLOAD CHROMEDRIVER FROM http://chromedriver.storage.googleapis.com/index.html AND PLACE IN PROJECT DIRECTORY
        System.setProperty("webdriver.chrome.driver", "chromedrivermac"); // FOR MAC
    }

    @Before
    public void BeforeTest(){
        //Before each test, launch a new Chrome Browser
        driver = new ChromeDriver();
    }

    //THIS TEST DEMONSTRATES THE FOLLOWING SELENIUM FUNCTIONALITY:
    //1. MOUSE OVER
    @Test
    public void SimpleSeleniumTestForAmazon() throws Exception{
        //load the amazon site
        driver.get("https://amazon.com");

        //assert the accounts menu exists
        Assert.assertTrue(driver.findElements(By.id(accountsMenuId)).size()==1);

        //get a webelement we can mouse over
        WebElement accountsMenu = driver.findElement(By.id(accountsMenuId));

        //mouse over the accounts menu and click "Find a Gift"
        Actions action = new Actions(driver);
        action.moveToElement(accountsMenu).moveToElement(driver.findElement(By.linkText("Find a Gift"))).click().build().perform();

        Thread.sleep(10000);


    }

    @After
    public void AfterTest(){
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;
    }
}
