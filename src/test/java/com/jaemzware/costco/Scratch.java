package com.jaemzware.costco;

import com.jaemzware.BaseSeleniumTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Scratch extends BaseSeleniumTest {

    @Test
    public void selectFourCompareItems() throws Exception{
        driver.get("https://www.costcobusinessdelivery.com/CatalogSearch?keyword=cups");
        List<WebElement> items = driver.findElements(By.xpath("//input[@id='in_Stock' and not(@value='0')]/../.."));

        Assert.assertTrue(items.size()>=4, "Could not find four in stock items to compare");

        for(int i=0;i<items.size();i++) {
            items.get(i).findElement(By.xpath("//input[@name='compare-product']")).click();
        }
    }


}
