# School Management UI Automation Framework

Production-grade, highly scalable UI Test Automation Framework built with **Java 11**, **Selenium WebDriver**, **TestNG**, **Maven**, **Page Object Model (POM)**, **Apache POI**, **ExtentReports**, and **Log4j2**. Designed for multi-environment execution, data-driven testing, thread-safe parallel execution, and automated failure reporting.

---

## Technology Stack

- **Core Language**: Java 11
- **Automation Engine**: Selenium WebDriver 4.25.0 (with native Selenium Manager)
- **Test Runner & Assertions**: TestNG 7.10.2
- **Build & Dependency Management**: Apache Maven
- **Test Data Management**: Apache POI 5.3.0 (Excel `.xlsx` / `.xls`)
- **HTML Reporting**: ExtentReports 5.1.2 (Thread-safe `ThreadLocal` reporting)
- **Logging Infrastructure**: Apache Log4j2 2.23.1 (Console & Rolling File Appender)

---

## Project Structure

```
SchoolManagement_testing/
├── pom.xml                                    # Maven dependencies, plugins & profiles
├── testng.xml                                 # Default TestNG suite configuration
├── testng-parallel-classes.xml               # Parallel class execution suite
├── testng-parallel-methods.xml               # Parallel method execution suite
├── .gitignore                                 # Git exclusion rules
└── src/
    └── test/
        ├── java/
        │   ├── base/
        │   │   ├── BaseTest.java              # Test lifecycle hooks (@BeforeMethod, @AfterMethod)
        │   │   └── DriverFactory.java         # ThreadLocal<WebDriver> factory
        │   ├── pages/
        │   │   └── LoginPage.java             # Page Object Model encapsulation
        │   ├── tests/
        │   │   └── LoginTest.java             # Data-driven TestNG test cases
        │   ├── utilities/
        │   │   ├── ConfigReader.java          # Multi-environment properties reader
        │   │   ├── TestDataReader.java        # Apache POI environment Excel loader
        │   │   ├── TestDataProvider.java      # Reusable TestNG @DataProvider repository
        │   │   ├── ExcelDataSupplier.java     # Custom test data configuration annotation
        │   │   ├── WaitUtils.java             # Explicit wait utilities
        │   │   ├── ElementUtils.java          # Safe DOM interaction helpers
        │   │   ├── JavaScriptUtils.java       # JavaScript DOM execution helpers
        │   │   ├── DateUtils.java             # Date-time timestamp generators
        │   │   ├── ScreenshotUtils.java       # Thread-safe screenshot capturing
        │   │   └── ExtentReportManager.java   # ExtentReports engine manager
        │   └── listeners/
        │       ├── TestListener.java          # TestNG execution & screenshot listener
        │       ├── RetryAnalyzer.java         # Automatic test retry logic
        │       └── AnnotationTransformer.java # Dynamic retry annotation injector
        └── resources/
            ├── log4j2.xml                     # Log4j2 console & file appender rules
            ├── config/
            │   ├── qa.properties              # QA environment configurations
            │   ├── uat.properties             # UAT environment configurations
            │   └── prod.properties            # PROD environment configurations
            ├── testdata/
            │   ├── qa/                        # QA environment Excel test data
            │   ├── uat/                       # UAT environment Excel test data
            │   └── prod/                      # PROD environment Excel test data
            └── logs/                          # Persistent runtime log directory
```

---

## Environment Setup

### Prerequisites
1. **Java Development Kit (JDK 11)** installed and `JAVA_HOME` environment variable configured.
2. **Apache Maven (3.8+)** installed and added to system `PATH`.
3. **Google Chrome / Firefox / Edge** browser installed.

### Verification
Verify installation in terminal:
```bash
java -version
mvn -version
```

---

## How to Run Tests across Environments

The framework supports environment selection via **System Property (`-Denv=...`)** or **Maven Profiles (`-P...`)**.

### 1. Run in QA Environment
```bash
mvn clean test -Denv=qa
# OR
mvn clean test -Pqa
```

