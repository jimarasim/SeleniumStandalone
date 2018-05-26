package com.jaemzware;

import static com.jaemzware.Utilities.DateStamp;
import static com.jaemzware.Utilities.GetOsType;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
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
    
    public BrowserType browserToStart= BrowserType.CHROME;;
    
    private String fileName = "";

    @BeforeMethod(alwaysRun = true)
    public void BeforeTest() throws Exception{
        driver = StartDriver();

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
                //CHECK OS TYPE AND USE APPROPRIATE CHROME DRIVER
                if(Utilities.GetOsType() == OsType.UNIX) {
                    System.setProperty("webdriver.chrome.driver", "./grid/chromedriverlinux"); // FOR MAC
                } else {
                    System.setProperty("webdriver.chrome.driver", "./grid/chromedrivermac"); // FOR LINUX
                }

                ChromeOptions options = new ChromeOptions();

                if(System.getProperty("headless")==null) {
                    //START MAXIMIZED
                    options.addArguments("start-maximized");
                } else {
                    //START HEADLESS
                    options.setHeadless(true);
                }
                
                //LOG BROWSER ERRORS
                LoggingPreferences loggingprefs = new LoggingPreferences();
                loggingprefs.enable(LogType.BROWSER, Level.ALL);
                options.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
                
                driverToLaunch = new ChromeDriver(options);
                break;
            case FIREFOX:
                System.setProperty("webdriver.gecko.driver", "./grid/geckodriver");

                FirefoxOptions ffoptions = new FirefoxOptions();

                if(System.getProperty("headless")!=null) {
                    ffoptions.setHeadless(true);
                }

                driverToLaunch = new FirefoxDriver(ffoptions);
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

        System.out.println(browserToStart.browserName.toString());

        if(browserToStart.browserName.toString().toLowerCase().contains("CHROME")) {
            LoggingPreferences loggingprefs = new LoggingPreferences();
            loggingprefs.enable(LogType.BROWSER, Level.ALL);
            cap.setCapability(CapabilityType.LOGGING_PREFS, loggingprefs);
        }
        
        String host = System.getProperty("host")!=null ? System.getProperty("host") : "http://localhost";
        String port = System.getProperty("port")!=null ? System.getProperty("port") : "4444";

        driverToLaunch = new RemoteWebDriver(new URL(host + ":" + port + "/wd/hub"), cap);
        
        return driverToLaunch;
    }
    
    protected String ExtractJSLogs() {
        StringBuilder logString = new StringBuilder();
        logString.append("<table>");

        LogEntries browserLog = driver.manage().logs().get(LogType.BROWSER);
        if (browserLog.getAll().size() > 0) {
            logString.append("<tr><td colspan=2><h3>BROWSER</h3></td></tr>");
            logString.append("<tr><td>LEVEL</td><td>MESSAGE</td></tr>");
            logString.append(WriteLogEntryRows(browserLog));
        } else {
            logString.append("<tr><td colspan=2>No BROWSER log entries found.</td></tr>");
        }
        
        logString.append("</table>");
        return logString.toString();
    }
    // ERROR LOGGING - TAKES LONG - ADD CAPABILITY WHEN CREATING driver BEFORE USING

    /**
     *
     * @param entries
     * @return
     */
    private String WriteLogEntryRows(LogEntries entries) {
        StringBuilder logEntryRows = new StringBuilder();

        String errorLevel;
        for (LogEntry entry : entries) {
            errorLevel = entry.getLevel().toString();
            logEntryRows.append("<tr>");

            // error level color coding
            if (errorLevel.contains("SEVERE")) {
                logEntryRows.append("<td class='severe'><b>");
                logEntryRows.append(errorLevel).append("</b></td>");
                logEntryRows.append("<td>").append(entry.getMessage()).append("</td></tr>");
            }
            else if (errorLevel.contains("WARNING")) {
                logEntryRows.append("<td class='warning'><b>");

                logEntryRows.append(errorLevel).append("</b></td>");
                logEntryRows.append("<td>").append(entry.getMessage()).append("</td></tr>");
            } else if (errorLevel.contains("INFO")) {
                logEntryRows.append("<td class='info'><b>");

                logEntryRows.append(errorLevel).append("</b></td>");
                logEntryRows.append("<td>").append(entry.getMessage()).append("</td></tr>");
            } else if (errorLevel.contains("FINE")) {
                logEntryRows.append("<td class='info'><b>");

                logEntryRows.append(errorLevel).append("</b></td>");
                logEntryRows.append("<td>").append(entry.getMessage()).append("</td></tr>");
            } else {
                logEntryRows.append("<td><b>");
                logEntryRows.append(errorLevel).append("</b></td>");
                logEntryRows.append("<td>").append(entry.getMessage()).append("</td></tr>");
            }
        }
        return logEntryRows.toString();
    }
}

