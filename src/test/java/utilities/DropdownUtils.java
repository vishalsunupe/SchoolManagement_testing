package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Reusable utility for handling standard HTML <select> dropdowns and custom dropdowns.
 */
public class DropdownUtils {

    private static final Logger logger = LogManager.getLogger(DropdownUtils.class);

    private DropdownUtils() {
    }

    private static Select getSelect(WebElement element) {
        WaitUtils.waitForVisibility(element);
        return new Select(element);
    }

    private static Select getSelect(By locator) {
        WebElement element = WaitUtils.waitForVisibility(locator);
        return new Select(element);
    }

    public static void selectByVisibleText(WebElement element, String text) {
        logger.info("Selecting dropdown option by visible text: '{}'", text);
        getSelect(element).selectByVisibleText(text);
    }

    public static void selectByVisibleText(By locator, String text) {
        logger.info("Selecting dropdown option by visible text: '{}'", text);
        getSelect(locator).selectByVisibleText(text);
    }

    public static void selectByValue(WebElement element, String value) {
        logger.info("Selecting dropdown option by value: '{}'", value);
        getSelect(element).selectByValue(value);
    }

    public static void selectByValue(By locator, String value) {
        logger.info("Selecting dropdown option by value: '{}'", value);
        getSelect(locator).selectByValue(value);
    }

    public static void selectByIndex(WebElement element, int index) {
        logger.info("Selecting dropdown option by index: {}", index);
        getSelect(element).selectByIndex(index);
    }

    public static void selectByIndex(By locator, int index) {
        logger.info("Selecting dropdown option by index: {}", index);
        getSelect(locator).selectByIndex(index);
    }

    public static String getSelectedOption(WebElement element) {
        String text = getSelect(element).getFirstSelectedOption().getText().trim();
        logger.info("Retrieved selected dropdown option: '{}'", text);
        return text;
    }

    public static String getSelectedOption(By locator) {
        String text = getSelect(locator).getFirstSelectedOption().getText().trim();
        logger.info("Retrieved selected dropdown option: '{}'", text);
        return text;
    }

    public static List<String> getAllOptions(WebElement element) {
        List<WebElement> options = getSelect(element).getOptions();
        List<String> optionTexts = new ArrayList<>();
        for (WebElement opt : options) {
            optionTexts.add(opt.getText().trim());
        }
        logger.info("Retrieved {} total options from dropdown.", optionTexts.size());
        return optionTexts;
    }

    public static List<String> getAllOptions(By locator) {
        return getAllOptions(WaitUtils.waitForVisibility(locator));
    }

    public static boolean isOptionAvailable(WebElement element, String text) {
        List<String> options = getAllOptions(element);
        boolean found = options.stream().anyMatch(opt -> opt.equalsIgnoreCase(text.trim()));
        logger.info("Option '{}' available status: {}", text, found);
        return found;
    }

    public static boolean isOptionAvailable(By locator, String text) {
        return isOptionAvailable(WaitUtils.waitForVisibility(locator), text);
    }

    /**
     * Handles non-<select> custom dropdown menus by clicking the container and selecting option by text.
     */
    public static void selectCustomDropdown(By dropdownLocator, By optionsLocator, String optionText) {
        logger.info("Selecting option '{}' from custom dropdown", optionText);
        ElementUtils.safeClick(dropdownLocator);
        List<WebElement> options = DriverFactory.getDriver().findElements(optionsLocator);
        for (WebElement option : options) {
            if (option.getText().trim().equalsIgnoreCase(optionText.trim())) {
                option.click();
                logger.info("Clicked custom dropdown option: '{}'", optionText);
                return;
            }
        }
        logger.error("Option '{}' not found in custom dropdown options.", optionText);
        throw new IllegalArgumentException("Custom dropdown option not found: " + optionText);
    }
}
