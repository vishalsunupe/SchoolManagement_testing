package utilities;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Centralized, reusable TestNG DataProvider repository.
 * Decouples Excel parsing logic from test classes and dynamically resolves
 * environment-specific test data files and sheet names.
 */
public class TestDataProvider {

    private TestDataProvider() {
    }

    /**
     * Universal DataProvider method.
     * Automatically inspects target test method for @ExcelDataSupplier annotation or applies naming conventions:
     * - Default File: <TestClassName>.xlsx or <TestClassName>Data.xlsx
     * - Default Sheet: <TestMethodName>
     *
     * @param method Target TestNG test method reference
     * @return 2D Object array containing test parameters
     */
    @DataProvider(name = "excelDataProvider")
    public static Object[][] getExcelData(Method method) {
        String fileName = "";
        String sheetName = "";

        if (method.isAnnotationPresent(ExcelDataSupplier.class)) {
            ExcelDataSupplier supplier = method.getAnnotation(ExcelDataSupplier.class);
            fileName = supplier.fileName();
            sheetName = supplier.sheetName();
        }

        // Fallbacks if annotation parameters are blank or unsupplied
        if (fileName.isEmpty()) {
            fileName = method.getDeclaringClass().getSimpleName() + ".xlsx";
        }
        if (sheetName.isEmpty()) {
            sheetName = method.getName();
        }

        try {
            Object[][] data = TestDataReader.getExcelData(fileName, sheetName);
            if (data == null || data.length == 0) {
                System.out.println(String.format("Warning: No test data found in file '%s' under sheet '%s' for environment '%s'",
                        fileName, sheetName, ConfigReader.getTargetEnvironment()));
                return new Object[0][0];
            }
            return data;
        } catch (Exception e) {
            System.err.println(String.format("Failed to load test data for test '%s': %s", method.getName(), e.getMessage()));
            throw e;
        }
    }

    /**
     * Dedicated DataProvider for Login test scenarios.
     */
    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        return TestDataReader.getExcelData("LoginData.xlsx", "LoginTests");
    }
}
