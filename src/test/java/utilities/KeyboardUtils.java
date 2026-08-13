package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Reusable utility for low-level keyboard actions and key combinations (CTRL+A, CTRL+C, CTRL+V, TAB, etc.).
 */
public class KeyboardUtils {

    private static final Logger logger = LogManager.getLogger(KeyboardUtils.class);

    private KeyboardUtils() {
    }

    private static Actions getActions() {
        return new Actions(DriverFactory.getDriver());
    }

    public static void pressEnter() {
        logger.info("Pressing ENTER key...");
        getActions().sendKeys(Keys.ENTER).perform();
    }

    public static void pressEscape() {
        logger.info("Pressing ESCAPE key...");
        getActions().sendKeys(Keys.ESCAPE).perform();
    }

    public static void pressTab() {
        logger.info("Pressing TAB key...");
        getActions().sendKeys(Keys.TAB).perform();
    }

    public static void pressArrowUp() {
        logger.info("Pressing ARROW_UP key...");
        getActions().sendKeys(Keys.ARROW_UP).perform();
    }

    public static void pressArrowDown() {
        logger.info("Pressing ARROW_DOWN key...");
        getActions().sendKeys(Keys.ARROW_DOWN).perform();
    }

    public static void pressArrowLeft() {
        logger.info("Pressing ARROW_LEFT key...");
        getActions().sendKeys(Keys.ARROW_LEFT).perform();
    }

    public static void pressArrowRight() {
        logger.info("Pressing ARROW_RIGHT key...");
        getActions().sendKeys(Keys.ARROW_RIGHT).perform();
    }

    public static void sendKeys(WebElement element, CharSequence... keys) {
        WaitUtils.waitForVisibility(element);
        logger.info("Sending keystrokes to element...");
        element.sendKeys(keys);
    }

    public static void sendKeys(By locator, CharSequence... keys) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        sendKeys(element, keys);
    }

    public static void ctrlA(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Performing CTRL+A select all on element...");
        getActions().keyDown(element, Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
    }

    public static void ctrlC(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Performing CTRL+C copy on element...");
        getActions().keyDown(element, Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
    }

    public static void ctrlV(WebElement element) {
        WaitUtils.waitForVisibility(element);
        logger.info("Performing CTRL+V paste on element...");
        getActions().keyDown(element, Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
    }

    public static void shiftTab() {
        logger.info("Performing SHIFT+TAB backward focus navigation...");
        getActions().keyDown(Keys.SHIFT).sendKeys(Keys.TAB).keyUp(Keys.SHIFT).perform();
    }

    public static void pressCombination(CharSequence... keys) {
        logger.info("Pressing key combination...");
        getActions().sendKeys(keys).perform();
    }
}
