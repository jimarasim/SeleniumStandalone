package com.jaemzware;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class PinterestHomePage extends BasePageObject{

    @FindBy(xpath="//div[contains(text(),'Home')]")
    private WebElement HomeButton;

    @FindBy(css="svg._mh")
    private WebElement pButton;

    @FindBy(xpath="//div[contains(text(),'Explore')]")
    private WebElement exploreButton;

    @FindBy(css="div[aria-label=Saved]")
    private WebElement profileButton;

    @FindBy(css="div[data-grid-item=true]")
    private WebElement aResult;

    @FindBy(css="div[data-grid-item=true]:nth-child(5)")
    private WebElement fifthResult;

    @FindBy(css="div[data-grid-item=true]")
    private List<WebElement> resultsList;

    @FindBy(css="div._0._25._2p._2c._2i._3h._56._jz._s._3o > div.article")
    private List<WebElement> articleList;

    @FindBy(css="input[name=q]")
    private WebElement searchTextbox;

    @FindBy(css="div.improvementsWrapper")
    private WebElement searchImprovementsWrapper;

    @FindBy(css="div.improvementsWrapper > div > div:nth-child(1) > div > div > div:nth-child(1) > a")
    private WebElement firstSearchImprovement;

    @FindBy(css="button[aria-label='Add Pin'']")
    private WebElement addPinButton;

    @FindBy(css="button[aria-label='More']")
    private WebElement moreButton;

    @FindBy(xpath="//a[contains(text(),'Privacy')]")
    private WebElement privacyLink;

    public String URL = "https://pinterest.com";

    public PinterestHomePage(WebDriver driver){
        super(driver);
    }

    public WebElement getSearchTextbox(){
        return searchTextbox;
    }

    public int ResultsListCount(){
        return resultsList.size();
    }

    public void SearchForTerm(String term){
        searchTextbox.clear();
        searchTextbox.sendKeys(term);
        searchTextbox.sendKeys(Keys.ENTER);

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(firstSearchImprovement));
    }

    public PinterestViewerPage pickRandomResult(){
        Random rand = new Random();

        int randomNum = rand.nextInt((resultsList.size() - 1) + 1) + 1;

        resultsList.get(randomNum).click();

        PinterestViewerPage viewerPage = new PinterestViewerPage(driver);

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(viewerPage.getSaveButton()));

        return viewerPage;
    }

    public PinterestProfilePage clickProfileButton(){
        PinterestProfilePage profilePage = new PinterestProfilePage(driver);

        profileButton.click();

        (new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(profilePage.getSettingsButton()));

        return profilePage;
    }
}
