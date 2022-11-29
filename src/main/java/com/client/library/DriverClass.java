package com.client.library;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverClass implements Driver {

    private WebDriver driver;
    static String driverName;
    public static String resourcePath = System.getProperty("user.dir")+"/src/test/resources/";
    static {
        driverName = new PropertyReader().getProperty("browser");
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Override
    public WebDriver createDriver() {
        WebDriver driver = null;
        if (driverName.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            driver = new ChromeDriver(chromeOptions);
        }
        this.driver = driver;
        driver.manage().window().maximize();
        return driver;
    }
}
