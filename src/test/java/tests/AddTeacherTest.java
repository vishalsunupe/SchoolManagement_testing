
package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.AddTeacherPage;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.TeacherPage;
import utilities.AlertUtils;
import utilities.ConfigReader;

public class AddTeacherTest extends BaseTest {

	private static final String LOGIN_PAGE_TITLE = "EduManager - Login";
	private static final String TEACHER_CREATED_MESSAGE = "Teacher created successfully!";

	@BeforeMethod
	public void loginFlow() {
		logger.info("Starting login flow...");

		// Verify Login Page
		String actualTitle = getDriver().getTitle();

		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE, "Login page title mismatch!");

		logger.info("Login page title verified successfully");

		// Perform Login
		LoginPage loginPage = new LoginPage();

		loginPage.signIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		logger.info("Login attempted with provided credentials");

		// Verify Dashboard
		DashBoardPage dashboardPage = new DashBoardPage();

		Assert.assertTrue(dashboardPage.isTopHeaderDisplayed(), "Dashboard top header not displayed!");

		logger.info("Dashboard top header verified successfully");
		logger.info("Logged in successfully");
	}

	@Test(description = "Verify Add Teacher functionality")
	public void addTeacherFunctionality() {

		logger.info("Starting Add Teacher functionality test");

		// Navigate to Teacher Page
		DashBoardPage dashboardPage = new DashBoardPage();

		dashboardPage.clickTotalTeachersCard();

		logger.info("Clicked on Total Teachers card");

		TeacherPage teacherPage = new TeacherPage();

		Assert.assertTrue(teacherPage.isPageHeaderDisplayed(), "Teacher page header not displayed!");

		logger.info("Teacher page header displayed successfully");

		// Navigate to Add Teacher Page
		teacherPage.clickAddTeacher();

		logger.info("Clicked on Add Teacher");

		AddTeacherPage addTeacherPage = new AddTeacherPage();

		Assert.assertTrue(addTeacherPage.isPageHeaderDisplayed(), "Add Teacher page header not displayed!");

		logger.info("Add Teacher page header displayed successfully");

		// Create Teacher
		addTeacherPage.createTeacher("TC023", "Min roy", "Female", "03/15/1994", "ME", "Mech", "8595654956",
				"mina1225@gmail.com", "57000", "Class_6X - A", "mina@2212853", true);

		logger.info("Teacher creation submitted successfully");

		// Verify Success Alert
		String actualSuccesMessage = addTeacherPage.getSuccessMessageForTeacherCreation();

		Assert.assertEquals(actualSuccesMessage, TEACHER_CREATED_MESSAGE, "Teacher creation  message mismatch!");

		logger.info("Teacher creation alert verified: {}", actualSuccesMessage);
		Assert.assertTrue(addTeacherPage.isPageHeaderDisplayed(), "Add Teacher page header not displayed!");
		
		logger.info("Add Teacher functionality test completed successfully");
	}
}
