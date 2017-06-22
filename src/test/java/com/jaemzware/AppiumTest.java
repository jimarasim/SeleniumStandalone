package com.jaemzware;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.appium.java_client.remote.MobileCapabilityType;


import java.net.URL;

/**
 * Created by jameskarasim on 6/22/17.
 */
public class AppiumTest {

    public static WebDriver driver = null;

    @Before
    public void BeforeTest() throws Exception{
        //Before each test, launch a new Chrome Browser
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "10.2");
        capabilities.setCapability("deviceName", "iPhone Simulator");
        capabilities.setCapability("app", "/Users/jameskarasim/Desktop/Digitease.app"); //xcode -> clean -> build -> products folder -> show in finder -> move .app to desktop

        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }


    @Test
    public void AppiumTest1() throws Exception {
        Assert.assertTrue(driver.findElements(By.id("phonenumber")).size()==1);

        WebElement phoneNumber = driver.findElement(By.id("phonenumber"));
        phoneNumber.sendKeys("2063727265");

        Assert.assertTrue(driver.findElements(By.id("phonewordpicker")).size()==1);
        Assert.assertTrue(driver.findElements(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[1]")).size()==1);
        Assert.assertTrue(driver.findElements(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[2]")).size()==1);
        Assert.assertTrue(driver.findElements(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[3]")).size()==1);

        WebElement pickerWheel1 = driver.findElement(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[1]"));
        WebElement pickerWheel2 = driver.findElement(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[2]"));
        WebElement pickerWheel3 = driver.findElement(By.xpath("//XCUIElementTypePicker[@name='phonewordpicker']/XCUIElementTypePickerWheel[3]"));

        pickerWheel1.sendKeys("b0m");
        pickerWheel2.sendKeys("esb");
        pickerWheel3.sendKeys("rank");
    }

    @After
    public void AfterTest(){
        driver.quit();
        driver = null;
    }

}
