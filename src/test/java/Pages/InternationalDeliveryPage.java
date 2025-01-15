package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InternationalDeliveryPage {
    WebDriver driver;

    // Locator for the "View Store" button
    @FindBy(xpath = "//*[@id=\"wq_body\"]/section[3]/div/div/div[5]/div/a[3]")
    WebElement viewStoreButton;

    // Locator for an item
    @FindBy(xpath = "//*[@id=\"department_fresh-flowers\"]/div/div[11]/div[2]/a/div/img")
    WebElement item;

    // Constructor to initialize WebDriver and page elements
    public InternationalDeliveryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click the "View Store" button.
     */
    public void clickViewStoreButton() {
        viewStoreButton.click();
    }

    /**
     * Method to click an item.
     */
    public void clickItem() {
        item.click();
    }
}
