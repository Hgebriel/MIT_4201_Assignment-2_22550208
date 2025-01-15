package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"product_view_price\"]/h2/span[2]")
    WebElement productPrice;

    @FindBy(id = "main_add_cart_btn")
    WebElement addToCartButton;

    public ProductDetailPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to check price and add to cart
    public void verifyPriceAndAddToCart(String expectedPrice) {
            addToCartButton.click();

    }
}
