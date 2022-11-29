package com.client.modules;

import com.client.library.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LandingPage extends TradeMePage{

    public LandingPage(WebDriver driver){super(driver);}

    public void loadUrl(){
        driver.get(new PropertyReader().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        assertThat("TradeMe home page is not loaded",driver.getTitle(),
                equalTo("Buy & Sell on NZ's #1 Auction & Classifieds Site | Trade Me"));
    }

}
