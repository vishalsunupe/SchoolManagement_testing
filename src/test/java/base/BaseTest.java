package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.ConfigReader;

/**
 * BaseTest serves as the core framework engine for test lifecycle management with Log4j2 logging.
 */
public abstract class BaseTest {

    protected final Logger logger = LogManager.getLogger(getClass());

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional String browserParam) {
        logger.info("Executing @BeforeMethod setup on thread [Thread ID: {}]", Thread.currentThread().getId());

        String browser = (browserParam != null && !browserParam.trim().isEmpty())
                ? browserParam
                : ConfigReader.getProperty("browser", "chrome");

        WebDriver driver = DriverFactory.initializeDriver(browser);

        String baseUrl = ConfigReader.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            logger.error("baseUrl is missing in configuration for environment: {}", ConfigReader.getTargetEnvironment());
            throw new IllegalStateException("baseUrl is not configured for the active environment: " 
                    + ConfigReader.getTargetEnvironment());
        }

        logger.info("Navigating to target application URL: {}", baseUrl);
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        logger.info("Executing @AfterClass teardown on thread [Thread ID: {}]", Thread.currentThread().getId());
        DriverFactory.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }
}
