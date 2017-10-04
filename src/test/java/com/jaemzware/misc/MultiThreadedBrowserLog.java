package com.jaemzware.misc;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class MultiThreadedBrowserLog extends Thread{
    //mvn -Dinput=http://seattlerules.com -DaString=seattlerules.com -DaNumber=10 -Dtest=MultiThreadedBrowserLog test

    private String homePageUrl = "https://seattle.craigslist.org";
    private String linksToFollowContain = "craigslist";

    private int numBrowsers = 5;
    private static int semaphore = 0;
    private static String[] linksArray;
    private static boolean linksArrayMutexLockOn = false;
    private static int currentLinksArrayIndex = 0;
    private static List<String> loggingOutput = new CopyOnWriteArrayList<String>();  //thread safe list

    @BeforeClass
    public static void BeforeClass(){
        //DOWNLOAD CHROMEDRIVER FROM http://chromedriver.storage.googleapis.com/index.html AND PLACE IN PROJECT DIRECTORY
        System.setProperty("webdriver.chrome.driver", "chromedrivermac"); // FOR MAC
    }

    @Before
    public void BeforeTest(){

        //CHECK FOR PARAMETERS
        if(System.getProperty("input")!=null){
            homePageUrl = System.getProperty("input");
        }
        if(System.getProperty("aString")!=null){
            linksToFollowContain = System.getProperty("aString");
        }
        if(System.getProperty("aNumber")!=null){
            numBrowsers = Integer.parseInt(System.getProperty("aNumber"));
        }
    }

    //THIS TEST VISITS ALL LINKS ON A HOME PAGE, BY DIVY'ING THEM UP AMONGST MULTIPLE BROWSERS (NO LINK IS VISITED TWICE)
    @Test
    public void VerifyHomeLinksTest() throws Exception{

        //GET LINKS TO BE VISITED
        WebDriver driver = getChromeDriverWithLogging();
        //load home page
        driver.get(homePageUrl);
        //get links to visit
        List<WebElement> craigslistLinks = driver.findElements(By.xpath("//a[contains(@href,'"+linksToFollowContain+"')]"));
        //populate the linksArray for browsers to pull from
        int numLinks = craigslistLinks.size();
        linksArray = new String[numLinks];
        for(int i=0;i<numLinks;i++){
            linksArray[i] = craigslistLinks.get(i).getAttribute("href");
        }

        writeOutLoggerErrors(driver,homePageUrl);

        driver.quit();

        //launch browsers to visit the links
        for(int i=0; i<numBrowsers; i++){
            (new MultiThreadedBrowserLog()).start();
        }

        //wait until all threads are finished to end the test
        while(semaphore > 0){
            Thread.sleep(1000);
        }

        //write out the logger messages
        for(String logMessage:loggingOutput){
            System.out.println(logMessage);
        }

    }

    public void run(){
        int arrayIndexToVisit;
        semaphore++;

        WebDriver driver = getChromeDriverWithLogging();

        //while there are still entries to process in the links array, visit the next link when the mutex becomes available
        while(currentLinksArrayIndex < linksArray.length){
            //use the mutext to ensure the currentLinksArrayIndex isn't used concurrently, to avoid duplicate page visits
            if(!linksArrayMutexLockOn){
                linksArrayMutexLockOn = true;
                arrayIndexToVisit = currentLinksArrayIndex;
                currentLinksArrayIndex += 1;
                linksArrayMutexLockOn = false;
            }
            else{
                continue;
            }

            //visit the array entry retrieved
            driver.get(linksArray[arrayIndexToVisit]);
            writeOutLoggerErrors(driver,linksArray[arrayIndexToVisit]);
        }

        driver.quit();

        semaphore--;
    }

    private WebDriver getChromeDriverWithLogging(){
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences loggingprefs = new LoggingPreferences();
        loggingprefs.enable(LogType.BROWSER, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);

        WebDriver driver = new ChromeDriver(cap);

        return driver;
    }

    private void writeOutLoggerErrors(WebDriver driver, String href){
        LogEntries browserLog = driver.manage().logs().get(LogType.BROWSER);

        for(LogEntry logentry: browserLog){
            if(logentry.getLevel().toString().contains("SEVERE")){
                loggingOutput.add(href + ": SEVERE-"+logentry.getMessage());
            }
        }

    }
}
