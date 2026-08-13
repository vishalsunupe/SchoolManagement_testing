package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

/**
 * Environment & Configuration Reader Utility with Log4j2 integration.
 * Dynamically loads environment-specific properties based on Maven system
 * property (-Denv=qa|uat|prod).
 */
public class ConfigReader {

	private static final Logger logger = LogManager.getLogger(ConfigReader.class);
	private static final Properties properties = new Properties();
	private static final Set<String> SUPPORTED_ENVIRONMENTS = Set.of("qa", "uat", "prod");
	private static final String DEFAULT_ENV = "qa";
	private static final String activeEnv;

	static {
		String envParam = System.getProperty("env");
		if (envParam == null || envParam.trim().isEmpty()) {
			envParam = System.getenv("env");
		}

		if (envParam == null || envParam.trim().isEmpty()) {
			activeEnv = DEFAULT_ENV;
			logger.info("No environment system property (-Denv) provided. Defaulting to: '{}'", DEFAULT_ENV);
		} else {
			activeEnv = envParam.trim().toLowerCase();
			logger.info("Target environment system property resolved to: '{}'", activeEnv);
		}

		if (!SUPPORTED_ENVIRONMENTS.contains(activeEnv)) {
			logger.error("Unsupported environment requested: '{}'. Allowed environments: {}", activeEnv,
					SUPPORTED_ENVIRONMENTS);
			throw new IllegalArgumentException(
					String.format("Invalid environment specified: '%s'. Supported environments are: %s. "
							+ "Example usage: mvn test -Denv=qa", envParam, SUPPORTED_ENVIRONMENTS));
		}

		String configFilePath = "config/" + activeEnv + ".properties";
		try (InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(configFilePath)) {
			if (inputStream == null) {
				logger.error("Configuration file not found on classpath: {}", configFilePath);
				throw new RuntimeException("Configuration file not found on classpath: " + configFilePath);
			}
			properties.load(inputStream);
			logger.info("Successfully loaded environment configuration file: '{}'", configFilePath);
		} catch (IOException e) {
			logger.error("Failed to load environment configuration file: {}", configFilePath, e);
			throw new RuntimeException("Failed to load environment configuration file: " + configFilePath, e);
		}
	}

	public static String getProperty(String key) {
		return properties.getProperty(Objects.requireNonNull(key, "Property key must not be null"));
	}

	public static String getProperty(String key, String defaultValue) {
		return properties.getProperty(key, defaultValue);
	}

	public static String getTargetEnvironment() {
		return activeEnv;
	}

	public static String getEdgeDriverPath() {
		String edgedriverpath = properties.getProperty("edgedriverpath");
		if (edgedriverpath == null || edgedriverpath.trim().isEmpty()) {
			return null;
		}
		java.io.File file = new java.io.File(edgedriverpath.trim());
		if (file.isAbsolute()) {
			return file.getAbsolutePath();
		}
		return new java.io.File(System.getProperty("user.dir"), edgedriverpath.trim()).getAbsolutePath();
	}

}
