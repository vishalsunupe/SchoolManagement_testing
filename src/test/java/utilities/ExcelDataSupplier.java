package utilities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom annotation to configure target Excel file and sheet for data-driven tests.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcelDataSupplier {

    /**
     * Name of the Excel file inside the environment folder (e.g. "LoginData.xlsx").
     */
    String fileName() default "";

    /**
     * Target sheet name inside the Excel file (e.g. "LoginTests").
     */
    String sheetName() default "";
}
