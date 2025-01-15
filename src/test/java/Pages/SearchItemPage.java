package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchItemPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"wq_body\"]/nav/div/div[1]/div[2]/a/i" )
    WebElement categoryDropdown;

    @FindBy(xpath = "//*[@id=\"wq_body\"]/nav/div/div[1]/div[2]/ul/li[2]/a")
    WebElement freshFlowerCategory;

    @FindBy(xpath = "//*[@id=\"carousel_cat_0_251\"]/div/div[1]/div/div[2]/div[2]/a/div/img")
    WebElement itemCard;

    public SearchItemPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to select category and item
    public void selectFreshFlowerCategory() {
        categoryDropdown.click();
        freshFlowerCategory.click();
    }

    public void clickItemCard() {
        itemCard.click();
    }
}
