package utilities;

import base.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Utility for capturing and persisting test failure screenshots.
 * Thread-safe implementation appending Thread ID to screenshot file names to prevent collisions.
 */
public class ScreenshotUtils {

    private ScreenshotUtils() {
    }

    public static String captureScreenshot(String testName) {
        return captureScreenshot(testName, "reports/screenshots");
    }

    public static String captureScreenshot(String testName, String folderPath) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            if (driver == null) {
                return null;
            }
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = DateUtils.getTimestamp();
            long threadId = Thread.currentThread().getId();

            File targetDir = new File(folderPath);
            if (!targetDir.exists()) {
                targetDir.mkdirs();
            }

            // Append threadId to filename to guarantee thread safety in parallel executions
            File destination = new File(targetDir, testName + "_t" + threadId + "_" + timestamp + ".png");
            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return destination.getAbsolutePath();
        } catch (Exception e) {
            System.err.println("Failed to capture screenshot for test '" + testName + "': " + e.getMessage());
            return null;
        }
    }

    public static String getBase64Screenshot() {
        return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
