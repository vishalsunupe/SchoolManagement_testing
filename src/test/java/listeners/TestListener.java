package listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.ExtentReportManager;
import utilities.ScreenshotUtils;

/**
 * Custom TestNG Listener integrating ThreadLocal ExtentReports, Log4j2 logging, and automated screenshot attachments.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LogManager.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("==================================================");
        logger.info("Starting Test Suite Execution: {}", context.getName());
        logger.info("==================================================");
        ExtentReportManager.getExtentReports();
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("==================================================");
        logger.info("Finished Test Suite Execution: {}", context.getName());
        logger.info("==================================================");
        ExtentReportManager.flushReports();
    }

    @Override
    public void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String description = result.getMethod().getDescription();
        logger.info("[TEST STARTED] -> {} (Thread ID: {})", methodName, Thread.currentThread().getId());

        ExtentTest test = ExtentReportManager.createTest(methodName, description);
        test.log(Status.INFO, "Test execution started for method: " + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.info("[TEST PASSED]  -> {} (Thread ID: {})", methodName, Thread.currentThread().getId());

        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.log(Status.PASS, "Test Passed Successfully: " + methodName);
        }
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.error("[TEST FAILED]  -> {} (Thread ID: {}) | Failure Cause: {}", 
                methodName, Thread.currentThread().getId(), result.getThrowable().getMessage(), result.getThrowable());

        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.log(Status.FAIL, "Test Failed: " + methodName);
            test.log(Status.FAIL, result.getThrowable());

            String screenshotPath = ScreenshotUtils.captureScreenshot(methodName, "reports/screenshots");
            if (screenshotPath != null) {
                try {
                    test.fail("Failure Screenshot",
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                    logger.info("Attached failure screenshot to ExtentReport: {}", screenshotPath);
                } catch (Exception e) {
                    logger.warn("Failed to attach screenshot to ExtentReport: {}", e.getMessage());
                }
            }
        }
        ExtentReportManager.removeTest();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        logger.warn("[TEST SKIPPED] -> {} (Thread ID: {})", methodName, Thread.currentThread().getId());

        ExtentTest test = ExtentReportManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test Skipped: " + methodName);
            if (result.getThrowable() != null) {
                test.log(Status.SKIP, result.getThrowable());
            }
        }
        ExtentReportManager.removeTest();
    }
}
