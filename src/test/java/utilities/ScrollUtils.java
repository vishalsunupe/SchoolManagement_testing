package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Reusable utility for scrolling operations utilizing Selenium Actions API and JavaScript fallback.
 */
public class ScrollUtils {

    private static final Logger logger = LogManager.getLogger(ScrollUtils.class);

    private ScrollUtils() {
    }

    private static JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) DriverFactory.getDriver();
    }

    public static void scrollToElement(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Scrolling element into view using Actions API...");
        try {
            new Actions(DriverFactory.getDriver()).scrollToElement(element).perform();
        } catch (Exception e) {
            logger.warn("Native Actions scrollToElement failed, falling back to JavaScript scroll...");
            scrollIntoView(element);
        }
    }

    public static void scrollToElement(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        scrollToElement(element);
    }

    public static void scrollBy(int x, int y) {
        logger.info("Scrolling window by offset (X: {}, Y: {})...", x, y);
        getExecutor().executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    public static void scrollToTop() {
        logger.info("Scrolling window to top...");
        getExecutor().executeScript("window.scrollTo(0, 0);");
    }

    public static void scrollToBottom() {
        logger.info("Scrolling window to bottom...");
        getExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void scrollIntoView(WebElement element) {
        logger.info("Scrolling element into view using JavaScript...");
        getExecutor().executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    public static void scrollIntoView(By locator) {
        WebElement element = DriverFactory.getDriver().findElement(locator);
        scrollIntoView(element);
    }
}
