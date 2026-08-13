package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pages.DashBoardPage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.ExcelDataSupplier;
import utilities.TestDataProvider;

/**
 * Data-driven Login Test.
 *
 * Expected results are maintained in Excel rather than hard-coded in the test
 * logic.
 */
public class LoginTestDataDriven extends BaseTest {

	@BeforeMethod
	public void ensureLoginPage() {
		getDriver().get(ConfigReader.getProperty("baseUrl"));
	}

	@Test(dataProvider = "excelDataProvider", dataProviderClass = TestDataProvider.class, description = "Verify Login functionality using Excel test data")
	@ExcelDataSupplier(fileName = "LoginData.xlsx", sheetName = "LoginTests")
	private void validateLoginResult(String testCase, String username, String password, String expectedResult) {

		logger.info("Executing test case: {}", testCase);
		logger.info("Username: {}", username);
		logger.info("Expected result: {}", expectedResult);

		// Verify Login Page
		String actualTitle = getDriver().getTitle();
		String expectedTitle = "EduManager - Login";

		Assert.assertEquals(actualTitle, expectedTitle, "Login page title mismatch!");

		// Perform Login
		LoginPage loginPage = new LoginPage();
		loginPage.signIn(username, password);

		logger.info("Login attempted for test case: {}", testCase);

		// Validate result from Excel
		validateExpectedResult(loginPage, expectedResult);

		logger.info("Test case '{}' completed successfully", testCase);
	}

	private void validateExpectedResult(LoginPage loginPage, String expectedResult) {

		if (expectedResult == null || expectedResult.trim().isEmpty()) {
			Assert.fail("ExpectedResult is missing in Excel for this test case");
		}

		switch (expectedResult.trim().toLowerCase()) {

		case "dashboard":

			DashBoardPage dashboardPage = new DashBoardPage();

			Assert.assertTrue(dashboardPage.isTopHeaderDisplayed(), "Dashboard top header is not displayed");

			logger.info("Dashboard verified successfully");
			break;

		case "user not found":

			String actualUserError = loginPage.getErrorMessage();

			Assert.assertEquals(actualUserError, expectedResult, "Login error message mismatch");

			logger.info("Expected error message verified: {}", actualUserError);
			break;

		case "invalid password":

			String actualPasswordError = loginPage.getErrorMessage();

			Assert.assertEquals(actualPasswordError, expectedResult, "Login error message mismatch");

			logger.info("Expected error message verified: {}", actualPasswordError);
			break;

		
		default:

			Assert.fail("Unsupported ExpectedResult in Excel: " + expectedResult);
		}
	}
}