package com.jaemzware.craigslist.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jameskarasim on 6/28/17.
 */
public class CraigslistResultsPage extends BasePageObject {

    @FindBy(xpath="//li[@class='result-row']/p/a")
    List<WebElement> searchResults;

    @FindBy(xpath="//a[@title='next page']")
    WebElement nextPageLink;

    @FindBy(xpath="//li[@class='result-row']/p/a[1]")
    WebElement firstResult;

    @FindBy(xpath="//button[@class='searchbtn']")
    WebElement searchButton;

    public CraigslistResultsPage(WebDriver driver){
        super(driver);

        (new WebDriverWait(driver,10)).until(ExpectedConditions.elementToBeClickable(searchButton));
    }

    public CraigslistResultsPage PrintOutResults(){

        for(WebElement result:searchResults){
            System.out.println(result.getText());
        }

        return this;
    }

    public CraigslistPostPage ClickFirstResult() throws Exception{
        if(searchResults.size() < 1){
            throw new Exception("THERE ARE NO SEARCH RESULTS");
        }

        firstResult.click();

        return new CraigslistPostPage(driver);
    }


    public CraigslistResultsPage ClickNextLink(){
        if(IsElementEnabled(nextPageLink)) {

            final String oldUrl = driver.getCurrentUrl();

            nextPageLink.click();

            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver d) {
                    return !driver.getCurrentUrl().equals(oldUrl);
                }
            });
        }

        return this;
    }

    public List<String> GetResultUrls(){
        List<String> allResultUrls = new ArrayList<String>();

        //check if max results was specified with -DaNumber
        int maxResults = 0;
        if(System.getProperty("aNumber")!=null){
            maxResults = Integer.parseInt(System.getProperty("aNumber"));
        }

        //collect all result urls
        do{
            for(WebElement w:searchResults){
                allResultUrls.add(w.getAttribute("href"));
            }

            //stop if we have more than a max results specified
            if(maxResults>0 && allResultUrls.size()>=maxResults){
                break;
            }

            ClickNextLink();
        }while(IsElementEnabled(nextPageLink));

        return allResultUrls;
    }

}
