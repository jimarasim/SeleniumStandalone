/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaemzware.misc;

import com.jaemzware.BaseSeleniumTest;
import static com.jaemzware.Utilities.ScreenShot;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 *
 * @author jameskarasim
 */
public class VerifyLogosTest extends BaseSeleniumTest{
    
    @Test(groups={"verifylogostest"})
    public void VerifyLogos() throws Exception{
        //GET PARAMETERS
        String link = System.getProperty("aLink");
        String linkToFollow = System.getProperty("aLinkToFollow");
        String logoXpath = System.getProperty("aLogoXpath");
        
        if(link == null || link.isEmpty()) {
            throw new Exception("PLEASE SPECIFY -DaLink");
        }
        
        if(linkToFollow == null) {
            throw new Exception("PLEASE SPECIFY -DaLinkToFollow");
        }
        
        if(logoXpath == null || logoXpath.isEmpty()) {
            throw new Exception("PLEASE SPECIFY -DaLogoXpath");
        }
        
        //NAVIGATE TO PAGE
        driver.get(link);
        
        System.out.println("HERE");
        
        //COMPILE LIST OF HREFS
        List<WebElement> linksToVisit = driver.findElements(By.xpath("//a[contains(@href,'"+linkToFollow+"')]"));
        List<String> urls = new ArrayList<String>();
        for(WebElement we:linksToVisit) {
            urls.add(we.getAttribute("href"));
        }
        
        //VISIT EACH HREF AND CHECK FOR LOGO AND PRINT A SCREENSHOT
        for(String url:urls) {
            driver.get(url);
            if(driver.findElements(By.xpath(logoXpath)).size() == 0) {
                writer.println("MISSING LOGO AT:" + url + "<br />");
            }
            
            String fileSS = ScreenShot(driver);
            
            writer.println("<img src='"+fileSS+"' /><br />");
        }
    }
    
}
