package com.client.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarsDetailsPage extends TradeMePage {

    @FindBy(xpath = "//div[@class='tm-motors-search-results__results-container']")
    public WebElement lstCarsDetails;

    @FindBy(xpath = "//div[@class='tm-motors-search-results__search-header']//h3")
    public WebElement carCountResult;

    @FindBy(xpath = "//button[contains(text(),'Make')]")
    public WebElement makeButton;

    @FindBy(xpath = "//input[@placeholder='Search']")
    public WebElement makeInputText;

    @FindBy(xpath = "//span[@class='o-radio__label-text']")
    public WebElement makeRadioBtn;

    @FindBy(xpath = "//button[contains(text(),'View')]")
    public WebElement viewResultBtn;


    public CarsDetailsPage(WebDriver driver){
        super(driver);
        waitForPage();
    }

    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(lstCarsDetails));
        assertThat("UsedCars page is not loaded",driver.getTitle(), containsString(" | Trade Me"));
    }

}
