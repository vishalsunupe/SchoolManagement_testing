package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

/**
 * Utility for performing JavaScript-based DOM actions (scroll, JS click, scroll-into-view).
 */
public class JavaScriptUtils {

    private JavaScriptUtils() {
    }

    private static JavascriptExecutor getExecutor() {
        return (JavascriptExecutor) DriverFactory.getDriver();
    }

    /**
     * Scrolls the page until the target WebElement is centered in the viewport.
     */
    public static void scrollIntoView(WebElement element) {
        getExecutor().executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
    }

    /**
     * Scrolls the page until the element located by locator is centered in the viewport.
     */
    public static void scrollIntoView(By locator) {
        WebElement element = DriverFactory.getDriver().findElement(locator);
        scrollIntoView(element);
    }

    /**
     * Performs a direct JavaScript click on the target element, bypassing overlay blocks.
     */
    public static void jsClick(WebElement element) {
        getExecutor().executeScript("arguments[0].click();", element);
    }

    /**
     * Performs a direct JavaScript click on the element located by locator.
     */
    public static void jsClick(By locator) {
        WebElement element = DriverFactory.getDriver().findElement(locator);
        jsClick(element);
    }

    /**
     * Scrolls the browser viewport smoothly to the bottom of the page.
     */
    public static void scrollToBottom() {
        getExecutor().executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Scrolls the browser viewport to the top of the page.
     */
    public static void scrollToTop() {
        getExecutor().executeScript("window.scrollTo(0, 0);");
    }
}
