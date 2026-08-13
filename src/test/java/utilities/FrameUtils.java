package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Reusable utility for iframe context switching with dynamic explicit waits.
 */
public class FrameUtils {

    private static final Logger logger = LogManager.getLogger(FrameUtils.class);

    private FrameUtils() {
    }

    private static Duration getDefaultTimeout() {
        long seconds = Long.parseLong(ConfigReader.getProperty("explicitWait", "15"));
        return Duration.ofSeconds(seconds);
    }

    public static void switchToFrame(WebElement element) {
        WebDriver driver = DriverFactory.getDriver();
        logger.info("Waiting for iframe element and switching context...");
        WebDriverWait wait = new WebDriverWait(driver, getDefaultTimeout());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
        logger.info("Successfully switched to frame.");
    }

    public static void switchToFrame(By locator) {
        WebDriver driver = DriverFactory.getDriver();
        logger.info("Waiting for iframe locator and switching context...");
        WebDriverWait wait = new WebDriverWait(driver, getDefaultTimeout());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        logger.info("Successfully switched to frame.");
    }

    public static void switchToFrameByIndex(int index) {
        WebDriver driver = DriverFactory.getDriver();
        logger.info("Waiting for iframe index {} and switching context...", index);
        WebDriverWait wait = new WebDriverWait(driver, getDefaultTimeout());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
        logger.info("Successfully switched to frame at index {}.", index);
    }

    public static void switchToFrameByNameOrId(String nameOrId) {
        WebDriver driver = DriverFactory.getDriver();
        logger.info("Waiting for iframe name/id '{}' and switching context...", nameOrId);
        WebDriverWait wait = new WebDriverWait(driver, getDefaultTimeout());
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
        logger.info("Successfully switched to frame '{}'.", nameOrId);
    }

    public static void switchToParentFrame() {
        logger.info("Switching back to parent frame context...");
        DriverFactory.getDriver().switchTo().parentFrame();
    }

    public static void switchToDefaultContent() {
        logger.info("Switching back to main top-level document content...");
        DriverFactory.getDriver().switchTo().defaultContent();
    }
}
