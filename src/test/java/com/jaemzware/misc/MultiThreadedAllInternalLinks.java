package com.jaemzware.misc;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Hashtable;
import java.util.List;

public class MultiThreadedAllInternalLinks extends Thread {
    private final int numBrowsers = 7;
    private static int semaphore = 0;
    private String seed = "https://www.soundtransit.org";
    private String seekHrefStartsWith = "/";
    public static Hashtable<String,String> toVisit = new Hashtable<String,String>();
    public static Hashtable<String,String> visited = new Hashtable<String,String>();

    @BeforeClass
    public static void BeforeClass(){
        //DOWNLOAD CHROMEDRIVER FROM http://chromedriver.storage.googleapis.com/index.html AND PLACE IN PROJECT DIRECTORY
        System.setProperty("webdriver.chrome.driver", "./grid/chromedrivermac"); // FOR MAC
    }

    //THIS TEST LAUNCHES MULTIPLE BROWSERS TO VISIT ALL LINKS ON THE CRAIGSLIST HOME PAGE CONCURRENTLY
    @Test
    public void MultiBrowserTest() throws Exception{
        WebDriver driver = new ChromeDriver();
        driver.get(seed);
        visited.put(seed, "SEED");
        GetUniqueHrefs(driver, seed);
        System.out.println("INITIAL SIZE:" + toVisit.size());
        driver.quit();

        for(int i=0; i<numBrowsers; i++){
            (new MultiThreadedAllInternalLinks()).start();
        }

        //wait until all threads are finished to end the test
        while(semaphore > 0){
            Thread.sleep(1000);
        }

    }

    public void run(){
        semaphore++;

        WebDriver driver = new ChromeDriver();

        String[] href;

        do {
            href = GetHrefToVisit();
            if(href != null && href[0].startsWith(seed)) { //NOTE: FOUND AN HREF ON THE SITE THAT BEGAN WITH //KINGCOUNTY.GOV, AND I DON'T WANT TO FOLLOW THOSE. I THINK ITS A BUG ON PAGE https://www.soundtransit.org/blog/platform/get-ready-seattle-squeeze
                System.out.println("VISITING: " + href[0] + " FROM: " + href[1]);
                driver.get(href[0]);
                GetUniqueHrefs(driver, href[0]);
            }
        } while(href != null);

        driver.quit();

        semaphore--;
    }

    public void GetUniqueHrefs(WebDriver driver, String currentHref) {
        List<WebElement> hrefs = driver.findElements(By.cssSelector("a[href^='"+seekHrefStartsWith+"']"));
        for(WebElement we:hrefs) {
            try {
                String href = we.getAttribute("href");
                if (!toVisit.containsKey(href) && !visited.containsKey(href)) {
                    toVisit.put(href, currentHref);
                }
            } catch (StaleElementReferenceException serex) {
                System.out.println("STALE ELEMENT EXCEPTION");
                continue;
            }
        }
    }

    public String[] GetHrefToVisit() {
        String[] hrefToVisit = new String[2];

        System.out.println("SIZE:" + toVisit.size() + " " + visited.size());

        if(toVisit.isEmpty()) {
            return null;
        } else {
            for(String key:toVisit.keySet()) {
                hrefToVisit[0] = key;
                hrefToVisit[1] = toVisit.remove(key);
                visited.put(hrefToVisit[0],hrefToVisit[1]);
                break;
            }
            return hrefToVisit;
        }
    }
}
