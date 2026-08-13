package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.DriverFactory;
import utilities.ElementUtils;

public class DashBoardPage {
    private static final Logger logger = LogManager.getLogger(DashBoardPage.class);
    private final WebDriver driver;
    
    // --- Top Header Locators ---
    private final By topHeader = By.cssSelector(".top-header");
    private final By headerTitle = By.cssSelector(".top-header h1");
    private final By darkModeToggleBtn = By.cssSelector(".dark-mode-toggle");
    private final By sunIcon = By.cssSelector(".toggle-sun");
    private final By moonIcon = By.cssSelector(".toggle-moon");
    private final By welcomeText = By.xpath("//div[contains(@class,'header-actions')]/span");
    private final By loggedInUser = By.xpath("//div[contains(@class,'header-actions')]//strong");
    private final By logoutButton = By.xpath("//button[@onclick='logout()' or normalize-space()='Logout']");

    // --- Stats Grid Locators ---
    private final By statsGrid = By.cssSelector(".stats-grid");
    
    private final By totalStudentsCard = By.cssSelector("a[href='/students']");
    private final By totalStudentsValue = By.id("totalStudents");
    
    private final By totalTeachersCard = By.cssSelector("a[href='/teachers']");
    private final By totalTeachersValue = By.id("totalTeachers");
    
    private final By totalClassesCard = By.cssSelector("a[href='/classes']");
    private final By totalClassesValue = By.id("totalClasses");
    
    private final By totalSubjectsCard = By.cssSelector("a[href='/subjects']");
    private final By totalSubjectsValue = By.id("totalSubjects");
    
    private final By pendingFeesCard = By.cssSelector("a[href='/fees']");
    private final By pendingFeesValue = By.id("pendingFees");
    
    private final By todayAttendanceCard = By.cssSelector("a[href='/attendance']");
    private final By todayAttendanceValue = By.id("todayAttendance");
    
    private final By totalExamsCard = By.cssSelector("a[href='/exams']");
    private final By totalExamsValue = By.id("totalExams");
    
    private final By totalAnnouncementsCard = By.cssSelector("a[href='/announcements']");
    private final By totalAnnouncementsValue = By.id("totalAnnouncements");

    public DashBoardPage() {
        this.driver = DriverFactory.getDriver();
    }
    
    // --- Top Header Action Methods ---

    public boolean isTopHeaderDisplayed() {
        logger.info("Checking if top header is displayed");
        return ElementUtils.isDisplayed(topHeader);
    }

    public String getHeaderTitleText() {
        logger.info("Getting header title text");
        return ElementUtils.getText(headerTitle);
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
        logger.info("Getting logged in username");
        return ElementUtils.getText(loggedInUser);
    }

    public void clickLogout() {
        logger.info("Clicking Logout button");
        ElementUtils.safeClick(logoutButton);
    }

    // --- Stats Grid Action Methods ---

    public boolean isStatsGridDisplayed() {
        logger.info("Checking if stats grid container is displayed");
        return ElementUtils.isDisplayed(statsGrid);
    }

    // Total Students Card
    public boolean isTotalStudentsCardDisplayed() {
        logger.info("Checking if total students card is displayed");
        return ElementUtils.isDisplayed(totalStudentsCard);
    }

    public String getTotalStudentsCount() {
        logger.info("Getting total students count from dashboard");
        return ElementUtils.getText(totalStudentsValue);
    }

    public void clickTotalStudentsCard() {
        logger.info("Clicking total students card");
        ElementUtils.safeClick(totalStudentsCard);
    }

    // Total Teachers Card
    public boolean isTotalTeachersCardDisplayed() {
        logger.info("Checking if total teachers card is displayed");
        return ElementUtils.isDisplayed(totalTeachersCard);
    }

    public String getTotalTeachersCount() {
        logger.info("Getting total teachers count from dashboard");
        return ElementUtils.getText(totalTeachersValue);
    }

    public void clickTotalTeachersCard() {
        logger.info("Clicking total teachers card");
        ElementUtils.safeClick(totalTeachersCard);
    }

    // Total Classes Card
    public boolean isTotalClassesCardDisplayed() {
        logger.info("Checking if total classes card is displayed");
        return ElementUtils.isDisplayed(totalClassesCard);
    }

    public String getTotalClassesCount() {
        logger.info("Getting total classes count from dashboard");
        return ElementUtils.getText(totalClassesValue);
    }

    public void clickTotalClassesCard() {
        logger.info("Clicking total classes card");
        ElementUtils.safeClick(totalClassesCard);
    }

    // Total Subjects Card
    public boolean isTotalSubjectsCardDisplayed() {
        logger.info("Checking if total subjects card is displayed");
        return ElementUtils.isDisplayed(totalSubjectsCard);
    }

    public String getTotalSubjectsCount() {
        logger.info("Getting total subjects count from dashboard");
        return ElementUtils.getText(totalSubjectsValue);
    }

    public void clickTotalSubjectsCard() {
        logger.info("Clicking total subjects card");
        ElementUtils.safeClick(totalSubjectsCard);
    }

    // Pending Fees Card
    public boolean isPendingFeesCardDisplayed() {
        logger.info("Checking if pending fees card is displayed");
        return ElementUtils.isDisplayed(pendingFeesCard);
    }

    public String getPendingFeesCount() {
        logger.info("Getting pending fees count from dashboard");
        return ElementUtils.getText(pendingFeesValue);
    }

    public void clickPendingFeesCard() {
        logger.info("Clicking pending fees card");
        ElementUtils.safeClick(pendingFeesCard);
    }

    // Today's Attendance Card
    public boolean isTodayAttendanceCardDisplayed() {
        logger.info("Checking if today's attendance card is displayed");
        return ElementUtils.isDisplayed(todayAttendanceCard);
    }

    public String getTodayAttendanceCount() {
        logger.info("Getting today's attendance count from dashboard");
        return ElementUtils.getText(todayAttendanceValue);
    }

    public void clickTodayAttendanceCard() {
        logger.info("Clicking today's attendance card");
        ElementUtils.safeClick(todayAttendanceCard);
    }

    // Total Exams Card
    public boolean isTotalExamsCardDisplayed() {
        logger.info("Checking if total exams card is displayed");
        return ElementUtils.isDisplayed(totalExamsCard);
    }

    public String getTotalExamsCount() {
        logger.info("Getting total exams count from dashboard");
        return ElementUtils.getText(totalExamsValue);
    }

    public void clickTotalExamsCard() {
        logger.info("Clicking total exams card");
        ElementUtils.safeClick(totalExamsCard);
    }

    // Announcements Card
    public boolean isTotalAnnouncementsCardDisplayed() {
        logger.info("Checking if total announcements card is displayed");
        return ElementUtils.isDisplayed(totalAnnouncementsCard);
    }

    public String getTotalAnnouncementsCount() {
        logger.info("Getting total announcements count from dashboard");
        return ElementUtils.getText(totalAnnouncementsValue);
    }

    public void clickTotalAnnouncementsCard() {
        logger.info("Clicking total announcements card");
        ElementUtils.safeClick(totalAnnouncementsCard);
    }
}

