package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderStatusPage {
    WebDriver driver;

    // Locators for the tracking input and submit button
    @FindBy(xpath = "//*[@id=\"orderStatusFrm\"]/div/input")
    WebElement trackingSearchBar;

    @FindBy(xpath = "//*[@id=\"submitbutton\"]")
    WebElement submitButton;

    // Constructor to initialize WebDriver and page elements
    public OrderStatusPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to enter a tracking number into the input field.
     *
     * @param trackingNumber The tracking number to enter.
     */
    public void enterTrackingNumber(String trackingNumber) {
        trackingSearchBar.sendKeys(trackingNumber);
    }

    /**
     * Method to click the submit button.
     */
    public void clickSubmit() {
        submitButton.click();
    }
}
