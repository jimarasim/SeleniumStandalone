package com.jaemzware.craigslist.pageobjects;

import com.jaemzware.BasePageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

public class CraigslistClassifiedWizardPage extends BasePageObject {
    @FindBy(name="id")
    List<WebElement> typeRadioButton;

    @FindBy(name="go")
    WebElement continueButton;

    @FindBy(css="input[value=fso]")
    WebElement forSaleByOwnerRadioButton;

    @FindBy(css="input[value='145']")
    WebElement autoRadioButton;

    @FindBy(xpath="//label[contains(text(),'seattle')]/input")
    WebElement seattleRadioButton;

    @FindBy(id="PostingTitle")
    WebElement postingTitleInput;

    @FindBy(name="price")
    WebElement postingPriceInput;

    @FindBy(id="GeographicArea")
    WebElement specificLocationInput;

    @FindBy(id="postal_code")
    WebElement postalCodeInput;

    @FindBy(id="PostingBody")
    WebElement postingBodyInput;

    @FindBy(id="auto_year")
    WebElement autoYearSelect;

    @FindBy(name="auto_make_model")
    WebElement makeModelInput;

    @FindBy(id="auto_vin")
    WebElement autoVinInput;

    @FindBy(id="auto_miles")
    WebElement autoMilesInput;

    @FindBy(id="language")
    WebElement languageSelect;

    @FindBy(id="condition")
    WebElement conditionSelect;

    @FindBy(id="auto_cylinders")
    WebElement cylindersSelect;

    @FindBy(id="auto_drivetrain")
    WebElement driveTrainSelect;

    @FindBy(id="auto_fuel_type")
    WebElement fuelTypeSelect;

    @FindBy(id="auto_paint")
    WebElement paintSelect;

    @FindBy(id="auto_size")
    WebElement sizeSelect;

    @FindBy(id="auto_title_status")
    WebElement titleStatusSelect;

    @FindBy(id="auto_transmission")
    WebElement transmissionSelect;

    @FindBy(id="auto_bodytype")
    WebElement bodyTypeSelect;

    @FindBy(css="span.err")
    WebElement errorText;

    public WebElement getErrorTextElement() {
        return errorText;
    }

    public CraigslistClassifiedWizardPage(WebDriver driver){
            super(driver);
        }

    public CraigslistClassifiedWizardPage pickRandomOptionAndContinue() {
        int numTypes = typeRadioButton.size();

        Random rand = new Random();

        int randomListNum = (rand.nextInt((numTypes - 1) + 1) + 1) - 1;

        typeRadioButton.get(randomListNum).click();

        continueButton.click();

        return this;
    }

    public WebElement getContinueButton() {
        return continueButton;
    }

    public CraigslistClassifiedWizardPage pickForSaleByOwnerAndContinue() {
        forSaleByOwnerRadioButton.click();
        continueButton.click();

        return this;
    }

    public CraigslistClassifiedWizardPage pickAutoAndContinue() {
        autoRadioButton.click();
        continueButton.click();

        return this;
    }

    public CraigslistClassifiedWizardPage pickSeattle() {
        seattleRadioButton.click();

        (new WebDriverWait(driver,10000)).until(ExpectedConditions.elementToBeClickable(postingTitleInput));

        return this;
    }

    public CraigslistClassifiedWizardPage fillOutPartialAutoPostingAndContinue() {
        postingTitleInput.sendKeys("Honda Civic 2010");
        postingPriceInput.sendKeys("12000");
        specificLocationInput.sendKeys("West Seattle");
        postalCodeInput.sendKeys("98126");
        postingBodyInput.sendKeys("This is the best car in the world. I really don't want to sell it, but" +
                "I must. So please buy it. It has a CD player and aux cord input. It only has 4 cylinders, so doesn't " +
                "go too fast, but if you drive stick, you know how to get speed when you need it.");
        (new Select(this.autoYearSelect)).selectByValue("2010");
        makeModelInput.sendKeys("Honda Civic");
        autoVinInput.sendKeys("V4N3KJ34JBHGJKSKD");
        autoMilesInput.sendKeys("40000");
        (new Select(this.languageSelect)).selectByValue("5");
        (new Select(this.conditionSelect)).selectByValue("40");
        (new Select(this.cylindersSelect)).selectByValue("2");
        (new Select(this.driveTrainSelect)).selectByValue("1");
        (new Select(this.fuelTypeSelect)).selectByValue("1");
        (new Select(this.paintSelect)).selectByValue("1");
        (new Select(this.sizeSelect)).selectByValue("1");
        (new Select(this.titleStatusSelect)).selectByValue("1");
        (new Select(this.transmissionSelect)).selectByValue("1");
        (new Select(this.bodyTypeSelect)).selectByValue("8");

        continueButton.click();

        return this;
    }
}
