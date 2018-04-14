package com.jaemzware;

import static com.jaemzware.Utilities.DateStamp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class BaseSeleniumTest {

    protected WebDriver driver = null;

    public PrintWriter writer = null;

    @BeforeMethod(alwaysRun = true)
    public void BeforeTest() throws Exception{
        driver = StartDriver();

        //maximize window
        driver.manage().window().maximize();

        //open a new file for writing html report
        String fileName = "index" + DateStamp() + ".htm";
        writer = new PrintWriter(fileName, "UTF-8");
        writer.write("<html><head></head><body>");
    }

    @AfterMethod(alwaysRun = true)
    public void AfterTest(){
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;

        //close file report
        writer.write("</body></html>");
        writer.flush();
        writer.close();
    }
    
    /**
     * StartDriver
     * 
     * Checks command line for desired browser type, and launches the browser.
     * 
     * @return WebDriver pointed to the specified browser
     */
    private WebDriver StartDriver() throws Exception{
        WebDriver driverToLaunch = null;
        BrowserType browserToStart = BrowserType.CHROME;  //default browser to chrome if not specified
        
        //CHECK COMMAND LINE FOR SPECIFIED BROWSER
        String specifiedBrowser = System.getProperty("browser");
        
        if (specifiedBrowser != null) {
            try {
                browserToStart = BrowserType.valueOf(specifiedBrowser);
            } catch (IllegalArgumentException iaex) {
                throw new Exception("'" + specifiedBrowser + "' IS NOT A SUPPORTED BROWSER.");
            }
        }
        
        switch(browserToStart) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "chromedrivermac"); // FOR MAC
                driverToLaunch = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                driverToLaunch = new FirefoxDriver();
                break;
            default:
                driverToLaunch = null;
                break;
        }
  
        return driverToLaunch;
    }
}

