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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private WebDriver driver = null;

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
    //. LOADING A WEB PAGE
    //. ASSERTING AN ELEMENT IS PRESENT
    //. FINDING AN ELEMENT BY ID
    //. TYPING TEXT INTO A TEXT BOX
    //. TYPING THE ENTER KEY ON A TEXT BOX
    //. WAITING FOR AN ELEMENT TO BE CLICKABLE (PAGE LOAD FROM A PAGE ACTION)
    //. RETRIEVING THE TEXT OF AN ANCHOR ELEMENT
    //. RETRIEVING THE HREF (ATTRIBUTE) OF AN ANCHOR ELEMENT
    //. WAITING FOR AN ELEMENT TO GO STALE (PAGE LOAD FROM A PAGE ACTION)
    @Test
    public void SimpleSeleniumTestForGoogle() throws Exception{
        driver.get("https://google.com");

        //assert that the search text box exists
        Assert.assertTrue(driver.findElements(By.id("lst-ib")).size()==1);

        //get a webelement for the search text box
        WebElement googleSearchTextBox = driver.findElement(By.id("lst-ib"));

        //type in a search string
        googleSearchTextBox.sendKeys("restaurants in west seattle");

        //hit the enter key
        googleSearchTextBox.sendKeys(Keys.RETURN);

        //wait 5 seconds for the results text to appear
        WebElement resultsTextElement = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.elementToBeClickable(By.id("resultStats")));

        //assert results are available
        Assert.assertTrue(driver.findElements(By.xpath("//div[@id='rso']//h3/a")).size()>0);

        //get webelements for the results
        List<WebElement> results = driver.findElements(By.xpath("//div[@id='rso']//h3/a"));

        //print out the urls
        for(WebElement e:results){
            System.out.println(e.getText() + ": " + e.getAttribute("href"));
        }

        //get the first result's url
        String firstResultUrl = results.get(0).getAttribute("href");

        //click the first result
        results.get(0).click();

        //wait for the page to appear
        Boolean stalenessResult = (new WebDriverWait(driver, 5))
                .until(ExpectedConditions.stalenessOf(resultsTextElement));

        //verify we were taken to the first result
        Assert.assertTrue(driver.getCurrentUrl().equals(firstResultUrl));
    }

    @After
    public void AfterTest(){
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;
    }
}
