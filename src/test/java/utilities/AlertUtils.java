package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Reusable utility for handling JavaScript browser alerts (Alert, Confirm, Prompt).
 */
public class AlertUtils {

    private static final Logger logger = LogManager.getLogger(AlertUtils.class);

    private AlertUtils() {
    }

    private static int getDefaultTimeout() {
        try {
            return Integer.parseInt(ConfigReader.getProperty("explicitWait", "15"));
        } catch (Exception e) {
            return 15;
        }
    }

    public static Alert waitForAlert(int timeoutInSeconds) {
        WebDriver driver = DriverFactory.getDriver();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
            logger.info("Waiting up to {} seconds for JavaScript alert to appear...", timeoutInSeconds);
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (TimeoutException e) {
            logger.warn("No alert appeared within {} seconds timeout.", timeoutInSeconds);
            return null;
        } catch (NoAlertPresentException e) {
            logger.warn("No alert present on the page.");
            return null;
        }
    }

    public static Alert waitForAlert() {
        return waitForAlert(getDefaultTimeout());
    }

    public static boolean acceptAlert() {
        Alert alert = waitForAlert();
        if (alert != null) {
            String text = alert.getText();
            alert.accept();
            logger.info("Accepted alert with message text: '{}'", text);
            return true;
        }
        logger.warn("Unable to accept alert: No alert present.");
        return false;
    }

    public static boolean dismissAlert() {
        Alert alert = waitForAlert();
        if (alert != null) {
            String text = alert.getText();
            alert.dismiss();
            logger.info("Dismissed alert with message text: '{}'", text);
            return true;
        }
        logger.warn("Unable to dismiss alert: No alert present.");
        return false;
    }

    public static String getAlertText() {
        Alert alert = waitForAlert();
        if (alert != null) {
            String text = alert.getText();
            logger.info("Retrieved alert message text: '{}'", text);
            return text;
        }
        logger.warn("Unable to retrieve alert text: No alert present.");
        return "";
    }

    public static boolean acceptAlertWithText(String expectedText) {
        Alert alert = waitForAlert();
        if (alert != null) {
            String actualText = alert.getText();
            logger.info("Validating alert text. Expected: '{}', Actual: '{}'", expectedText, actualText);
            if (actualText != null && actualText.contains(expectedText)) {
                alert.accept();
                logger.info("Alert text matched! Successfully accepted alert.");
                return true;
            } else {
                logger.error("Alert text mismatch! Expected to contain: '{}', but was: '{}'", expectedText, actualText);
                alert.dismiss();
                return false;
            }
        }
        logger.warn("Unable to validate and accept alert: No alert present.");
        return false;
    }

    public static boolean dismissAlertWithText(String expectedText) {
        Alert alert = waitForAlert();
        if (alert != null) {
            String actualText = alert.getText();
            logger.info("Validating alert text before dismiss. Expected: '{}', Actual: '{}'", expectedText, actualText);
            if (actualText != null && actualText.contains(expectedText)) {
                alert.dismiss();
                logger.info("Alert text matched! Successfully dismissed alert.");
                return true;
            } else {
                logger.error("Alert text mismatch! Expected to contain: '{}', but was: '{}'", expectedText, actualText);
                alert.dismiss();
                return false;
            }
        }
        logger.warn("Unable to validate and dismiss alert: No alert present.");
        return false;
    }

    public static boolean enterTextInAlert(String text) {
        Alert alert = waitForAlert();
        if (alert != null) {
            logger.info("Sending text to prompt alert...");
            alert.sendKeys(text);
            alert.accept();
            logger.info("Successfully entered text and accepted prompt alert.");
            return true;
        }
        logger.warn("Unable to enter text in alert: No alert present.");
        return false;
    }

    public static boolean isAlertPresent() {
        try {
            DriverFactory.getDriver().switchTo().alert();
            logger.info("Alert is present on page.");
            return true;
        } catch (NoAlertPresentException e) {
            logger.info("No alert present on page.");
            return false;
        }
    }
}
