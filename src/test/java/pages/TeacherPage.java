package pages;

import java.util.ArrayList;
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
 * Page Object Model representation of the Teacher Management Page.
 */
public class TeacherPage {

    private static final Logger logger = LogManager.getLogger(TeacherPage.class);
    private final WebDriver driver;

    // --- Locators ---
    private final By pageHeader = By.xpath("//h1[normalize-space()='Teachers']");
    private final By addTeacherButton = By.cssSelector("a[href='/add-teacher']");
    private final By alertBox = By.id("alert");
    private final By searchInput = By.id("searchInput");
    private final By searchCount = By.id("searchCount");
    private final By teacherTable = By.id("teacherTable");
    private final By teacherRows = By.cssSelector("#teacherTable tr");

    public TeacherPage() {
        this.driver = DriverFactory.getDriver();
    }

    // --- Header & General Action Methods ---

    public boolean isPageHeaderDisplayed() {
        logger.info("Checking if Teacher page header is displayed");
        return ElementUtils.isDisplayed(pageHeader);
    }

    public String getPageHeaderText() {
        logger.info("Getting Teacher page header text");
        return ElementUtils.getText(pageHeader);
    }

    public void clickAddTeacher() {
        logger.info("Clicking '+ Add Teacher' button");
        ElementUtils.safeClick(addTeacherButton);
    }

    public String getAlertText() {
        logger.info("Getting alert message text");
        return ElementUtils.getText(alertBox);
    }

    public boolean isAlertDisplayed() {
        logger.info("Checking if alert box is displayed");
        return ElementUtils.isDisplayed(alertBox);
    }

    // --- Search Bar Action Methods ---

    public void searchTeacher(String searchKeyword) {
        logger.info("Searching teacher with keyword: '{}'", searchKeyword);
        ElementUtils.clearAndType(searchInput, searchKeyword);
    }

    public String getSearchInputText() {
        logger.info("Getting current text in search input field");
        WebElement element = driver.findElement(searchInput);
        return element.getAttribute("value");
    }

    public String getSearchCountText() {
        logger.info("Getting search results count text");
        return ElementUtils.getText(searchCount);
    }

    // --- Table Action & Data Extraction Methods ---

    public int getTeacherRowCount() {
        logger.info("Getting count of teachers in table");
        List<WebElement> rows = driver.findElements(teacherRows);
        return rows.size();
    }

    public boolean isTeacherPresentByEmpId(String empId) {
        logger.info("Checking if teacher with Employee ID '{}' is present in table", empId);
        By locator = By.xpath("//tbody[@id='teacherTable']//tr[td[strong[text()='" + empId + "']]]");
        return ElementUtils.isDisplayed(locator);
    }

    public boolean isTeacherPresentByName(String teacherName) {
        logger.info("Checking if teacher with Name '{}' is present in table", teacherName);
        By locator = By.xpath("//tbody[@id='teacherTable']//tr[td[normalize-space(text())='" + teacherName + "']]");
        return ElementUtils.isDisplayed(locator);
    }

    public void clickEditTeacherByEmpId(String empId) {
        logger.info("Clicking edit button for teacher with Employee ID: '{}'", empId);
        By editBtnLocator = By.xpath("//tbody[@id='teacherTable']//tr[td[strong[text()='" + empId + "']]]//a[contains(@class,'edit')]");
        ElementUtils.safeClick(editBtnLocator);
    }

    public void clickEditTeacherByName(String teacherName) {
        logger.info("Clicking edit button for teacher with Name: '{}'", teacherName);
        By editBtnLocator = By.xpath("//tbody[@id='teacherTable']//tr[td[normalize-space(text())='" + teacherName + "']]//a[contains(@class,'edit')]");
        ElementUtils.safeClick(editBtnLocator);
    }

    public void clickDeleteTeacherByEmpId(String empId) {
        logger.info("Clicking delete button for teacher with Employee ID: '{}'", empId);
        By deleteBtnLocator = By.xpath("//tbody[@id='teacherTable']//tr[td[strong[text()='" + empId + "']]]//button[contains(@class,'delete')]");
        ElementUtils.safeClick(deleteBtnLocator);
    }

    public void clickDeleteTeacherByName(String teacherName) {
        logger.info("Clicking delete button for teacher with Name: '{}'", teacherName);
        By deleteBtnLocator = By.xpath("//tbody[@id='teacherTable']//tr[td[normalize-space(text())='" + teacherName + "']]//button[contains(@class,'delete')]");
        ElementUtils.safeClick(deleteBtnLocator);
    }

    public Map<String, String> getTeacherDetailsByEmpId(String empId) {
        logger.info("Retrieving teacher details for Employee ID: '{}'", empId);
        By rowLocator = By.xpath("//tbody[@id='teacherTable']//tr[td[strong[text()='" + empId + "']]]");
        WebElement row = driver.findElement(rowLocator);
        List<WebElement> cells = row.findElements(By.tagName("td"));

        Map<String, String> details = new HashMap<>();
        if (cells.size() >= 8) {
            details.put("employeeId", cells.get(0).getText().trim());
            details.put("name", cells.get(1).getText().trim());
            details.put("gender", cells.get(2).getText().trim());
            details.put("qualification", cells.get(3).getText().trim());
            details.put("specialization", cells.get(4).getText().trim());
            details.put("email", cells.get(5).getText().trim());
            details.put("salary", cells.get(6).getText().trim());
            details.put("status", cells.get(7).getText().trim());
        }
        return details;
    }

    public List<Map<String, String>> getAllTeachersData() {
        logger.info("Retrieving all teachers data from table");
        List<WebElement> rows = driver.findElements(teacherRows);
        List<Map<String, String>> allData = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            if (cells.size() >= 8) {
                Map<String, String> rowData = new HashMap<>();
                rowData.put("employeeId", cells.get(0).getText().trim());
                rowData.put("name", cells.get(1).getText().trim());
                rowData.put("gender", cells.get(2).getText().trim());
                rowData.put("qualification", cells.get(3).getText().trim());
                rowData.put("specialization", cells.get(4).getText().trim());
                rowData.put("email", cells.get(5).getText().trim());
                rowData.put("salary", cells.get(6).getText().trim());
                rowData.put("status", cells.get(7).getText().trim());
                allData.add(rowData);
            }
        }
        return allData;
    }
}
