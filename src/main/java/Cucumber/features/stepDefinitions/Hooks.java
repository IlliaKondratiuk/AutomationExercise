package Cucumber.features.stepDefinitions;

import config.WebDriverConfig;
import config.Drivers;
import config.testConfigs.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import pages.CommonElementsPage;

//This class contains the setUp and tearDown methods for Cucumber features
public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    //Setting up the driver and the test context
    @Before
    public void setUp() {
        WebDriver driver = WebDriverConfig.create(Drivers.CHROME);
        context.setDriver(driver);
        System.out.println("Driver initialized.");
    }

    //Quitting the driver after the tests are done
    @After(order = 1)
    public void tearDown() {
        WebDriver driver = context.getDriver();
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit.");
        }
    }

    @After(value = "@AccountCleanup", order = 2) //only used in scenarios that include creating a new account and deleting it at the end
    public void deleteAccountAfterScenario() {
        CommonElementsPage commonElementsPage = new CommonElementsPage();

        commonElementsPage.clickDeleteAccButton();
    }
}
