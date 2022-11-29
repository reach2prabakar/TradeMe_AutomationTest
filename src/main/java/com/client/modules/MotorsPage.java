package com.client.modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MotorsPage extends TradeMePage {

    @FindAll(
            @FindBy(xpath = "//*[contains(@class,'tm-motors-search-bar__dropdown')]/label/following-sibling::div/select")
    )
    public List<WebElement> motorDDlist;

    @FindBy(xpath = "//button[text()=' Search ']")
    public WebElement searchButton;

    public MotorsPage(WebDriver driver){
        super(driver);
        waitForPage();
    }

    public void waitForPage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(lstSubMenuItem.get(0)));
        assertThat("UsedCars page is not loaded",driver.getTitle(),equalTo("Cars And Vehicles For Sale | Trade Me Motors"));
    }

    public WebElement selectDDMenu(String dropDown){
        return motorDDlist
                .stream()
                .filter(items -> items.getText().toLowerCase().trim().contains(dropDown.toLowerCase()))
                .findFirst().get();
    }

    public int getCarDetailsList(String dropDown) {
        WebElement element =  motorDDlist
                            .stream()
                            .filter(items -> items.getText().toLowerCase().trim().contains(dropDown.toLowerCase()))
                            .findFirst().get();
        Select select = new Select(element);
        return select.getOptions().size();
    }

    public boolean checkForData(String data) {
        WebElement element =  motorDDlist
                .stream()
                .filter(items -> items.getText().toLowerCase().trim().contains(data.toLowerCase()))
                .findFirst().get();
        Select select = new Select(element);
        List<WebElement> selectedOptions = select.getOptions();
        for (WebElement elem:selectedOptions) {
           if(elem.getText().contains(data)){
               return true;
           }
        }
        return false;
    }

    public void selectOptions(String data) {
        WebElement element =  motorDDlist
                .stream()
                .filter(items -> items.getText().toLowerCase().trim().contains(data.toLowerCase()))
                .findFirst().get();
        Select select = new Select(element);
        select.selectByValue(data);
    }

}
