package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import utilities.DropdownUtils;
import utilities.ElementUtils;
import utilities.ScrollUtils;

/**
 * Page Object Model representation of the Add Student Page.
 */
public class AddStudentPage {

    private static final Logger logger = LogManager.getLogger(AddStudentPage.class);
    private final WebDriver driver;

    // --- Page Locators ---
    private final By sidebar = By.id("sidebar");
    private final By sidebarBrand = By.cssSelector("#sidebar .sidebar-header h2");
    private final By sidebarSubtitle = By.cssSelector("#sidebar .sidebar-header p");
    private final By dashboardLink = By.cssSelector("#sidebar a[href='/dashboard']");
    private final By classesLink = By.cssSelector("#sidebar a[href='/classes']");
    private final By subjectsLink = By.cssSelector("#sidebar a[href='/subjects']");
    private final By studentsLink = By.cssSelector("#sidebar a[href='/students']");
    private final By teachersLink = By.cssSelector("#sidebar a[href='/teachers']");
    private final By feesLink = By.cssSelector("#sidebar a[href='/fees']");
    private final By attendanceLink = By.cssSelector("#sidebar a[href='/attendance']");
    private final By examsLink = By.cssSelector("#sidebar a[href='/exams']");
    private final By announcementsLink = By.cssSelector("#sidebar a[href='/announcements']");
    private final By reportCardLink = By.cssSelector("#sidebar a[href='/report-card']");
    private final By profileLink = By.cssSelector("#sidebar a[href='/profile']");
    private final By sidebarLogoutLink = By.cssSelector("#sidebar a[href='javascript:logout()'], #sidebar a[onclick='logout()']");

    private final By topHeader = By.cssSelector(".top-header");
    private final By pageHeader = By.cssSelector(".top-header h1, .card-header h3");
    private final By darkModeToggleBtn = By.cssSelector(".dark-mode-toggle");
    private final By sunIcon = By.cssSelector(".toggle-sun");
    private final By moonIcon = By.cssSelector(".toggle-moon");
    private final By welcomeText = By.xpath("//div[contains(@class,'header-actions')]/span");
    private final By loggedInUser = By.xpath("//div[contains(@class,'header-actions')]//strong");
    private final By headerLogoutButton = By.xpath("//button[contains(@onclick,'logout') or normalize-space()='Logout']");

    private final By admissionNoInput = By.id("admissionNo");
    private final By studentNameInput = By.id("studentName");
    private final By classDropdown = By.id("classId");
    private final By sectionInput = By.id("section");
    private final By genderDropdown = By.id("gender");
    private final By dateOfBirthInput = By.id("dateOfBirth");
    private final By addressInput = By.id("address");
    private final By cityInput = By.id("city");
    private final By stateInput = By.id("state");
    private final By pinCodeInput = By.id("pinCode");
    private final By mobileNumberInput =By.xpath("//input[@id='mobileNumber']");
    private final By emailInput = By.id("email");
    private final By passwordInput = By.id("password");
    private final By parentNameInput = By.id("parentName");
    private final By parentPhoneInput = By.id("parentPhone");
    private final By activeCheckbox = By.id("active");
    private final By backToStudentsLink = By.cssSelector("a[href='/students']");
    private final By saveStudentButton = By.xpath("//button[normalize-space()='Save Student' or normalize-space()='Submit']");
    private final By alertBox = By.id("alert");
    private final By studentCreationSuccesMessage=By.xpath("//*[normalize-space()='Student created successfully!']");

    public AddStudentPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean verifyTitleOfAddStudentPage() {
        logger.info("Verifying Add Student page title");
        return driver.getTitle().equals("EduManager - Add Student");
    }

    public boolean isSidebarDisplayed() {
        logger.info("Checking if sidebar is displayed");
        return ElementUtils.isDisplayed(sidebar);
    }

    public String getSidebarBrandText() {
        logger.info("Getting sidebar brand text");
        return ElementUtils.getText(sidebarBrand);
    }

    public String getSidebarSubtitleText() {
        logger.info("Getting sidebar subtitle text");
        return ElementUtils.getText(sidebarSubtitle);
    }

