// This is "Test Case 4: Logout User" from the Automation Exercise page at automationexercise.com
// Steps 1(Launch browser) and 2(Navigate to url) have been skipped as common sense
// Using JUnit 4.13.2, designing with Page Object Model and generating an Allure(2.32.2) report
// The user data is parsed from src\resources\validlogin.properties

import config.PageNames;
import config.testConfigs.BaseTest;
import config.testConfigs.CriticalTests;
import io.qameta.allure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pages.LoginPage;
import pages.MainPage;

import java.util.ResourceBundle;

@Category(CriticalTests.class)
public class Exercise4LogoutTest extends BaseTest {

    private static MainPage mainPage;
    private static LoginPage loginPage;

    private static ResourceBundle values;

    @Before
    public void begin() {
        driver.get(PageNames.MAIN.getUrl());

        mainPage = new MainPage(driver);
        mainPage.handleCookies();

        values = ResourceBundle.getBundle("validlogin");
    }

    @Test
    @Epic("User management")
    @Feature("Login functionality")
    @Story("As a user, I can log out of my account to end my session securely.")
    @Severity(SeverityLevel.CRITICAL)
    public void Ex4LogoutTest() {
        //Step 1. Check if the logo is visible.
        Assert.assertTrue("Step 1: The logo is not visible", mainPage.logoIsVisible());

        //Step 2. Click on 'Signup / Login' button
        mainPage.clickSignupLogin();

        //Step 3. Verify 'Login to your account' is visible
        loginPage = new LoginPage(driver);
        Assert.assertTrue("Step 3: The 'Login to your account' label is not visible",
        loginPage.isLoginTextVisible());

        //Step 4. Enter valid/invalid email address and password
        loginPage.enterEmailPassLogin(values.getString("email"), values.getString("pass"));

        //Step 5. Click 'login' button
        loginPage.clickLoginButton();

        //Step 6. Verify that 'Logged in as username' is visible
        mainPage.initUsername(values.getString("username"));
        Assert.assertTrue("Step 6: 'Logged in as username' is not visible or the username is incorrect",
                mainPage.checkLoggedInAsLabelNav());

        //Step 7. Click 'Logout' button
        mainPage.clickLogOut();

        //Step 8. Verify that user is navigated to login page
        Assert.assertEquals("Step 8: The user is not navigated to the login page after logging out.",
                "https://automationexercise.com/login", driver.getCurrentUrl());
    }
}
