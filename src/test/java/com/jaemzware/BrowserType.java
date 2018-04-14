package com.jaemzware;

import org.openqa.selenium.Platform;

/**
 *
 * @author jaemzware.org
 */
public enum BrowserType {
    //GRID: PLATFORM AND VERSION ARE ONLY USED WHEN USING GRID OR A CHROME EMULATION
    //NON-GRID: FOR NON-GRID, JUST SPECIFYING CHROME, FIREFOX, OR SAFARI WORKS ON MAC
    //FIREFOX: ONLY WORKS WITH GECKODRIVER NOW
    CHROME("chrome","",Platform.MAC),  //REQUIRES CHROMEDRIVER
    FIREFOX("firefox","",Platform.MAC); //REQUIRES GECKODRIVER
    
    public final String browserName;
    public final String version;
    public final Platform platform;
    
    BrowserType(final String browserName, final String version, final Platform platform)
    {
        this.browserName = browserName;
        this.version = version;
        this.platform = platform;
    }

}
