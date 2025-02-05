package config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;

public class Base {

    public static WebDriver driver;

    //Initializing the driver before executing each test case
    @BeforeClass
    public static void base_before(){
        driver = Config.create(Drivers.CHROME);
    }

    //Closing the driver after executing each test case
    @AfterClass
    public static void base_after(){
        driver.quit();
    }

}