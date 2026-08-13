package pages;

import base.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import utilities.AlertUtils;
import utilities.ElementUtils;

/**
 * Page Object Model representation of the Application Login Page with Log4j2 logging.
 * Strictly protects sensitive user credentials from log exposure.
 */
public class LoginPage {

    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private final WebDriver driver;

    // --- Locators (Private & Encapsulated) ---
    // --- Locators (Private & Encapsulated) ---
    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By SigninButton = By.xpath("//button[@type='submit' and normalize-space()='Sign In']");
    private final By error = By.id("error");
   
    public LoginPage() {
        this.driver = DriverFactory.getDriver();
    }
    /*=========== action methods  ==========*/
    public void enterUsername(String username) {
        logger.info("Entering username: '{}'", username);
        ElementUtils.clearAndType(usernameInput, username);
    }

    public void enterPassword(String password) {
        logger.info("Entering password: [PROTECTED]");
        ElementUtils.clearAndType(passwordInput, password);
    }

    public void clickSignin() {
        logger.info("Clicking Login button");
        ElementUtils.safeClick(SigninButton);
    }

    public void signIn(String username, String password) {
        logger.info("Performing login workflow for user: '{}'", username);
        enterUsername(username);
        enterPassword(password);
        clickSignin();  
    }
    
    public String getUsernameFieldValidationMessage() {
        logger.info("getting validation message");
        WebElement element=driver.findElement(usernameInput);
        String validationMessage = element.getAttribute("validationMessage");
        return validationMessage;
    }
    /*=========== verification methods  ==========*/
    public boolean verifyTitleOfLoginPage() {
        logger.info("Verifying Login page title");
        return driver.getTitle().equals("EduManager - Login");
    }

    public boolean verifyTitleOFLoginPge() {
        return verifyTitleOfLoginPage();
    }

    public String getErrorMessage() {
    	String errorMessage=ElementUtils.getText(error);
		return errorMessage;
    }
   
}
