package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Environment-Aware Excel Test Data Reader with Log4j2 logging.
 */
public class TestDataReader {

    private static final Logger logger = LogManager.getLogger(TestDataReader.class);
    private static final DataFormatter formatter = new DataFormatter();

    private TestDataReader() {
    }

    private static String getFilePath(String fileName) {
        String env = ConfigReader.getTargetEnvironment();
        return "testdata/" + env + "/" + fileName;
    }

    public static Object[][] getExcelData(String fileName, String sheetName) {
        String resourcePath = getFilePath(fileName);
        logger.info("Reading test data from Excel file: '{}', Sheet: '{}'", resourcePath, sheetName);

        try (InputStream inputStream = TestDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                logger.error("Excel file not found on classpath: {}", resourcePath);
                throw new RuntimeException("Test data Excel file not found on classpath: " + resourcePath);
            }

            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    logger.error("Sheet '{}' not found in file: {}", sheetName, resourcePath);
                    throw new IllegalArgumentException(
                            String.format("Sheet '%s' not found in Excel file '%s'", sheetName, resourcePath));
                }

                int totalRows = sheet.getLastRowNum();
                if (totalRows <= 0) {
                    logger.warn("Sheet '{}' in file '{}' contains no data rows.", sheetName, resourcePath);
                    return new Object[0][0];
                }

                Row headerRow = sheet.getRow(0);
                if (headerRow == null) {
                    logger.error("Header row (row 0) is missing in file: {}", resourcePath);
                    throw new IllegalArgumentException("Excel sheet header row (row 0) is missing in " + resourcePath);
                }

                int totalCols = headerRow.getLastCellNum();
                Object[][] data = new Object[totalRows][totalCols];

                for (int i = 1; i <= totalRows; i++) {
                    Row currentRow = sheet.getRow(i);
                    for (int j = 0; j < totalCols; j++) {
                        if (currentRow == null) {
                            data[i - 1][j] = "";
                        } else {
                            Cell cell = currentRow.getCell(j);
                            data[i - 1][j] = formatter.formatCellValue(cell);
                        }
                    }
                }
                logger.info("Successfully loaded {} rows of test data from sheet '{}'", totalRows, sheetName);
                return data;
            }
        } catch (IOException e) {
            logger.error("IOException while reading Excel file: {}", resourcePath, e);
            throw new RuntimeException("Error reading test data file: " + resourcePath, e);
        }
    }

    public static List<Map<String, String>> getExcelDataAsListOfMaps(String fileName, String sheetName) {
        String resourcePath = getFilePath(fileName);
        logger.info("Reading test data as Map list from Excel file: '{}', Sheet: '{}'", resourcePath, sheetName);

        try (InputStream inputStream = TestDataReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                logger.error("Excel file not found on classpath: {}", resourcePath);
                throw new RuntimeException("Test data Excel file not found on classpath: " + resourcePath);
            }

            try (Workbook workbook = WorkbookFactory.create(inputStream)) {
                Sheet sheet = workbook.getSheet(sheetName);
                if (sheet == null) {
                    logger.error("Sheet '{}' not found in file: {}", sheetName, resourcePath);
                    throw new IllegalArgumentException(
                            String.format("Sheet '%s' not found in Excel file '%s'", sheetName, resourcePath));
                }

                List<Map<String, String>> dataList = new ArrayList<>();
                Row headerRow = sheet.getRow(0);

                if (headerRow == null) {
                    logger.error("Header row (row 0) is missing in file: {}", resourcePath);
                    throw new IllegalArgumentException("Excel sheet header row (row 0) is missing in " + resourcePath);
                }

                int totalCols = headerRow.getLastCellNum();
                List<String> headers = new ArrayList<>();
                for (int c = 0; c < totalCols; c++) {
                    headers.add(formatter.formatCellValue(headerRow.getCell(c)).trim());
                }

                int totalRows = sheet.getLastRowNum();
                for (int r = 1; r <= totalRows; r++) {
                    Row currentRow = sheet.getRow(r);
                    if (currentRow == null) {
                        continue;
                    }
                    Map<String, String> rowMap = new HashMap<>();
                    for (int col = 0; col < totalCols; col++) {
                        String headerKey = headers.get(col);
                        Cell cell = currentRow.getCell(col);
                        String cellValue = formatter.formatCellValue(cell);
                        rowMap.put(headerKey, cellValue);
                    }
                    dataList.add(rowMap);
                }

                logger.info("Successfully loaded {} data map records from sheet '{}'", dataList.size(), sheetName);
                return dataList;
            }
        } catch (IOException e) {
            logger.error("IOException while reading Excel file: {}", resourcePath, e);
            throw new RuntimeException("Error reading test data file: " + resourcePath, e);
        }
    }
}
