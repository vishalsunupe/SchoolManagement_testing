package utilities;

import base.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility for explicit wait conditions to prevent flakiness and hardcoded Thread.sleep calls.
 */
public class WaitUtils {

    private WaitUtils() {
    }

    /**
     * Resolves the default explicit wait duration configured in environment properties.
     */
    private static Duration getDefaultTimeout() {
        long seconds = Long.parseLong(ConfigReader.getProperty("explicitWait", "15"));
        return Duration.ofSeconds(seconds);
    }

    /**
     * Waits until the element located by locator becomes visible on page.
     */
    public static WebElement waitForVisibility(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the given WebElement becomes visible.
     */
    public static WebElement waitForVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Waits until the element located by locator becomes clickable.
     */
    public static WebElement waitForClickability(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the given WebElement becomes clickable.
     */
    public static WebElement waitForClickability(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits until the element located by locator is present in DOM.
     */
    public static WebElement waitForPresence(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits until the element located by locator disappears or becomes invisible.
     */
    public static boolean waitForDisappearance(By locator) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Waits until the given WebElement disappears or becomes invisible.
     */
    public static boolean waitForDisappearance(WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), getDefaultTimeout());
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
