/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jaemzware.misc;

import com.jaemzware.BaseSeleniumTest;
import com.jaemzware.BrowserType;
import static com.jaemzware.Utilities.ScreenShot;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

/**
 *
 * @author jameskarasim
 * 
 * sample command line:
 * mvn -Dgroups=verifylogostest -DaLink="http://www.wilburellis.com" -DaLinkToFollow="wilburellis.com" -DaLogoXpath="//img[@class='logo']" test
 * mvn -Dgroups=verifylogostest -DaLink="https://www.seattlerules.com" -DaLinkToFollow="seattlerules.com" -DaLogoXpath="//a[@rel='home']" test
 */
public class VerifyLogosTest extends BaseSeleniumTest{
    
    @Test(groups={"verifylogostest"})
    public void VerifyLogos() throws Exception{
        //GET PARAMETERS
        String link = System.getProperty("aLink");
        String linkToFollow = System.getProperty("aLinkToFollow");
        String logoXpath = System.getProperty("aLogoXpath");
        String fileSS;
        
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
        
        //COMPILE LIST OF HREFS
        List<WebElement> linksToVisit = driver.findElements(By.xpath("//a[contains(@href,'"+linkToFollow+"')]"));
        List<String> urls = new ArrayList<>();
        urls.add(driver.getCurrentUrl());
        linksToVisit.forEach((we) -> {
            urls.add(we.getAttribute("href"));
        });
        
        //VISIT EACH HREF AND CHECK FOR LOGO AND PRINT A SCREENSHOT
        for(String url:urls) {
            writer.println("<hr />");
            driver.get(url);
            if(driver.findElements(By.xpath(logoXpath)).isEmpty()) {
                writer.println("MISSING LOGO "+logoXpath+" AT:<a href='"+url+"' target='_blank'>" + url + "</a><br />");
            }
            
            //PRINT BROWSER LOGS IF CHROME
            if(browserToStart == BrowserType.CHROME) {
                System.out.println("HERE");
                writer.println(ExtractJSLogs());
            }
            
            fileSS = ScreenShot(driver);
            
            writer.println("<a href='"+url+"' target='_blank'><img src='"+fileSS+"' /></a><br />");
        }
    }
    
}
