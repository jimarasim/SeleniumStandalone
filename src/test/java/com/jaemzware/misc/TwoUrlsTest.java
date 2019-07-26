package com.jaemzware.misc;

import com.jaemzware.BaseSeleniumTest;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class TwoUrlsTest extends BaseSeleniumTest {

    private String url1 = "https://google.com";
    private String url2 = "https://yahoo.com";

    @Test
    public void twoUrlsTest() {
        driver.get(url1);

        ((JavascriptExecutor)driver).executeScript("window.open();");

        String tab1 = driver.getWindowHandle();
        String tab2 = (String)driver.getWindowHandles().toArray()[1];

        driver.switchTo().window(tab2);
        driver.get(url2);
    }

}
