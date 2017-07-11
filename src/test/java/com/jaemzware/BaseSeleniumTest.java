package com.jaemzware;

import org.apache.commons.io.FileUtils;
import org.testng.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.*;

import java.io.File;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jameskarasim on 6/25/17.
 */
public class BaseSeleniumTest {

    protected WebDriver driver = null;

    public enum OsType {
        WINDOWS, MAC, UNIX
    }

    PrintWriter writer = null;

    @BeforeMethod
    public void BeforeTest() throws Exception{
        //Before each test, launch a new Chrome Browser
        System.setProperty("webdriver.chrome.driver", "chromedrivermac"); // FOR MAC
        driver = new ChromeDriver();
        driver = new Augmenter().augment(driver); //for screenshots

        //open a new file for writing
        writer = new PrintWriter("index.htm", "UTF-8");
        writer.write("<html><head></head><body>");
    }

    @AfterMethod
    public void AfterTest(){
        //After each test, destroy the Chrome Browser
        driver.quit();
        driver = null;

        //close file report
        writer.write("</body></html>");
        writer.flush();
        writer.close();
    }

    protected String ScreenShot() {
        String fileName = "";

        try {
            // take the screen shot
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // get path to the working directory
            String workingDir = System.getProperty("user.dir");

            // generate a unique file name
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            Date date = new Date();
            String dateStamp = dateFormat.format(date);
            // String fileName = GetOsType().equals(OsType.WINDOWS)?workingDir.replace("\\","\\\\")+
            // "\\screenshot"+dateStamp+".png":workingDir + "/screenshot"+dateStamp+".png";
            fileName = GetOsType().equals(OsType.WINDOWS) ? workingDir + "\\screenshot" + dateStamp + ".png"
                    : workingDir + "/screenshot" + dateStamp + ".png";

            // save the file
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (Exception ex) {
            System.out.println("COMMON.SCREENSHOT FAILED:" + ex.getMessage());
        }

        System.out.println("SCREENSHOT:" + fileName);

        return fileName;
    }

    protected OsType GetOsType() throws Exception {
        // get the os
        String os = System.getProperty("os.name").toLowerCase();

        // set the system property for chromedriver depending on the os
        if (os.contains("win")) {
            return OsType.WINDOWS;
        } else if (os.contains("mac")) {
            return OsType.MAC;
        } else if (os.contains("nix") || os.contains("nux") || os.indexOf("aix") > 0) {
            return OsType.UNIX;
        } else {
            throw new Exception("UNSUPPORTED OPERATING SYSTEM:" + os);
        }

    }
}

