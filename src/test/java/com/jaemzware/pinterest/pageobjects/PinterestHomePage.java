package com.jaemzware.pinterest.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class PinterestHomePage extends BasePageObject {

    @FindBy(xpath="//div[contains(text(),'Home')]")
    private WebElement HomeButton;

    @FindBy(css="svg._mh")
    private WebElement pButton;

    @FindBy(xpath="//div[contains(text(),'Explore')]")
    private WebElement exploreButton;

    @FindBy(css="div[aria-label=Saved]")
    private WebElement profileButton;

    @FindBy(css="button[aria-label='Add Pin']")
    private WebElement addPinButton;

    @FindBy(css="button[aria-label='More']")
    private WebElement moreButton;

    @FindBy(css="a.nonEuPrivacyPolicy")
    private WebElement privacyLink;

    @FindBy(xpath="//h3[contains(text(),'Explore')]")
    private WebElement exploreHeading;

    @FindBy(css="div[data-grid-item=true]")
    private WebElement aResult;

    @FindBy(css="div[data-grid-item=true]:nth-child(5)")
    private WebElement fifthResult;

    @FindBy(css="div[data-grid-item=true]")
    private List<WebElement> resultsList;

    @FindBy(css="div.article")
    private List<WebElement> articleList;

    @FindBy(css="input[name=q]")
    private WebElement searchTextbox;

    @FindBy(css="div.improvementsWrapper")
    private WebElement searchImprovementsWrapper;

    @FindBy(css="div.improvementsWrapper > div > div:nth-child(1) > div > div > div:nth-child(1) > a")
    private WebElement firstSearchImprovement;

    public String URL = "https://pinterest.com";

    public PinterestHomePage(WebDriver driver){
        super(driver);
    }

    public boolean IsHomeButtonEnabled(){return IsElementEnabled(HomeButton);}
    public boolean IsPButtonEnabled(){return IsElementEnabled(pButton);}
    public boolean IsExploreButtonEnabled(){return IsElementEnabled(exploreButton);}
    public boolean IsProfileButtonEnabled(){return IsElementEnabled(profileButton);}
    public boolean IsAddPinButtonEnabled(){return IsElementEnabled(addPinButton);}
    public boolean IsMoreButtonEnabled(){return IsElementEnabled(moreButton);}
    public boolean IsSearchImprovementsWrapperEnabled(){return IsElementEnabled(searchImprovementsWrapper);}
    public boolean IsExploreHeadingEnabled(){return IsElementEnabled(exploreHeading);}

    public int resultsListCount(){
        return resultsList.size();
    }

    public int articleListCount(){
        return articleList.size();
    }

    public void clickExploreButton(){
        exploreButton.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(aResult));
    }

    public PinterestProfilePage clickProfileButton(){
        PinterestProfilePage profilePage = new PinterestProfilePage(driver);

        profileButton.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(profilePage.getFirstResult()));

        return profilePage;
    }

    public void searchForTerm(String term){
        searchTextbox.clear();
        searchTextbox.sendKeys(term);
        searchTextbox.sendKeys(Keys.ENTER);

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(firstSearchImprovement));
    }

    public PinterestViewerPage pickRandomResult(){
        Random rand = new Random();

        int randomNum = rand.nextInt((4 - 1) + 1) + 1;

        try {
            resultsList.get(randomNum).click();
        }
        catch(WebDriverException wdx){
            //scroll down a little bit to click below the bottom of the visible page
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500)");
            resultsList.get(randomNum).click();
        }

        PinterestViewerPage viewerPage = new PinterestViewerPage(driver);

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(viewerPage.getSaveButton()));

        return viewerPage;
    }
}
