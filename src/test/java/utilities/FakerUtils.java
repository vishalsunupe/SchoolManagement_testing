package utilities;

import net.datafaker.Faker;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Thread-safe utility for generating dynamic runtime test data using Java
 * DataFaker. Ideal for populating test data across UI forms, API payloads,
 * database setups, and data-driven testing.
 */
public class FakerUtils {

	private static final ThreadLocal<Faker> FAKER_THREAD_LOCAL = ThreadLocal.withInitial(() -> new Faker(Locale.US));
	private static final Random RANDOM = new Random();

	private FakerUtils() {
		// Private constructor for utility class
	}

	/**
	 * Retrieves the thread-local Faker instance.
	 * 
	 * @return Thread-safe net.datafaker.Faker instance
	 */
	public static Faker getFaker() {
		return FAKER_THREAD_LOCAL.get();
	}

	/**
	 * Retrieves a Faker instance configured with a specific locale.
	 * 
	 * @param locale Desired Locale
	 * @return net.datafaker.Faker instance for the specified locale
	 */
	public static Faker getFaker(Locale locale) {
		return new Faker(locale);
	}

	// ==========================================
	// PERSONAL & STUDENT DATA GENERATORS
	// ==========================================

	public static String getFirstName() {
		return getFaker().name().firstName();
	}

	public static String getLastName() {
		return getFaker().name().lastName();
	}

	public static String getFullName() {
		return getFaker().name().fullName();
	}

	public static String getGender() {
		String[] genders = { "Male", "Female" };
		return genders[RANDOM.nextInt(genders.length)];
	}

	public static String getPrefix() {
		return getFaker().name().prefix();
	}

	public static String getBloodGroup() {
		String[] bloodGroups = { "A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-" };
		return bloodGroups[RANDOM.nextInt(bloodGroups.length)];
	}

	public static String getParentName() {
		return getFaker().name().fullName();
	}

	// ==========================================
	// SCHOOL & ACADEMIC DATA GENERATORS
	// ==========================================

	public static String getStudentId() {
		return "STU" + getFaker().number().numberBetween(100000, 999999);
	}

	public static String getAdmissionNumber() {
		return "ADM" + System.currentTimeMillis() % 1000000;
	}

	public static String getTeacherId() {
		return "TCH" + getFaker().number().numberBetween(10000, 99999);
	}

	public static String getEmployeeId() {
		return "EMP" + getFaker().number().numberBetween(1000, 9999);
	}

	public static String getSchoolName() {
		return getFaker().educator().university() + " School";
	}

	public static String getCourseName() {
		return getFaker().educator().course();
	}

	public static String getClassName() {
		String[] claases = {"9 - C","Class_4W - B","Class_6X - A" };
		return claases[RANDOM.nextInt(claases.length)];
	}

	public static String getSection() {
		String[] section = { "A", "B", "C" };
		return section[RANDOM.nextInt(section.length)];
	}

	public static String getSubject() {
		String[] subjects = { "Mathematics", "Physics", "Chemistry", "Biology", "English", "Computer Science",
				"History", "Geography", "Economics", "Art" };
		return subjects[RANDOM.nextInt(subjects.length)];
	}

	public static String getDepartment() {
		return getFaker().educator().campus();
	}

	public static String getGradeLevel() {
		int grade = getFaker().number().numberBetween(1, 12);
		String[] sections = { "A", "B", "C", "D" };
		String section = sections[RANDOM.nextInt(sections.length)];
		return grade + "th - " + section;
	}

	public static String getQualification() {
		String[] degrees = { "B.Ed", "M.Ed", "B.Sc", "M.Sc", "B.Tech", "M.Tech", "Ph.D", "B.A", "M.A" };
		return degrees[RANDOM.nextInt(degrees.length)];
	}

	public static String getGpa() {
		double gpa = 2.5 + (4.0 - 2.5) * RANDOM.nextDouble();
		return String.format("%.2f", gpa);
	}

	// ==========================================
	// CONTACT & ADDRESS GENERATORS
	// ==========================================

	public static String getEmailAddress() {
		return getFaker().internet().emailAddress();
	}

	public static String getEmailAddress(String domain) {
		String sanitizedName = getFirstName().toLowerCase().replaceAll("[^a-z0-9]", "")
				+ getFaker().number().numberBetween(100, 999);
		return sanitizedName + "@" + domain;
	}

	public static String getPhoneNumber() {
		return getFaker().phoneNumber().cellPhone();
	}

	public static String getTenDigitPhoneNumber() {
		return "9" + getFaker().number().digits(9);
	}

	public static String getStreetAddress() {
		return getFaker().address().streetAddress();
	}

	public static String getCity() {
		return getFaker().address().city();
	}

	public static String getState() {
		return getFaker().address().state();
	}

	public static String getZipCode() {
		return getFaker().address().zipCode();
	}

	public static String getCountry() {
		return getFaker().address().country();
	}

	public static String getFullAddress() {
		return getFaker().address().fullAddress();
	}

	// ==========================================
	// DATES & AGE GENERATORS
	// ==========================================


    /**
     * Generates a random Date of Birth for a given age range in MM/dd/yyyy format.
     */
    public static String getDateOfBirth(int minAge, int maxAge) {
        return getDateOfBirth(minAge, maxAge, "MM/dd/yyyy");
    }

    /**
     * Generates a random Date of Birth formatted according to the given pattern.
     */
    public static String getDateOfBirth(int minAge, int maxAge, String pattern) {
        return getFaker().date()
                   .birthday(minAge, maxAge)
                   .toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate()
                   .format(DateTimeFormatter.ofPattern(pattern));
    }

	public static String getPastDate(int maxDaysAgo, String pattern) {
		return getFaker().date().past(maxDaysAgo, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate().format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String getFutureDate(int maxDaysInFuture, String pattern) {
		return getFaker().date().future(maxDaysInFuture, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate().format(DateTimeFormatter.ofPattern(pattern));
	}

	// ==========================================
	// AUTH & SECURITY DATA GENERATORS
	// ==========================================

	public static String getUsername() {
		return getFaker().name().username().replaceAll("[^a-zA-Z0-9_]", "");
	}

	public static String getPassword(int length, boolean includeSpecial) {
		return getFaker().internet().password(length, length + 4, true, includeSpecial, true);
	}

	public static String getStrongPassword() {
		return "Pass#" + getFaker().internet().password(8, 12, true, true, true);
	}

	// ==========================================
	// FINANCIAL & MISC DATA GENERATORS
	// ==========================================

	public static String getTuitionFee(double min, double max) {
		double fee = min + (max - min) * RANDOM.nextDouble();
		return String.format("%.2f", fee);
	}

	public static String getCreditCardNumber() {
		return getFaker().finance().creditCard();
	}

	public static String getUuid() {
		return getFaker().internet().uuid();
	}

	public static int getRandomNumber(int min, int max) {
		return getFaker().number().numberBetween(min, max);
	}

	public static String getRandomNumeric(int length) {
		return getFaker().number().digits(length);
	}

	public static String getRandomAlphanumeric(int length) {
		return getFaker().text().text(length);
	}

	public static String getSentence() {
		return getFaker().lorem().sentence();
	}

	public static String getParagraph() {
		return getFaker().lorem().paragraph();
	}

	public static String getRemark() {
		return getFaker().lorem().sentence(8);
	}
	/* ========================================================== */

	public static boolean getStatus() {
		return RANDOM.nextBoolean();
	}

}
