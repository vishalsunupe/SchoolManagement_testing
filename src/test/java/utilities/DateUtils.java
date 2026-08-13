package utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility for generating date-time timestamps formatted for logs and artifact naming.
 */
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Generates a precise timestamp string suitable for file naming (e.g. 20260810_140800_123).
     * @return Formatted timestamp string
     */
    public static String getTimestamp() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
    }

    /**
     * Generates a date-time string matching a custom pattern.
     * @param pattern Desired DateTimeFormatter pattern (e.g. "yyyy-MM-dd HH:mm:ss")
     * @return Formatted date-time string
     */
    public static String getCurrentFormattedDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
}
