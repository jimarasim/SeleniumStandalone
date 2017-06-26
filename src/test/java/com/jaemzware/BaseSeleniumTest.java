package com.jaemzware;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class BaseSeleniumTest {

    protected WebDriver driver = null;

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

    @After
    public void AfterTest(){
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;
    }
}

