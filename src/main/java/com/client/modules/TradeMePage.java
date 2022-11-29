package com.client.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TradeMePage {

    public WebDriver driver;

    @FindAll(
            @FindBy(xpath = "//li[contains(@class,'tm-homepage-search-header_')]/a")
    )
    public List<WebElement> lstLstMainMenuItem;

    @FindAll(
            @FindBy(name = "selectedMake")
    )
    public List<WebElement> lstSubMenuItem;

    @FindAll(
            @FindBy(css = "#makes tr td a")
    )
    public List<WebElement> lstUsedCarsMakes;

    @FindAll(
        @FindBy(xpath = "//ul[@id='ListViewList']/li[1]//div/a")
    )
    public List<WebElement> lstUsedCarsList;

    @FindAll(
            @FindBy(xpath = "//div[@id='AttributesDisplay_attributesSection']//li/div[1]")
    )
    public List<WebElement> lstUsedCarsDetails;

    public TradeMePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public WebElement selectMainMenu(String mainMenuName){
        return lstLstMainMenuItem
                .stream()
                .filter(items -> items.getText().equalsIgnoreCase(mainMenuName.toLowerCase()))
                .findFirst().get();
    }

    public void waitForElement(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(element));
    }


}
