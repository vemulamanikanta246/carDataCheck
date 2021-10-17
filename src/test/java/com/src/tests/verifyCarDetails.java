package com.src.tests;

import com.src.Utils.TextFileHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.src.pages.LandingPage;
import com.src.pages.CarDetails;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class verifyCarDetails {

    public WebDriver driver;

    @BeforeMethod
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        driver.get("https://cartaxcheck.co.uk/");
    }

    @Test
    public void test() throws IOException,InterruptedException
    {
        SoftAssert softassert = new SoftAssert();
        LandingPage lpage = new LandingPage(driver);
        TextFileHandler tfile = new TextFileHandler();
        HashMap<String, ArrayList<String>> expectedData = tfile.getExpecetedData(System.getProperty("user.dir")+"/src/test/resources/car_output.txt");
        //System.out.println(expectedData);
        for(String registrationNo : tfile.getCarRegistrations(System.getProperty("user.dir")+"/src/test/resources/car_input.txt"))
        {
            lpage.searchCar(registrationNo);
            CarDetails details = new CarDetails(driver);
            HashMap<String, String> outData = details.getVehicleDetails();
            try {
                if(!outData.get("Registration").equals(expectedData.get(registrationNo).get(0)))
                    System.out.println("Registration Didn't Match with outData for Input Registration No: " + registrationNo);
                if(!outData.get("Make").equals(expectedData.get(registrationNo).get(1)))
                    System.out.println("Make Didn't Match with outData for Input Registration No: " + registrationNo);
                if(!outData.get("Model").equals(expectedData.get(registrationNo).get(2)))
                    System.out.println("Model Didn't Match with outData for Input Registration No: " + registrationNo);
                if(!outData.get("Colour").equals(expectedData.get(registrationNo).get(3)))
                    System.out.println("Colour Didn't Match with outData for Input Registration No: " + registrationNo);
                if(!outData.get("Registered").contains(expectedData.get(registrationNo).get(4)))
                    System.out.println("Year Didn't Match with outData for Input Registration No: " + registrationNo);
                details.goToLandingPage();
            }
            catch(Exception e)
            {
                System.out.println("Exception while comparing Data for Registration No: "+registrationNo);
                if(expectedData.keySet().contains(registrationNo)) {
                    System.out.println("Expected Data is: " + expectedData.get(registrationNo));
                    System.out.println("Output Data is: " + outData);
                }
                else
                    System.out.println("Expected Data doesn't have given Input registration No : "+registrationNo);
                System.out.println("Continuing for Other Registrations");
                details.goToLandingPage();
            }
        }
    }

    @AfterMethod
    public void cleanUp()
    {
        driver.quit();
    }
}
