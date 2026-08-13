package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

/**
 * Thread-safe ExtentReports Manager.
 * Singleton initialization for ExtentReports, combined with thread-isolated ExtentTest 
 * instances to support safe parallel execution.
 */
public class ExtentReportManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static String reportFilePath;

    private ExtentReportManager() {
    }

    /**
     * Lazy thread-safe initialization of ExtentReports instance.
     * Generates a single timestamped report HTML file per suite run inside reports/ directory.
     *
     * @return ExtentReports singleton instance
     */
    public static synchronized ExtentReports getExtentReports() {
        if (extent == null) {
            String timestamp = DateUtils.getTimestamp();
            File reportsDir = new File("reports");
            if (!reportsDir.exists()) {
                reportsDir.mkdirs();
            }

            reportFilePath = "reports/ExtentReport_" + timestamp + ".html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);

            sparkReporter.config().setDocumentTitle("School Management UI Automation Report");
            sparkReporter.config().setReportName("Automated Test Execution Summary");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy/MM/dd_HH:mm:ss");
            

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            // Populate system & execution metadata environment properties
            extent.setSystemInfo("Environment", ConfigReader.getTargetEnvironment().toUpperCase());
            extent.setSystemInfo("Browser", ConfigReader.getProperty("browser", "chrome"));
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }

    /**
     * Creates a new ExtentTest node and binds it to the calling execution thread.
     *
     * @param testName    Target test method name
     * @param description Test method description
     * @return ExtentTest instance bound to current thread
     */
    public static synchronized ExtentTest createTest(String testName, String description) {
        ExtentTest test = getExtentReports().createTest(testName, description);
        extentTestThreadLocal.set(test);
        return test;
    }

    /**
     * Retrieves the thread-isolated ExtentTest instance for the calling thread.
     * @return Active ThreadLocal ExtentTest instance
     */
    public static ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    /**
     * Flushes the ExtentReports instance to write out HTML report content.
     */
    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    /**
     * Gets the generated report file path.
     * @return Report file path string
     */
    public static String getReportFilePath() {
        return reportFilePath;
    }

    /**
     * Cleans up ThreadLocal ExtentTest reference for the current thread.
     */
    public static void removeTest() {
        extentTestThreadLocal.remove();
    }
}
