package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AddStudentPage;
import pages.DashBoardPage;
import pages.LoginPage;
import pages.StudentPage;
import utilities.ConfigReader;
import utilities.FakerUtil;
import utilities.FakerUtils;

public class AddStudentTest extends BaseTest {

	private static final String LOGIN_PAGE_TITLE = "EduManager - Login";
	private static final String STUDENT_SUCCESS_MESSAGE = "Student created successfully!";

	@BeforeMethod(alwaysRun = true)
	public void loginFlow() {
		logger.info("Starting login flow for Add Student test");

		String actualTitle = getDriver().getTitle();
		Assert.assertEquals(actualTitle, LOGIN_PAGE_TITLE, "Login page title mismatch!");

		LoginPage loginPage = new LoginPage();
		loginPage.signIn(ConfigReader.getProperty("username"), ConfigReader.getProperty("password"));

		DashBoardPage dashboardPage = new DashBoardPage();
		Assert.assertTrue(dashboardPage.isTopHeaderDisplayed(), "Dashboard top header not displayed after login!");
	}

	@Test(groups= {"smoke"},priority=1,description = "Verify Add Student functionality")
	public void addStudentFunctionality() {
		logger.info("Starting Add Student functionality test");

		DashBoardPage dashboardPage = new DashBoardPage();
		dashboardPage.clickTotalStudentsCard();

		StudentPage studentPage = new StudentPage();
		Assert.assertTrue(studentPage.isPageHeaderDisplayed(), "Students page header not displayed!");

		studentPage.clickAddStudent();

		AddStudentPage addStudentPage = new AddStudentPage();
		Assert.assertTrue(addStudentPage.isPageHeaderDisplayed(), "Add Student page header not displayed!");

		String admissionNo = FakerUtils.getAdmissionNumber();
		String studentName = FakerUtils.getFullName();
		String gender = FakerUtils.getGender();
		String dob = FakerUtils.getDateOfBirth(14, 30);
		String street = FakerUtils.getStreetAddress();
		String city = FakerUtils.getCity();
		String state = FakerUtils.getState();
		String zip = FakerUtils.getZipCode();
		String phone = FakerUtils.getTenDigitPhoneNumber();
		String email = FakerUtils.getEmailAddress();
		String password = FakerUtils.getPassword(8, true);
		String parentName = FakerUtils.getParentName();
		String parentPhone = FakerUtils.getTenDigitPhoneNumber();
	    String classofStudent=FakerUtil.getClassOfStudent();
		String section=FakerUtil.getSection();
     
		addStudentPage.addStudent(admissionNo, studentName, gender, dob, street, city, state, zip, phone, email, password, parentName, parentPhone, classofStudent, section, true);

		Assert.assertEquals(addStudentPage.getSuccessMessageForStudentCreation(), STUDENT_SUCCESS_MESSAGE,
				"Student creation  message mismatch!");

		Assert.assertTrue(studentPage.isPageHeaderDisplayed(), "Students page header was not displayed after save!");
		Assert.assertTrue(studentPage.isStudentPresentByName(studentName), "Created student not found in list!");
		Assert.assertTrue(studentPage.isStudentPresentByName(studentName),"Student not found");
	}
}
