package com.src.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class CarDetails {

    private WebDriver driver;
    public WebDriverWait driverwait;
    public String vehicleDetails = "jsx-3807182525";
    public String eachRow = "jsx-3517272246";
    public String Key = "jsx-1051216822";
    public String Value = "jsx-3496807389";
    public String TryAgain = "jsx-2611738455";
    public CarDetails(WebDriver driver)
    {
        this.driver = driver;
        driverwait = new WebDriverWait(this.driver, TimeUnit.SECONDS.toSeconds(20));
        driverwait.until(ExpectedConditions.visibilityOfElementLocated(By.className(eachRow)));
    }
     public HashMap<String,String> getVehicleDetails() throws InterruptedException
     {
         HashMap<String,String> vdetails = new HashMap<String,String>();
         WebElement vehicleData = this.driver.findElements(By.className(vehicleDetails)).get(0);
         Thread.sleep(1000);
         for(WebElement element : vehicleData.findElements(By.className(eachRow)))
         {
             driverwait.until(ExpectedConditions.visibilityOfElementLocated(By.className(Value)));
             vdetails.put(element.findElement(By.className(Key)).getText(),element.findElement(By.className(Value)).getText());
         }
        return vdetails;
     }

     public void goToLandingPage()
     {
         try {
             driver.findElement(By.tagName("img")).click();
         }
         catch (ElementClickInterceptedException e)
         {
             driver.findElement(By.className(TryAgain)).click();
         }
     }
}
