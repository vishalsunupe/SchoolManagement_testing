package listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import utilities.ConfigReader;

/**
 * Custom TestNG IRetryAnalyzer implementation.
 * Automatically retries failed tests up to a configurable maximum retry limit read from environment properties.
 */
public class RetryAnalyzer implements IRetryAnalyzer {

    private static final Logger logger = LogManager.getLogger(RetryAnalyzer.class);
    private int retryCount = 0;

    /**
     * Resolves the maximum retry limit from environment configuration (defaults to 1).
     */
    private int getMaxRetryCount() {
        try {
            return Integer.parseInt(ConfigReader.getProperty("maxRetryCount", "1"));
        } catch (Exception e) {
            return 1;
        }
    }

    @Override
    public boolean retry(ITestResult result) {
        int maxRetryCount = getMaxRetryCount();

        // Do not retry passed tests
        if (result.getStatus() == ITestResult.SUCCESS) {
            return false;
        }

        // Retry failed attempts up to maxRetryCount
        if (retryCount < maxRetryCount) {
            retryCount++;
            logger.warn("Retrying test method '{}' [Attempt {} of {}] due to failure: {}",
                    result.getMethod().getMethodName(), retryCount, maxRetryCount,
                    (result.getThrowable() != null ? result.getThrowable().getMessage() : "Unknown Failure"));
            return true;
        }

        return false;
    }
}
