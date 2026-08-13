package pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.DriverFactory;
import utilities.ElementUtils;

/**
 * Page Object Model representation of the Students page.
 */
public class StudentPage {

    private static final Logger logger = LogManager.getLogger(StudentPage.class);
    private final WebDriver driver;

    // --- Locators ---
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
    private final By pageHeader = By.cssSelector(".top-header h1");
    private final By darkModeToggleBtn = By.cssSelector(".dark-mode-toggle");
    private final By sunIcon = By.cssSelector(".toggle-sun");
    private final By moonIcon = By.cssSelector(".toggle-moon");
    private final By welcomeText = By.xpath("//div[contains(@class,'header-actions')]/span");
    private final By loggedInUser = By.xpath("//div[contains(@class,'header-actions')]//strong");
    private final By headerLogoutButton = By.xpath("//button[contains(@onclick,'logout') or normalize-space()='Logout']");

    private final By addStudentButton = By.cssSelector("a[href='/add-student'], button#addStudent, .btn-add-student");
    private final By studentTable = By.id("studentTable");
    private final By studentRows = By.cssSelector("#studentTable tbody tr");
    private final By searchInput = By.id("searchInput");
    private final By searchCount = By.id("searchCount");
    private final By alertBox = By.id("alert");

    public StudentPage() {
        this.driver = DriverFactory.getDriver();
    }

    public boolean verifyTitleOfStudentsPage() {
        logger.info("Verifying Students page title");
        return driver.getTitle().equals("EduManager - Students");
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
        logger.info("Checking if Dashboard link is displayed in sidebar");
        return ElementUtils.isDisplayed(dashboardLink);
    }

    public boolean isClassesLinkDisplayed() {
        logger.info("Checking if Classes link is displayed in sidebar");
        return ElementUtils.isDisplayed(classesLink);
    }

    public boolean isSubjectsLinkDisplayed() {
        logger.info("Checking if Subjects link is displayed in sidebar");
        return ElementUtils.isDisplayed(subjectsLink);
    }

    public boolean isStudentsLinkDisplayed() {
        logger.info("Checking if Students link is displayed in sidebar");
        return ElementUtils.isDisplayed(studentsLink);
    }

    public boolean isTeachersLinkDisplayed() {
        logger.info("Checking if Teachers link is displayed in sidebar");
        return ElementUtils.isDisplayed(teachersLink);
    }

    public boolean isFeesLinkDisplayed() {
        logger.info("Checking if Fees link is displayed in sidebar");
        return ElementUtils.isDisplayed(feesLink);
    }

    public boolean isAttendanceLinkDisplayed() {
        logger.info("Checking if Attendance link is displayed in sidebar");
        return ElementUtils.isDisplayed(attendanceLink);
    }

    public boolean isExamsLinkDisplayed() {
        logger.info("Checking if Exams link is displayed in sidebar");
        return ElementUtils.isDisplayed(examsLink);
    }

    public boolean isAnnouncementsLinkDisplayed() {
        logger.info("Checking if Announcements link is displayed in sidebar");
        return ElementUtils.isDisplayed(announcementsLink);
    }

    public boolean isReportCardLinkDisplayed() {
        logger.info("Checking if Report Card link is displayed in sidebar");
        return ElementUtils.isDisplayed(reportCardLink);
    }

    public boolean isProfileLinkDisplayed() {
        logger.info("Checking if Profile link is displayed in sidebar");
        return ElementUtils.isDisplayed(profileLink);
    }

    public boolean isSidebarLogoutLinkDisplayed() {
        logger.info("Checking if sidebar logout link is displayed");
        return ElementUtils.isDisplayed(sidebarLogoutLink);
    }

    public boolean isTopHeaderDisplayed() {
        logger.info("Checking if Students top header is displayed");
        return ElementUtils.isDisplayed(topHeader);
    }

    public boolean isPageHeaderDisplayed() {
        logger.info("Checking if Students page header is displayed");
        return ElementUtils.isDisplayed(pageHeader);
    }

    public String getPageHeaderText() {
        logger.info("Getting Students page header text");
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

    public void clickAddStudent() {
        logger.info("Clicking Add Student button");
        ElementUtils.safeClick(addStudentButton);
    }

    public void searchStudent(String keyword) {
        logger.info("Searching for student with keyword: '{}'", keyword);
        ElementUtils.clearAndType(searchInput, keyword);
    }

    public String getSearchCountText() {
        logger.info("Getting student search count text");
        return ElementUtils.getText(searchCount);
    }

    public int getStudentRowCount() {
        logger.info("Getting number of student rows in table");
        List<WebElement> rows = driver.findElements(studentRows);
        return rows.size();
    }

    public boolean isStudentPresentById(String studentId) {
        logger.info("Checking if student with ID '{}' is present in the table", studentId);
        By locator = By.xpath("//tbody[@id='studentTable']//tr[td[normalize-space(text())='" + studentId + "']]");
        return ElementUtils.isDisplayed(locator);
    }

    public boolean isStudentPresentByName(String studentName) {
        logger.info("Checking if student with name '{}' is present in the table", studentName);
        By locator = By.xpath("//tbody[@id='studentTable']//tr[td[normalize-space(text())='" + studentName + "']]");
        return ElementUtils.isDisplayed(locator);
    }

    public String getAlertText() {
        logger.info("Getting alert text from Students page");
        return ElementUtils.getText(alertBox);
    }

    public boolean isAlertDisplayed() {
        logger.info("Checking if alert box is displayed on Students page");
        return ElementUtils.isDisplayed(alertBox);
    }

    public Map<String, String> getStudentDetailsById(String studentId) {
        logger.info("Retrieving student details for ID: '{}'", studentId);
        By rowLocator = By.xpath("//tbody[@id='studentTable']//tr[td[normalize-space(text())='" + studentId + "']]");
        WebElement row = driver.findElement(rowLocator);
        List<WebElement> cells = row.findElements(By.tagName("td"));

        Map<String, String> details = new HashMap<>();
        if (cells.size() >= 5) {
            details.put("studentId", cells.get(0).getText().trim());
            details.put("name", cells.get(1).getText().trim());
            details.put("grade", cells.get(2).getText().trim());
            details.put("email", cells.get(3).getText().trim());
            details.put("status", cells.get(4).getText().trim());
        }
        return details;
    }
}
