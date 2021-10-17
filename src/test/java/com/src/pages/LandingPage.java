package com.src.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LandingPage {
    private WebDriver driver;
    public String searchBox = "vrm-input";
    public String freeCarCheck = "jsx-1164392954";
    WebDriverWait driverwait;
    public LandingPage(WebDriver driver)
    {
        this.driver = driver;
        driverwait = new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(20));
    }

    public void searchCar(String registrationNo)
    {

        this.driver.findElement(By.id(searchBox)).clear();
        this.driver.findElement(By.id(searchBox)).sendKeys(registrationNo);
        this.driver.findElement(By.className(freeCarCheck)).click();
    }


}
