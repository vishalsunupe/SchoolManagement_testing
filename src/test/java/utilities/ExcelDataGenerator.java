package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Utility to generate sample LoginData.xlsx files for QA, UAT, and PROD environments.
 */
public class ExcelDataGenerator {

    public static void main(String[] args) {
        createTestDataForEnv("qa", "qa_student@school.com", "qa_pass123");
        createTestDataForEnv("uat", "uat_student@school.com", "uat_pass123");
        createTestDataForEnv("prod", "prod_student@school.com", "prod_pass123");
        System.out.println("Excel test data files generated successfully for QA, UAT, and PROD environments!");
    }

    private static void createTestDataForEnv(String env, String validUser, String validPass) {
        String dirPath = "src/test/resources/testdata/" + env;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File excelFile = new File(dir, "LoginData.xlsx");

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("LoginTests");

            // Header row
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("TestCase");
            header.createCell(1).setCellValue("Username");
            header.createCell(2).setCellValue("Password");
            header.createCell(3).setCellValue("ExpectedResult");

            // Row 1: Valid Login
            Row row1 = sheet.createRow(1);
            row1.createCell(0).setCellValue("valid_login");
            row1.createCell(1).setCellValue(validUser);
            row1.createCell(2).setCellValue(validPass);
            row1.createCell(3).setCellValue("Dashboard");

            // Row 2: Invalid Username
            Row row2 = sheet.createRow(2);
            row2.createCell(0).setCellValue("invalid_username");
            row2.createCell(1).setCellValue("invalid_" + env + "_user@school.com");
            row2.createCell(2).setCellValue(validPass);
            row2.createCell(3).setCellValue("Invalid Credentials");

            // Row 3: Invalid Password
            Row row3 = sheet.createRow(3);
            row3.createCell(0).setCellValue("invalid_password");
            row3.createCell(1).setCellValue(validUser);
            row3.createCell(2).setCellValue("wrong_pass");
            row3.createCell(3).setCellValue("Invalid Credentials");

            try (FileOutputStream fos = new FileOutputStream(excelFile)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create Excel file for environment: " + env, e);
        }
    }
}
