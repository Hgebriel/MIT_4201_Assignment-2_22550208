package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    // Locator for the "Track Order" button
    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[6]/a")
    WebElement trackOrderButton;

    // Locator for the search box (if still required for other searches on the homepage)
    @FindBy(id = "search_query")
    WebElement searchBox;

    // Locator for the search button
    @FindBy(xpath = "//*[@id=\"search_query_button\"]/i")
    WebElement searchButton;

    // Locator for the "Chat" button (from the ChatHome functionality)
    @FindBy(xpath = "//*[@id=\"Embed\"]/div/div")
    WebElement chatButton;

    // Locator for the "SignIn" button
    @FindBy(xpath = "//*[@id=\"default\"]")
    WebElement signInButton;

    // Locator for the account dropdown
    @FindBy(xpath = "//*[@id=\"profile-dropdown\"]/i")
    WebElement accountDropdown;

    // Locator for the profile button
    @FindBy(xpath = "//*[@id=\"profile\"]")
    WebElement profileButton;

    // Locator for the "International" button
    @FindBy(xpath = "//*[@id=\"bs-example-navbar-collapse-1\"]/ul/li[4]/a")
    WebElement internationalButton;

    // Constructor to initialize WebDriver and page elements
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click the "Track Order" button.
     * Navigates to the Track Order page.
     */
    public void clickTrackOrder() {
        trackOrderButton.click();
    }

    /**
     * Method to search for a given keyword on the home page.
     *
     * @param keyword The keyword to search.
     */
    public void searchFor(String keyword) {
        searchBox.sendKeys(keyword);
        searchButton.click();
    }

    /**
     * Method to click the "Chat" button.
     * Opens the chat functionality.
     */
    public void clickChatButton() {
        chatButton.click();
    }

    /**
     * Method to click the "SignIn" button.
     * Navigates to the SignIn page.
     */
    public void clickSignIn() {
        signInButton.click();
    }

    /**
     * Method to click the account dropdown.
     */
    public void clickAccountDropdown() {
        accountDropdown.click();
    }

    /**
     * Method to click the profile button.
     */
    public void clickProfileButton() {
        profileButton.click();
    }

    /**
     * Method to click the "International" button.
     * Navigates to the International Delivery page.
     */
    public void clickInternationalButton() {
        internationalButton.click();
    }

    public void clickCartIcon() {

    }
}