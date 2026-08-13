package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.ConfigReader;

import java.time.Duration;
import java.util.Objects;

/**
 * Thread-safe DriverFactory using ThreadLocal<WebDriver> with Log4j2 logging.
 */
public class DriverFactory {

    private static final Logger logger = LogManager.getLogger(DriverFactory.class);
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver initializeDriver() {
        String browser = ConfigReader.getProperty("browser", "chrome");
        return initializeDriver(browser);
    }

    public static WebDriver initializeDriver(String browser) {
        if (driverThreadLocal.get() != null) {
            logger.info("WebDriver instance already exists for current thread [Thread ID: {}]", Thread.currentThread().getId());
            return driverThreadLocal.get();
        }

        String targetBrowser = Objects.requireNonNull(browser, "Browser parameter must not be null")
                .trim().toLowerCase();

        boolean isHeadless = Boolean.parseBoolean(ConfigReader.getProperty("headless", "false"));
        long implicitWait = Long.parseLong(ConfigReader.getProperty("implicitWait", "10"));
        long pageLoadTimeout = Long.parseLong(ConfigReader.getProperty("pageLoadTimeout", "30"));

        logger.info("Initializing WebDriver for target browser: '{}' (Headless: {}) [Thread ID: {}]",
                targetBrowser, isHeadless, Thread.currentThread().getId());

        WebDriver driver;

        switch (targetBrowser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--start-maximized");
                if (isHeadless) {
                    chromeOptions.addArguments("--headless=new");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                String edgeDriverPath = ConfigReader.getEdgeDriverPath();
                if (edgeDriverPath != null && !edgeDriverPath.isEmpty()) {
                    java.io.File driverFile = new java.io.File(edgeDriverPath);
                    if (driverFile.exists()) {
                        System.setProperty("webdriver.edge.driver", edgeDriverPath);
                        logger.info("Using configured Edge driver at: {}", edgeDriverPath);
                    } else {
                        logger.warn("Configured Edge driver not found at: {}. Relying on Selenium Manager.", edgeDriverPath);
                    }
                }
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("--start-maximized");
                if (isHeadless) {
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                logger.error("Unsupported browser requested: '{}'", browser);
                throw new IllegalArgumentException(String.format(
                        "Unsupported browser specified: '%s'. Supported browsers are: chrome, firefox, edge", browser));
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTimeout));

        driverThreadLocal.set(driver);
        logger.info("WebDriver successfully initialized and stored in ThreadLocal for thread [Thread ID: {}]", Thread.currentThread().getId());
        return getDriver();
    }

    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            logger.error("Attempted to access getDriver() before initializing WebDriver on thread [Thread ID: {}]", Thread.currentThread().getId());
            throw new IllegalStateException("WebDriver is not initialized for the current thread. Call initializeDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            try {
                logger.info("Terminating WebDriver session for thread [Thread ID: {}]", Thread.currentThread().getId());
                driver.quit();
            } finally {
                driverThreadLocal.remove();
                logger.info("Removed ThreadLocal WebDriver reference for thread [Thread ID: {}]", Thread.currentThread().getId());
            }
        }
    }
}
