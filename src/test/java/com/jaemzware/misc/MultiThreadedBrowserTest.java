package com.jaemzware.misc;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class MultiThreadedBrowserTest extends Thread{

    private final int numBrowsers = 3;
    private static int semaphore = 0;

    @BeforeClass
    public static void BeforeClass(){
        //DOWNLOAD CHROMEDRIVER FROM http://chromedriver.storage.googleapis.com/index.html AND PLACE IN PROJECT DIRECTORY
        System.setProperty("webdriver.chrome.driver", "./grid/chromedrivermac"); // FOR MAC
    }

    //THIS TEST LAUNCHES MULTIPLE BROWSERS TO VISIT ALL LINKS ON THE CRAIGSLIST HOME PAGE CONCURRENTLY
    @Test
    public void MultiBrowserTest() throws Exception{

        for(int i=0; i<numBrowsers; i++){
            (new MultiThreadedBrowserTest()).start();
        }

        //wait until all threads are finished to end the test
        while(semaphore > 0){
            Thread.sleep(1000);
        }

    }

    //This thread opens a browser and follows all craigslist links from the home page
    public void run(){
        semaphore++;

        WebDriver driver = new ChromeDriver();

        driver.get("https://seattle.craigslist.org");

        List<WebElement> craigslistLinks = driver.findElements(By.xpath("//a[contains(@href,'craigslist')]"));

        int numLinks = craigslistLinks.size();

        String[] linksArray = new String[numLinks];

        for(int i=0;i<numLinks;i++){
            linksArray[i] = craigslistLinks.get(i).getAttribute("href");
        }

        for(int i=0; i<numLinks;i++){
            driver.get(linksArray[i]);
        }

        driver.quit();

        semaphore--;
    }

}
