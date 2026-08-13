package utilities;

import net.datafaker.Faker;
import java.util.Locale;

/**
 * Alternative convenient alias wrapper class for FakerUtils.
 * Provides identical static access methods for dynamic runtime test data generation.
 */
public class FakerUtil {

    private FakerUtil() {
        // Private constructor for utility class
    }

    public static Faker getFaker() {
        return FakerUtils.getFaker();
    }

    public static Faker getFaker(Locale locale) {
        return FakerUtils.getFaker(locale);
    }

    public static String getFirstName() {
        return FakerUtils.getFirstName();
    }

    public static String getLastName() {
        return FakerUtils.getLastName();
    }

    public static String getFullName() {
        return FakerUtils.getFullName();
    }

    public static String getGender() {
        return FakerUtils.getGender();
    }

    public static String getPrefix() {
        return FakerUtils.getPrefix();
    }

    public static String getBloodGroup() {
        return FakerUtils.getBloodGroup();
    }

    public static String getParentName() {
        return FakerUtils.getParentName();
    }

    public static String getStudentId() {
        return FakerUtils.getStudentId();
    }
    
    public static String getClassOfStudent() {
        return FakerUtils.getClassName();
    }
    
    public static String getSection() {
        return FakerUtils.getSection();
    }

    public static String getAdmissionNumber() {
        return FakerUtils.getAdmissionNumber();
    }

    public static String getTeacherId() {
        return FakerUtils.getTeacherId();
    }

    public static String getEmployeeId() {
        return FakerUtils.getEmployeeId();
    }

    public static String getSchoolName() {
        return FakerUtils.getSchoolName();
    }

    public static String getCourseName() {
        return FakerUtils.getCourseName();
    }

    public static String getSubject() {
        return FakerUtils.getSubject();
    }

    public static String getDepartment() {
        return FakerUtils.getDepartment();
    }

    public static String getGradeLevel() {
        return FakerUtils.getGradeLevel();
    }

    public static String getQualification() {
        return FakerUtils.getQualification();
    }

    public static String getGpa() {
        return FakerUtils.getGpa();
    }

    public static String getEmailAddress() {
        return FakerUtils.getEmailAddress();
    }

    public static String getEmailAddress(String domain) {
        return FakerUtils.getEmailAddress(domain);
    }

    public static String getPhoneNumber() {
        return FakerUtils.getPhoneNumber();
    }

    public static String getTenDigitPhoneNumber() {
        return FakerUtils.getTenDigitPhoneNumber();
    }

    public static String getStreetAddress() {
        return FakerUtils.getStreetAddress();
    }

    public static String getCity() {
        return FakerUtils.getCity();
    }

    public static String getState() {
        return FakerUtils.getState();
    }

    public static String getZipCode() {
        return FakerUtils.getZipCode();
    }

    public static String getCountry() {
        return FakerUtils.getCountry();
    }

    public static String getFullAddress() {
        return FakerUtils.getFullAddress();
    }

    public static String getDateOfBirth(int minAge, int maxAge) {
        return FakerUtils.getDateOfBirth(minAge, maxAge);
    }

    public static String getDateOfBirth(int minAge, int maxAge, String pattern) {
        return FakerUtils.getDateOfBirth(minAge, maxAge, pattern);
    }

    public static String getPastDate(int maxDaysAgo, String pattern) {
        return FakerUtils.getPastDate(maxDaysAgo, pattern);
    }

    public static String getFutureDate(int maxDaysInFuture, String pattern) {
        return FakerUtils.getFutureDate(maxDaysInFuture, pattern);
    }

    public static String getUsername() {
        return FakerUtils.getUsername();
    }

    public static String getPassword(int length, boolean includeSpecial) {
        return FakerUtils.getPassword(length, includeSpecial);
    }

    public static String getStrongPassword() {
        return FakerUtils.getStrongPassword();
    }

    public static String getTuitionFee(double min, double max) {
        return FakerUtils.getTuitionFee(min, max);
    }

    public static String getCreditCardNumber() {
        return FakerUtils.getCreditCardNumber();
    }

    public static String getUuid() {
        return FakerUtils.getUuid();
    }

    public static int getRandomNumber(int min, int max) {
        return FakerUtils.getRandomNumber(min, max);
    }

    public static String getRandomNumeric(int length) {
        return FakerUtils.getRandomNumeric(length);
    }

    public static String getRandomAlphanumeric(int length) {
        return FakerUtils.getRandomAlphanumeric(length);
    }

    public static String getSentence() {
        return FakerUtils.getSentence();
    }

    public static String getParagraph() {
        return FakerUtils.getParagraph();
    }

    public static String getRemark() {
        return FakerUtils.getRemark();
    }

    public static boolean getStatus() {
        return FakerUtils.getStatus();
    }
}
