package com.client.stepdefinitions.api;

import com.client.processor.ApiService;
import com.client.processor.DataHandler;
import com.client.processor.DataHandlerThread;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class UsedCarsApiSteps {

    private static final Logger logger = LogManager.getLogger(UsedCarsApiSteps.class);
    private JSONObject jsonObject;
    ApiService apiService ;

    @And("Business details information for tradeMe")
    public void getBusinessDetails(){
        jsonObject = DataHandler.readJson();
        DataHandlerThread.setCurrentDataHandler(jsonObject);
    }

    @When("^the user searches for the data in (.*)$")
    public void searchListOfUsedCars(String apiName){
        apiService = new ApiService();
        apiService.runServiceCall(apiName);
    }

    @Then("there is total {int} in the TradeMe UsedCars category")
    public void getListofUsedCars(Integer noofitems){
       JsonPath jsonPath =  apiService.getServiceResponse();
       List<String> usedCarsNameList = jsonPath.getList("Subcategories.Name");
       logger.info("There listing in the trade me used cars list", usedCarsNameList.size());
       assertThat("There is 0 listing in the trade me cars list", usedCarsNameList.size(), equalTo(noofitems));
    }
}
