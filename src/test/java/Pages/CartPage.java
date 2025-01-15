package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
    WebDriver driver;

    // Locator for the Start Shopping button
    @FindBy(xpath = "//*[@id=\"wq_body\"]/section[3]/div[2]/div/div[1]/div/a")
    WebElement startShoppingButton;

    // Constructor to initialize WebDriver and page elements
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click the Start Shopping button.
     */
    public void clickStartShoppingButton() {
        startShoppingButton.click();
    }
}
