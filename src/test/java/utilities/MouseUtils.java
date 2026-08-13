package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Reusable utility for low-level mouse actions (click, doubleClick, rightClick, hover, clickAndHold, moveByOffset).
 */
public class MouseUtils {

    private static final Logger logger = LogManager.getLogger(MouseUtils.class);

    private MouseUtils() {
    }

    private static Actions getActions() {
        return new Actions(DriverFactory.getDriver());
    }

    public static void click(WebElement element) {
        WaitUtils.waitForClickability(element);
        logger.info("Performing mouse click on element...");
        getActions().click(element).perform();
    }

    public static void click(By locator) {
        WebElement element = WaitUtils.waitForClickability(locator);
        click(element);
    }

    public static void doubleClick(WebElement element) {
        WaitUtils.waitForClickability(element);
        logger.info("Performing mouse double-click on element...");
        getActions().doubleClick(element).perform();
    }

    public static void doubleClick(By locator) {
        WebElement element = WaitUtils.waitForClickability(locator);
        doubleClick(element);
    }

    public static void rightClick(WebElement element) {
        WaitUtils.waitForClickability(element);
        logger.info("Performing mouse context/right-click on element...");
        getActions().contextClick(element).perform();
    }

    public static void rightClick(By locator) {
        WebElement element = WaitUtils.waitForClickability(locator);
        rightClick(element);
    }

    public static void hover(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Hovering mouse over element...");
        getActions().moveToElement(element).perform();
    }

    public static void hover(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        hover(element);
    }

    public static void clickAndHold(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Clicking and holding element with mouse...");
        getActions().clickAndHold(element).perform();
    }

    public static void release() {
        logger.info("Releasing mouse button...");
        getActions().release().perform();
    }

    public static void moveToElement(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Moving mouse pointer to element...");
        getActions().moveToElement(element).perform();
    }

    public static void moveByOffset(int x, int y) {
        logger.info("Moving mouse by offset (X: {}, Y: {})...", x, y);
        getActions().moveByOffset(x, y).perform();
    }
}
