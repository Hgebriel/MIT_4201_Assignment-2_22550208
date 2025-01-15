package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage {
    WebDriver driver;

    // Locators for the SignIn elements
    @FindBy(xpath = "//*[@id='frmLogin']/div[1]/input")
    WebElement emailField;

    @FindBy(xpath = "//*[@id='frmLogin']/div[2]/input")
    WebElement passwordField;

    @FindBy(xpath = "//*[@id='frmLogin']/button[2]")
    WebElement signInButton;

    @FindBy(xpath = "//*[contains(text(),'Welcome')]") // Adjust based on actual success criteria
    WebElement welcomeMessage;

    @FindBy(xpath = "//*[contains(text(),'Invalid credentials')]") // Error message for invalid login
    WebElement invalidLoginMessage;

    // Constructor
    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to enter email address.
     *
     * @param email The email address to enter.
     */
    public void enterEmail(String email) {
        emailField.sendKeys(email);
    }

    /**
     * Method to enter password.
     *
     * @param password The password to enter.
     */
    public void enterPassword(String password) {
        passwordField.sendKeys(password);
    }

    /**
     * Method to click the SignIn button.
     */
    public void clickSignIn() {
        signInButton.click();
    }

    /**
     * Method to verify if the SignIn was successful.
     *
     * @return True if the welcome message is displayed, otherwise false.
     */
    public boolean verifySignInSuccess() {
        try {
            return welcomeMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to verify if an error message is displayed for invalid login.
     *
     * @return True if the invalid login message is displayed, otherwise false.
     */
    public boolean verifyInvalidLogin() {
        try {
            return invalidLoginMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
