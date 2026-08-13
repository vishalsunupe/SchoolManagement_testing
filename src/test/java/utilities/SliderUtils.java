package utilities;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Reusable utility for interacting with UI Sliders (HTML5 <input type="range">, ARIA sliders, and custom div-based sliders).
 */
public class SliderUtils {

    private static final Logger logger = LogManager.getLogger(SliderUtils.class);

    private SliderUtils() {
    }

    public static void moveSliderByOffset(WebElement slider, int xOffset, int yOffset) {
        WaitUtils.waitForVisibility(slider);
        logger.info("Moving slider by offset (X: {}, Y: {})...", xOffset, yOffset);
        new Actions(DriverFactory.getDriver()).dragAndDropBy(slider, xOffset, yOffset).perform();
    }

    public static void moveSliderByOffset(By sliderLocator, int xOffset, int yOffset) {
        WebElement slider = WaitUtils.waitForVisibility(sliderLocator);
        moveSliderByOffset(slider, xOffset, yOffset);
    }

    public static void setSliderPercentage(WebElement slider, double percentage) {
        WaitUtils.waitForVisibility(slider);
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100. Provided: " + percentage);
        }

        int width = slider.getSize().getWidth();
        int targetX = (int) ((percentage / 100.0) * width) - (width / 2);

        logger.info("Moving slider element width {}px to percentage {}% (target offset: {}px)...", width, percentage, targetX);
        new Actions(DriverFactory.getDriver()).dragAndDropBy(slider, targetX, 0).perform();
    }

    public static void setSliderPercentage(By sliderLocator, double percentage) {
        WebElement slider = WaitUtils.waitForVisibility(sliderLocator);
        setSliderPercentage(slider, percentage);
    }

    public static void increaseSlider(WebElement slider, int steps) {
        WaitUtils.waitForVisibility(slider);
        logger.info("Increasing slider value by {} arrow steps...", steps);
        for (int i = 0; i < steps; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }
    }

    public static void decreaseSlider(WebElement slider, int steps) {
        WaitUtils.waitForVisibility(slider);
        logger.info("Decreasing slider value by {} arrow steps...", steps);
        for (int i = 0; i < steps; i++) {
            slider.sendKeys(Keys.ARROW_LEFT);
        }
    }

    public static String getSliderValue(WebElement slider) {
        WaitUtils.waitForVisibility(slider);
        String value = slider.getAttribute("value");
        if (value == null || value.isEmpty()) {
            value = slider.getAttribute("aria-valuenow");
        }
        if (value == null || value.isEmpty()) {
            value = slider.getText().trim();
        }
        logger.info("Retrieved slider current value: '{}'", value);
        return value;
    }

    public static String getSliderValue(By sliderLocator) {
        WebElement slider = WaitUtils.waitForVisibility(sliderLocator);
        return getSliderValue(slider);
    }
}