### 2. Run in UAT Environment
```bash
mvn clean test -Denv=uat
# OR
mvn clean test -Puat
```

### 3. Run in PROD Environment
```bash
mvn clean test -Denv=prod
# OR
mvn clean test -Pprod
```

---

## How to Run Specific TestNG Suites & Tests

### Run Default Test Suite (`testng.xml`)
```bash
mvn clean test -Denv=qa
```

### Run Parallel Execution Suite
```bash
# Class-level parallelism (Recommended)
mvn clean test -DsuiteXmlFile=testng-parallel-classes.xml -Denv=qa

# Method-level parallelism
mvn clean test -DsuiteXmlFile=testng-parallel-methods.xml -Denv=qa
```

### Run a Specific Test Class
```bash
mvn test -Dtest=LoginTest -Denv=qa
```

### Run a Specific Test Method
```bash
mvn test -Dtest=LoginTest#testLogin -Denv=qa
```

---

## How to View Test Reports & Logs

### 1. ExtentReports HTML Report
Upon test execution completion, a single timestamped HTML report is generated inside the `reports/` folder:
```
reports/ExtentReport_YYYYMMDD_HHMMSS.html
```
Open this file in any web browser to view execution summary, system metadata, step logs, and embedded failure screenshots.

### 2. Failure Screenshots
Failure screenshots are automatically saved with Thread ID and timestamp details under:
```
reports/screenshots/<testMethodName>_t<threadId>_<timestamp>.png
```

### 3. Log4j2 Logs
Console logs are printed during test runs and persisted into:
```
logs/automation.log
```

---

## How to Add New Page Objects

1. Create a new Java class inside `src/test/java/pages/` (e.g. `DashboardPage.java`).
2. Obtain `DriverFactory.getDriver()` in the constructor.
3. Declare `private final By` locators at the top of the class.
4. Delegate DOM actions to `ElementUtils` and `WaitUtils`.
5. Keep assertions outside the Page Object.

```java
package pages;

import base.DriverFactory;
import org.openqa.selenium.By;
import utilities.ElementUtils;

public class DashboardPage {

    private final By welcomeHeader = By.id("welcome-hdr");

    public DashboardPage() {
        DriverFactory.getDriver();
    }

    public boolean isDashboardDisplayed() {
        return ElementUtils.isDisplayed(welcomeHeader);
    }
}
```

---

## How to Add New Test Data

1. Place your Excel file (`.xlsx`) inside environment folders under `src/test/resources/testdata/`:
   - `src/test/resources/testdata/qa/StudentData.xlsx`
   - `src/test/resources/testdata/uat/StudentData.xlsx`
   - `src/test/resources/testdata/prod/StudentData.xlsx`
2. Define headers in Row 0 matching parameter names.
3. Annotate your test method with `@ExcelDataSupplier`:

```java
@Test(dataProvider = "excelDataProvider", dataProviderClass = TestDataProvider.class)
@ExcelDataSupplier(fileName = "StudentData.xlsx", sheetName = "AddStudent")
public void testAddStudent(String studentName, String grade, String expectedStatus) {
    // Test logic here
}
```

---

## How to Add a New Environment (e.g. `STAGING`)

1. **Add Configuration File**: Create `src/test/resources/config/staging.properties` with environment key-values (`env=staging`, `baseUrl=...`, `browser=chrome`, etc.).
2. **Add Test Data Directory**: Create folder `src/test/resources/testdata/staging/` and populate relevant Excel data files.
3. **Register Environment**: Update `SUPPORTED_ENVIRONMENTS` in `ConfigReader.java`:
   ```java
   Set.of("qa", "uat", "prod", "staging")
   ```
4. **Add Maven Profile**: Add a `<profile>` entry in `pom.xml`:
   ```xml
   <profile>
       <id>staging</id>
       <properties>
           <env>staging</env>
       </properties>
   </profile>
   ```
5. Execute tests: `mvn clean test -Denv=staging`.
