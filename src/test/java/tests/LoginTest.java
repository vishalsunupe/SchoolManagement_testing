package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashBoardPage;
import pages.LoginPage;
import utilities.ConfigReader;

/**
 * Data-driven Login Test class leveraging centralized TestDataProvider.
 * Decoupled from Excel reading implementation details.
 */
public class LoginTest extends BaseTest {

    @Test(groups= {"smoke"},priority=1,description = "Verify Login functionality for valid data")
    public void validateSuccessfulLogin() {
        logger.info("Starting login test...");

        // Step 1: Verify Login Page Title
        String actualTitle = getDriver().getTitle();
        String expectedTitle = "EduManager - Login";
        logger.info("Verifying Login page title");
        Assert.assertEquals(actualTitle, expectedTitle, "Login page title mismatch!");
        logger.info("Login page title verified successfully");

        // Step 2: Perform Login
        LoginPage loginPage = new LoginPage();
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");
        loginPage.signIn(username, password);
        logger.info("Login attempted with provided credentials");

        // Step 3: Verify Dashboard
        DashBoardPage dashboardPage = new DashBoardPage();
        Assert.assertTrue(dashboardPage.isTopHeaderDisplayed(), "Dashboard top header not displayed!");
        logger.info("Dashboard top header verified successfully");

        logger.info("Login test completed successfully");
    }
    
    @Test(priority=1,description = "Verify Login functionality for invalid data")
    public void validateLoginWithInvalidUsername() {
        logger.info("Starting login test...");

        // Step 1: Verify Login Page Title
        String actualTitle = getDriver().getTitle();
        String expectedTitle = "EduManager - Login";
        logger.info("Verifying Login page title");
        Assert.assertEquals(actualTitle, expectedTitle, "Login page title mismatch!");
        logger.info("Login page title verified successfully");

        // Step 2: Perform Login
        LoginPage loginPage = new LoginPage();
        loginPage.signIn("adminhfj", " adminpass");
        logger.info("Login attempted with provided credentials");

        // Step 3: Verify error message
        Assert.assertEquals(loginPage.getErrorMessage(),"User not found",  "error message mismatch");
        logger.info("Login test completed successfully");
        System.out.println("java.version");
    }
}
