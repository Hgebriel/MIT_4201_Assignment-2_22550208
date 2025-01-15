package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCartPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"cart-drop\"]")
    WebElement cartIcon;

    public ViewCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to click on cart icon
    public void clickCartIcon() {
        cartIcon.click();
    }
}
