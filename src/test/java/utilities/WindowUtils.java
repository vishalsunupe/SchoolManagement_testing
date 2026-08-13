package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.Set;

/**
 * Reusable utility for handling multiple browser windows and tabs.
 */
public class WindowUtils {

    private static final Logger logger = LogManager.getLogger(WindowUtils.class);

    private WindowUtils() {
    }

    public static Set<String> getWindowHandles() {
        Set<String> handles = DriverFactory.getDriver().getWindowHandles();
        logger.info("Retrieved {} window handle(s).", handles.size());
        return handles;
    }

    public static void switchToWindow(String handle) {
        logger.info("Switching to window handle: '{}'", handle);
        DriverFactory.getDriver().switchTo().window(handle);
    }

    public static void switchToNewWindow() {
        logger.info("Opening and switching to a new browser window...");
        DriverFactory.getDriver().switchTo().newWindow(WindowType.WINDOW);
    }

    public static void switchToNewTab() {
        logger.info("Opening and switching to a new browser tab...");
        DriverFactory.getDriver().switchTo().newWindow(WindowType.TAB);
    }

    public static boolean switchToWindowByTitle(String expectedTitle) {
        WebDriver driver = DriverFactory.getDriver();
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        logger.info("Searching for window with title containing: '{}'", expectedTitle);
        for (String handle : allHandles) {
            driver.switchTo().window(handle);
            if (driver.getTitle() != null && driver.getTitle().toLowerCase().contains(expectedTitle.toLowerCase())) {
                logger.info("Switched to window with title: '{}'", driver.getTitle());
                return true;
            }
        }
        logger.warn("Window with title containing '{}' not found. Switching back to original window.", expectedTitle);
        driver.switchTo().window(currentHandle);
        return false;
    }

    public static boolean switchToWindowByUrl(String expectedUrl) {
        WebDriver driver = DriverFactory.getDriver();
        String currentHandle = driver.getWindowHandle();
        Set<String> allHandles = driver.getWindowHandles();

        logger.info("Searching for window with URL containing: '{}'", expectedUrl);
        for (String handle : allHandles) {
            driver.switchTo().window(handle);
            if (driver.getCurrentUrl() != null && driver.getCurrentUrl().toLowerCase().contains(expectedUrl.toLowerCase())) {
                logger.info("Switched to window with URL: '{}'", driver.getCurrentUrl());
                return true;
            }
        }
        logger.warn("Window with URL containing '{}' not found. Switching back to original window.", expectedUrl);
        driver.switchTo().window(currentHandle);
        return false;
    }

    public static void closeCurrentWindow() {
        logger.info("Closing active window context...");
        DriverFactory.getDriver().close();
    }

    public static void switchToParentWindow() {
        WebDriver driver = DriverFactory.getDriver();
        Set<String> handles = driver.getWindowHandles();
        if (!handles.isEmpty()) {
            String parentHandle = handles.iterator().next();
            logger.info("Switching back to primary/parent window handle: '{}'", parentHandle);
            driver.switchTo().window(parentHandle);
        }
    }
}
