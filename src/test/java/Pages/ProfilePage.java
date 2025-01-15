package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    WebDriver driver;

    // Locator for the "favourite product" button
    @FindBy(xpath = "//*[@id=\"wq_body\"]/section[3]/div/div/div[1]/div/div[1]/div[2]/a[1]")
    WebElement favouriteButton;

    // Constructor to initialize WebDriver and page elements
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to click the favourite product button.
     */
    public void clickFavouriteButton() {
        favouriteButton.click();
    }
}
