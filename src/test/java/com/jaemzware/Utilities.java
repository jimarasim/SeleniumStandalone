/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaemzware;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author jameskarasim
 */
public class Utilities {
    
    /**
     * DateStamp
     * 
     * Generates a string representing the current date to the nearest millisecond.
     * 
     * @return current date string.
     */
    public static String DateStamp() {
        // generate a unique file name with current date down to milliseconds
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    /**
     * GetOsType
     * 
     * Detects the os of the current platform.
     * 
     * @return OsType enum of the current platform.
     * @throws Exception 
     */
    public static OsType GetOsType() throws Exception {
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
    
     /**
     * ScreenShot
     * 
     * Takes a screenshot of the current page and stores to a datestamped .png file.
     * 
     * @return Filename without the full path.
     */
    public static String ScreenShot(WebDriver driver) {
        String fileName = "";
        String path = "";

        try {
            // take the screen shot
            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // get path to the working directory
            String workingDir = System.getProperty("user.dir");
            
            path = GetOsType().equals(OsType.WINDOWS)?workingDir.replace("\\","\\\\")+"\\":workingDir + "/";
            fileName = "screenshot"+DateStamp()+".png";

            // save the file
            FileUtils.copyFile(scrFile, new File(path + fileName));
            
        } catch (Exception ex) {
            System.out.println("COMMON.SCREENSHOT FAILED:" + ex.getMessage());
        }

        return fileName;
    }
    
    /**
     * GetCommandLineCredentials
     * 
     * This method retrieves the username and password from the commandline, and throws an exception if either are not specified.
     * 
     * @return HashMap with a key for username and a key for password
     * @throws Exception 
     */
    public static HashMap<String, String> GetCommandlineCredentials() throws Exception {
        HashMap<String, String> credentials = new HashMap<String, String>();
        
        String username = System.getProperty("username");
        String password = System.getProperty("password");
        
        if (username == null || username.isEmpty()) {
            throw new Exception ("-Dusername PARAMETER REQUIRED FOR THIS TEST");
        }
        
        if (password == null || password.isEmpty()) {
            throw new Exception ("-Dpassword PARAMETER REQUIRED FOR THIS TEST");
        }
        
        credentials.put("username", username);
        credentials.put("password", password);
        
        return credentials;
    }
}
