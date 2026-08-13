package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * High-level element interaction utility combining explicit waits with safe DOM interactions.
 */
public class ElementUtils {

    private ElementUtils() {
    }

    /**
     * Waits for element clickability before clicking.
     */
    public static void safeClick(By locator) {
        try {
            WebElement element = WaitUtils.waitForClickability(locator);
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            JavaScriptUtils.jsClick(locator);
        }
    }

    /**
     * Waits for element clickability before clicking.
     */
    public static void safeClick(WebElement element) {
        try {
            WaitUtils.waitForClickability(element).click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            JavaScriptUtils.jsClick(element);
        }
    }

    /**
     * Waits for element visibility before sending keystrokes.
     */
    public static void sendKeys(By locator, String text) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.sendKeys(text);
    }

    /**
     * Waits for element visibility before sending keystrokes.
     */
    public static void sendKeys(WebElement element, String text) {
        WaitUtils.waitForVisibility(element).sendKeys(text);
    }

    /**
     * Clears input field content and types new text safely.
     */
    public static void clearAndType(By locator, String text) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Clears input field content and types new text safely.
     */
    public static void clearAndType(WebElement element, String text) {
        WaitUtils.waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Waits for element visibility and retrieves trimmed text.
     */
    public static String getText(By locator) {
        return WaitUtils.waitForVisibility(locator).getText().trim();
    }

    /**
     * Waits for element visibility and retrieves trimmed text.
     */
    public static String getText(WebElement element) {
        return WaitUtils.waitForVisibility(element).getText().trim();
    }

    /**
     * Checks if element located by locator is displayed on page with explicit wait.
     */
    public static boolean isDisplayed(By locator) {
        try {
            return WaitUtils.waitForVisibility(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if element is displayed on page with explicit wait.
     */
    public static boolean isDisplayed(WebElement element) {
        try {
            return WaitUtils.waitForVisibility(element).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
