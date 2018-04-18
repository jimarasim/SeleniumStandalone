package com.jaemzware;

import static com.jaemzware.Utilities.DateStamp;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class BaseSeleniumTest {

    protected WebDriver driver = null;

    public PrintWriter writer = null;
    
    private String fileName = "";

    @BeforeMethod(alwaysRun = true)
    public void BeforeTest() throws Exception{
        driver = StartDriver();

        //maximize window (NEED TO USE OPTIONS IN CHROME TO MAXIMIZE INSTEAD)
        //driver.manage().window().maximize();

        //open a new file for writing html report
        fileName = "index" + DateStamp() + ".htm";
        writer = new PrintWriter(fileName, "UTF-8");
        writer.write("<html><head></head><body>");
    }

    @AfterMethod(alwaysRun = true)
    public void AfterTest(ITestResult testResult){
        if (testResult.getStatus() == ITestResult.FAILURE) {
            String screenshotFilename = Utilities.ScreenShot(driver);
            writer.println("<H1>SCREENSHOT OF TEST FAILURE:</H1>");
            writer.println("<img src='"+screenshotFilename+"' />");
        }
        
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;

        //close file report
        writer.write("</body></html>");
        writer.flush();
        writer.close();
        
        System.out.println("REPORT GENERATED: " + fileName);
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
        
        //CHECK COMMAND LINE IF GRID IS BEING USED
        if(System.getProperty("grid") == null) {
        switch(browserToStart) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "./grid/chromedrivermac"); // FOR MAC
                driverToLaunch = new ChromeDriver();
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "./grid/geckodriver");
                driverToLaunch = new FirefoxDriver();
                break;
            case SAFARI:
                driverToLaunch = new SafariDriver();
                break;
            default:
                driverToLaunch = null;
                break;
        }
        } else {
            driverToLaunch = StartGridDriver(browserToStart);
        }
  
        return driverToLaunch;
    }
    
    //THIS METHOD STARTS THE BROWSERS THROUGH SELENIUM GRID. SCRIPTS TO START HUB AND NODES CAN BE FOUND IN ./grid
    private WebDriver StartGridDriver(BrowserType browserToStart) throws MalformedURLException {
        WebDriver driverToLaunch = null;
        
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browserToStart.browserName);
        cap.setPlatform(browserToStart.platform);
        cap.setVersion(browserToStart.version);
        
        String host = System.getProperty("host")!=null ? System.getProperty("host") : "http://localhost";
        String port = System.getProperty("port")!=null ? System.getProperty("port") : "4444";

        driverToLaunch = new RemoteWebDriver(new URL(host + ":" + port + "/wd/hub"), cap);
        
        return driverToLaunch;
    }
}

