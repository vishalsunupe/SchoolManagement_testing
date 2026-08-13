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
 * Reusable utility for Drag-and-Drop operations using Selenium Actions API and JavaScript HTML5 fallback.
 */
public class DragAndDropUtils {

    private static final Logger logger = LogManager.getLogger(DragAndDropUtils.class);

    private DragAndDropUtils() {
    }

    private static Actions getActions() {
        return new Actions(DriverFactory.getDriver());
    }

    public static void dragAndDrop(WebElement source, WebElement target) {
        WaitUtils.waitForVisibility(source);
        WaitUtils.waitForVisibility(target);
        logger.info("Performing drag and drop using Actions API...");
        getActions().dragAndDrop(source, target).perform();
    }

    public static void dragAndDrop(By sourceLocator, By targetLocator) {
        WebElement source = WaitUtils.waitForVisibility(sourceLocator);
        WebElement target = WaitUtils.waitForVisibility(targetLocator);
        dragAndDrop(source, target);
    }

    public static void dragAndDropByOffset(WebElement source, int xOffset, int yOffset) {
        WaitUtils.waitForVisibility(source);
        logger.info("Dragging element by offset (X: {}, Y: {})...", xOffset, yOffset);
        getActions().dragAndDropBy(source, xOffset, yOffset).perform();
    }

    public static void dragAndDropByOffset(By sourceLocator, int xOffset, int yOffset) {
        WebElement source = WaitUtils.waitForVisibility(sourceLocator);
        dragAndDropByOffset(source, xOffset, yOffset);
    }

    public static void moveToElement(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Moving pointer to element...");
        getActions().moveToElement(element).perform();
    }

    public static void moveToElement(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        moveToElement(element);
    }

    public static void clickAndHold(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Clicking and holding element...");
        getActions().clickAndHold(element).perform();
    }

    public static void clickAndHold(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        clickAndHold(element);
    }

    public static void release(WebElement element) {
        logger.info("Releasing element...");
        getActions().release(element).perform();
    }

    public static void release() {
        logger.info("Releasing active action...");
        getActions().release().perform();
    }

    /**
     * JavaScript HTML5 Drag and Drop fallback helper for modern web applications where native Actions API fails.
     */
    public static void dragAndDropJS(WebElement source, WebElement target) {
        logger.info("Executing JavaScript HTML5 drag-and-drop fallback script...");
        WebDriver driver = DriverFactory.getDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsScript =
                "function createEvent(type) {" +
                "  var event = document.createEvent('CustomEvent');" +
                "  event.initCustomEvent(type, true, true, null);" +
                "  event.dataTransfer = {" +
                "    data: {}," +
                "    setData: function(key, val) { this.data[key] = val; }," +
                "    getData: function(key) { return this.data[key]; }" +
                "  };" +
                "  return event;" +
                "}" +
                "function dispatchEvent(elem, type, event) {" +
                "  if (elem.dispatchEvent) elem.dispatchEvent(event);" +
                "  else if (elem.fireEvent) elem.fireEvent('on' + type, event);" +
                "}" +
                "function simulateHTML5DragAndDrop(source, target) {" +
                "  var dragStartEvent = createEvent('dragstart');" +
                "  dispatchEvent(source, 'dragstart', dragStartEvent);" +
                "  var dropEvent = createEvent('drop');" +
                "  dropEvent.dataTransfer = dragStartEvent.dataTransfer;" +
                "  dispatchEvent(target, 'drop', dropEvent);" +
                "  var dragEndEvent = createEvent('dragend');" +
                "  dragEndEvent.dataTransfer = dragStartEvent.dataTransfer;" +
                "  dispatchEvent(source, 'dragend', dragEndEvent);" +
                "}" +
                "simulateHTML5DragAndDrop(arguments[0], arguments[1]);";

        js.executeScript(jsScript, source, target);
        logger.info("JavaScript HTML5 drag and drop executed successfully.");
    }
}