    public boolean isDashboardLinkDisplayed() {
        logger.info("Checking if Dashboard link is displayed");
        return ElementUtils.isDisplayed(dashboardLink);
    }

    public boolean isClassesLinkDisplayed() {
        logger.info("Checking if Classes link is displayed");
        return ElementUtils.isDisplayed(classesLink);
    }

    public boolean isSubjectsLinkDisplayed() {
        logger.info("Checking if Subjects link is displayed");
        return ElementUtils.isDisplayed(subjectsLink);
    }

    public boolean isStudentsLinkDisplayed() {
        logger.info("Checking if Students link is displayed");
        return ElementUtils.isDisplayed(studentsLink);
    }

    public boolean isTeachersLinkDisplayed() {
        logger.info("Checking if Teachers link is displayed");
        return ElementUtils.isDisplayed(teachersLink);
    }

    public boolean isFeesLinkDisplayed() {
        logger.info("Checking if Fees link is displayed");
        return ElementUtils.isDisplayed(feesLink);
    }

    public boolean isAttendanceLinkDisplayed() {
        logger.info("Checking if Attendance link is displayed");
        return ElementUtils.isDisplayed(attendanceLink);
    }

    public boolean isExamsLinkDisplayed() {
        logger.info("Checking if Exams link is displayed");
        return ElementUtils.isDisplayed(examsLink);
    }

    public boolean isAnnouncementsLinkDisplayed() {
        logger.info("Checking if Announcements link is displayed");
        return ElementUtils.isDisplayed(announcementsLink);
    }

    public boolean isReportCardLinkDisplayed() {
        logger.info("Checking if Report Card link is displayed");
        return ElementUtils.isDisplayed(reportCardLink);
    }

    public boolean isProfileLinkDisplayed() {
        logger.info("Checking if Profile link is displayed");
        return ElementUtils.isDisplayed(profileLink);
    }

    public boolean isSidebarLogoutLinkDisplayed() {
        logger.info("Checking if sidebar logout link is displayed");
        return ElementUtils.isDisplayed(sidebarLogoutLink);
    }

    public boolean isTopHeaderDisplayed() {
        logger.info("Checking if Add Student top header is displayed");
        return ElementUtils.isDisplayed(topHeader);
    }

    public boolean isPageHeaderDisplayed() {
        logger.info("Checking if Add Student page header is displayed");
        return ElementUtils.isDisplayed(pageHeader);
    }

    public String getPageHeaderText() {
        logger.info("Getting Add Student page header text");
        return ElementUtils.getText(pageHeader);
    }

    public void clickDarkModeToggle() {
        logger.info("Clicking dark mode toggle button");
        ElementUtils.safeClick(darkModeToggleBtn);
    }

    public boolean isSunIconDisplayed() {
        logger.info("Checking if sun icon is displayed");
        return ElementUtils.isDisplayed(sunIcon);
    }

    public boolean isMoonIconDisplayed() {
        logger.info("Checking if moon icon is displayed");
        return ElementUtils.isDisplayed(moonIcon);
    }

    public String getWelcomeText() {
        logger.info("Getting welcome text from header");
        return ElementUtils.getText(welcomeText);
    }

    public String getLoggedInUsername() {
        logger.info("Getting logged in username from header");
        return ElementUtils.getText(loggedInUser);
    }

    public void clickHeaderLogout() {
        logger.info("Clicking header logout button");
        ElementUtils.safeClick(headerLogoutButton);
    }

    public void enterAdmissionNo(String admissionNo) {
        logger.info("Entering Admission No: '{}'", admissionNo);
        ElementUtils.clearAndType(admissionNoInput, admissionNo);
    }

    public void enterStudentName(String studentName) {
        logger.info("Entering Student Name: '{}'", studentName);
        ElementUtils.clearAndType(studentNameInput, studentName);
    }

    public void selectClass(String className) {
        logger.info("Selecting Class: '{}'", className);
        ScrollUtils.scrollIntoView(classDropdown);
        DropdownUtils.selectByVisibleText(classDropdown, className);
    }

