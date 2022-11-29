package com.client.stepdefinitions.ui;

import com.client.modules.*;
import com.client.processor.TradeMeTest;
import com.client.stepdefinitions.api.UsedCarsApiSteps;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CarsSearchSteps extends TradeMeTest {
    private static final Logger logger = LogManager.getLogger(UsedCarsApiSteps.class);
    LandingPage landingPage = new LandingPage(driver);

    @Given("^user opens TradeMe application$")
    public void loadApplication(){
        landingPage.loadUrl();
    }

    @When("^user navigated to the (.*) menu option$")
    public void navigateToMainMenu(String tabName){
        landingPage.selectMainMenu(tabName).getText();
        landingPage.selectMainMenu(tabName).click();
    }

    @And("^user navigates to the (.*) options$")
    public void navigateToDDMenu(String dropDown){
        MotorsPage motorsPage = new MotorsPage(driver);
        motorsPage.selectDDMenu(dropDown).click();
    }

    @Then("^user checks for the total number of (.*) options matches (.*)$")
    public void getTotalAvailableListing(String dropDown,int makeCount){
        MotorsPage motorsPage = new MotorsPage(driver);
        int totalMake = motorsPage.getCarDetailsList(dropDown);
        logger.info("Total car make listed in trademe",totalMake);
        assertThat("Total car make listed in trademe mismatch with requirement",totalMake-1,equalTo(makeCount));
    }

    @Then("^user logs the total number of cars for$")
    public void searchForCarBrand(DataTable dataTable){
        List<String> detailsList = dataTable.asList();
        MotorsPage motorsPage = new MotorsPage(driver);
        for (String data: detailsList) {
            boolean isMakeAvailable = motorsPage.checkForData(data);
            logger.info("brand - "+data+" - exist in the car make list :" ,isMakeAvailable);
        }
        motorsPage.selectOptions(detailsList.get(0));
        motorsPage.searchButton.click();
        CarsDetailsPage carsDetailsPage =new CarsDetailsPage(driver);
        logger.info("brand - "+detailsList.get(0)+" - has total :" + carsDetailsPage.carCountResult.getText().trim().replaceAll("Showing",""));
        for (String data: detailsList) {
            carsDetailsPage.makeButton.click();
            carsDetailsPage.makeInputText.clear();
            carsDetailsPage.makeInputText.sendKeys(data);
            if(carsDetailsPage.makeRadioBtn.getText().contains(data)){
                carsDetailsPage.makeRadioBtn.click();
                carsDetailsPage.waitForElement(carsDetailsPage.viewResultBtn);
                carsDetailsPage.viewResultBtn.click();
                logger.info("brand - "+data+" - has total :" + carsDetailsPage.carCountResult.getText().trim().replaceAll("Showing",""));
            }
        }
    }

}
