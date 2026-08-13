package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.DriverFactory;
import utilities.AlertUtils;
import utilities.DropdownUtils;
import utilities.ElementUtils;
import utilities.ScrollUtils;

/**
 * Page Object Model representation of the Add New Teacher Page.
 */
public class AddTeacherPage {

	private static final Logger logger = LogManager.getLogger(AddTeacherPage.class);
	private final WebDriver driver;

	// --- Header & General Locators ---
	private final By pageHeader = By.cssSelector(".card-header h3");
	private final By backToTeachersButton = By.cssSelector("a[href='/teachers']");
	private final By alertBox = By.id("alert");
	private final By teacherCreationsuccesMessage = By.xpath("//*[normalize-space()='Teacher created successfully!']");

	// --- Form Input Locators ---
	private final By employeeIdInput = By.id("employeeId");
	private final By teacherNameInput = By.id("teacherName");
	private final By genderDropdown = By.id("gender");
	private final By dateOfBirthInput = By.id("dateOfBirth");
	private final By qualificationInput = By.id("qualification");
	private final By specializationInput = By.id("specialization");
	private final By mobileNumberInput = By.id("mobileNumber");
	private final By emailInput = By.id("email");
	private final By salaryInput = By.id("salary");
	private final By classIdDropdown = By.id("classId");
	private final By passwordInput = By.id("password");
	private final By activeCheckbox = By.id("active");
	private final By saveTeacherButton = By.xpath("//button[normalize-space()='Save Teacher']");

	public AddTeacherPage() {
		this.driver = DriverFactory.getDriver();
	}

	// --- Header & General Action Methods ---

	public boolean isPageHeaderDisplayed() {
		logger.info("Checking if 'Add New Teacher' page header is displayed");
		return ElementUtils.isDisplayed(pageHeader);
	}

	public String getPageHeaderText() {
		logger.info("Getting 'Add New Teacher' page header text");
		return ElementUtils.getText(pageHeader);
	}

	public void clickBackToTeachers() {
		logger.info("Clicking 'Back to Teachers' button");
		ElementUtils.safeClick(backToTeachersButton);
	}

	public boolean isAlertDisplayed() {
		logger.info("Checking if alert box is displayed");
		return ElementUtils.isDisplayed(alertBox);
	}

	public String getAlertText() {
		logger.info("Getting alert message text");
		return ElementUtils.getText(alertBox);
	}

	// --- Form Action Methods ---

	public void enterEmployeeId(String employeeId) {
		logger.info("Entering Employee ID: '{}'", employeeId);
		ElementUtils.clearAndType(employeeIdInput, employeeId);
	}

	public void enterTeacherName(String teacherName) {
		logger.info("Entering Teacher Name: '{}'", teacherName);
		ElementUtils.clearAndType(teacherNameInput, teacherName);
	}

	public void selectGender(String gender) {
		logger.info("Selecting Gender: '{}'", gender);
		DropdownUtils.selectByVisibleText(genderDropdown, gender);
	}

	public String getSelectedGender() {
		logger.info("Getting selected Gender option");
		return DropdownUtils.getSelectedOption(genderDropdown);
	}

	public void enterDateOfBirth(String dateOfBirth) {
		logger.info("Entering Date of Birth: '{}'", dateOfBirth);
		ElementUtils.clearAndType(dateOfBirthInput, dateOfBirth);
	}

	public void enterQualification(String qualification) {
		logger.info("Entering Qualification: '{}'", qualification);
		ElementUtils.clearAndType(qualificationInput, qualification);
	}

	public void enterSpecialization(String specialization) {
		logger.info("Entering Specialization: '{}'", specialization);
		ElementUtils.clearAndType(specializationInput, specialization);
	}

	public void enterMobileNumber(String mobileNumber) {
		logger.info("Entering Mobile Number: '{}'", mobileNumber);
		ElementUtils.clearAndType(mobileNumberInput, mobileNumber);
	}

	public void enterEmail(String email) {
		logger.info("Entering Email: '{}'", email);
		ElementUtils.clearAndType(emailInput, email);
	}

	public void enterSalary(String salary) {
		logger.info("Entering Salary: '{}'", salary);
		ElementUtils.clearAndType(salaryInput, salary);
	}

	public void selectAssignedClassByVisibleText(String className) {
		logger.info("Selecting Assigned Class by visible text: '{}'", className);
		DropdownUtils.selectByVisibleText(classIdDropdown, className);
	}

	public void selectAssignedClassByValue(String classValue) {
		logger.info("Selecting Assigned Class by value: '{}'", classValue);
		DropdownUtils.selectByValue(classIdDropdown, classValue);
	}

	public String getSelectedAssignedClass() {
		logger.info("Getting selected Assigned Class option");
		return DropdownUtils.getSelectedOption(classIdDropdown);
	}

	public void enterPassword(String password) {
		logger.info("Entering Password: [PROTECTED]");
		ElementUtils.clearAndType(passwordInput, password);
	}

	public void setStatusActive(boolean isActive) {
		logger.info("Setting Active status checkbox to: {}", isActive);
		WebElement checkbox = driver.findElement(activeCheckbox);
		if (checkbox.isSelected() != isActive) {
			checkbox.click();
		}
	}

	public boolean isActiveCheckboxSelected() {
		logger.info("Checking if Active checkbox is selected");
		return driver.findElement(activeCheckbox).isSelected();
	}

	public void clickSaveTeacher() {
		logger.info("Scrolling to 'Save Teacher' button");
		ScrollUtils.scrollIntoView(saveTeacherButton);
		logger.info("Clicking 'Save Teacher' button");
		ElementUtils.safeClick(saveTeacherButton);
	}

	public void getAlertmessage() {
		String alertText = AlertUtils.getAlertText();

	}

	public String getSuccessMessageForTeacherCreation() {
		
	 return ElementUtils.getText(teacherCreationsuccesMessage);
	}

	/**
	 * Fills out the entire Add Teacher form and submits it.
	 */
	public void createTeacher(String employeeId, String name, String gender, String dob, String qualification,
			String specialization, String mobile, String email, String salary, String className, String password,
			boolean isActive) {
		logger.info("Creating new teacher: '{}' (ID: '{}')", name, employeeId);
		enterEmployeeId(employeeId);
		enterTeacherName(name);
		selectGender(gender);
		enterDateOfBirth(dob);
		enterQualification(qualification);
		enterSpecialization(specialization);
		enterMobileNumber(mobile);
		enterEmail(email);
		enterSalary(salary);
		if (className != null && !className.isEmpty()) {
			selectAssignedClassByVisibleText(className);
		}
		if (password != null && !password.isEmpty()) {
			enterPassword(password);
		}
		setStatusActive(isActive);
		clickSaveTeacher();
	}
}