    public void enterSectionForStudent(String sectionName) {
        logger.info("Entering Section: '{}'", sectionName);
        ElementUtils.clearAndType(sectionInput, sectionName);
    }

    public void selectGender(String gender) {
        logger.info("Selecting Gender: '{}'", gender);
        DropdownUtils.selectByVisibleText(genderDropdown, gender);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        logger.info("Entering Date of Birth: '{}'", dateOfBirth);
        ElementUtils.clearAndType(dateOfBirthInput, dateOfBirth);
    }

    public void enterAddress(String address) {
        logger.info("Entering Address: '{}'", address);
        ElementUtils.clearAndType(addressInput, address);
    }

    public void enterCity(String city) {
        logger.info("Entering City: '{}'", city);
        ElementUtils.clearAndType(cityInput, city);
    }

    public void enterState(String state) {
        logger.info("Entering State: '{}'", state);
        ElementUtils.clearAndType(stateInput, state);
    }

    public void enterPinCode(String pinCode) {
        logger.info("Entering Pin Code: '{}'", pinCode);
        ElementUtils.clearAndType(pinCodeInput, pinCode);
    }

    public void enterEmail(String email) {
        logger.info("Entering Email: '{}'", email);
        ElementUtils.clearAndType(emailInput, email);
    }

    public void enterPassword(String password) {
        logger.info("Entering Password: [PROTECTED]");
        ElementUtils.clearAndType(passwordInput, password);
    }

    public void enterParentName(String parentName) {
        logger.info("Entering Parent Name: '{}'", parentName);
        ElementUtils.clearAndType(parentNameInput, parentName);
    }

    public void enterParentPhone(String parentPhone) {
        logger.info("Entering Parent Phone: '{}'", parentPhone);
        ElementUtils.clearAndType(parentPhoneInput, parentPhone);
    }

    public void enterMobileNumber(String mobileNumber) {
        logger.info("Entering Mobile Number: '{}'", mobileNumber);
        ElementUtils.clearAndType(mobileNumberInput, mobileNumber);
    }

    public void enterSection(String section) {
        logger.info("Entering Section: '{}'", section);
        ElementUtils.clearAndType(sectionInput, section);
        logger.info("Entered section: '{}'", section);
    }

    public void setActiveStatus(boolean active) {
        logger.info("Setting Active status to: {}", active);
        if (ElementUtils.isDisplayed(activeCheckbox)) {
            boolean isSelected = driver.findElement(activeCheckbox).isSelected();
            if (isSelected != active) {
                ElementUtils.safeClick(activeCheckbox);
            }
        }
    }

    public void clickBackToStudents() {
        logger.info("Clicking Back to Students link");
        ElementUtils.safeClick(backToStudentsLink);
    }

    public void clickSaveStudent() {
        logger.info("Clicking Save Student button");
        ElementUtils.safeClick(saveStudentButton);
        logger.info("Clicked Save Student button");
    }

    public boolean isAlertDisplayed() {
        logger.info("Checking if alert is displayed on Add Student page");
        return ElementUtils.isDisplayed(alertBox);
    }

  
    public String getSuccessMessageForStudentCreation() {
		
   	 return ElementUtils.getText(studentCreationSuccesMessage);
   	}

    public void addStudent(
            String admissionNo,
            String studentName,
            String gender,
            String dateOfBirth,
            String address,
            String city,
            String state,
            String pinCode,
            String mobileNumber,
            String email,
            String password,
            String parentName,
            String parentPhone,
            String className,
            String section,
            boolean active) {
        logger.info("Filling Add Student form for '{}' ({})", studentName, admissionNo);
        enterAdmissionNo(admissionNo);
        enterStudentName(studentName);
        selectGender(gender);
        enterDateOfBirth(dateOfBirth);
        enterAddress(address);
        enterCity(city);
        enterState(state);
        enterPinCode(pinCode);
        enterMobileNumber(mobileNumber);
        enterEmail(email);
        enterPassword(password);
        enterParentName(parentName);
        enterParentPhone(parentPhone);
        selectClass(className);
        enterSection(section);
        setActiveStatus(active);
        clickSaveStudent();
    }
}
