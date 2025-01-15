package Test_Cases;

import Base.TestSetup;
import Pages.*;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchItemAddToCart extends TestSetup {
    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void searchAndAddFlowerToCart() {
        HomePage HomePage = new HomePage(driver);
        SearchItemPage searchItemPage = new SearchItemPage(driver);
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        ViewCartPage viewCartPage = new ViewCartPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx";
        String sheetName = "Data";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        // Read data
        String searchKeyword = excel.getCellData(1, 1); // "flower"
        String expectedPrice = excel.getCellData(2, 2); // "10800"

        try {
            // Step 1: Search for 'flower'
            HomePage.searchFor(searchKeyword);
            test = extent.createTest("Search for Flower", "Searched 'flower' and pressed Enter");
            test.pass("Successfully searched for 'flower'.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Search_Flower"));

            // Step 2: Select Fresh Flower Category
            searchItemPage.selectFreshFlowerCategory();
            test = extent.createTest("Select Fresh Flower Category", "Selected 'Fresh Flower' category");
            test.pass("Successfully selected 'Fresh Flower' category.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Select_Category"));

            // Step 3: Click on Item Card
            searchItemPage.clickItemCard();
            test = extent.createTest("Select Item", "Clicked on the item card");
            test.pass("Successfully clicked on the item card.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Select_Item"));

            // Step 4: Verify Price and Add to Cart
            productDetailPage.verifyPriceAndAddToCart(expectedPrice);
            test = extent.createTest("Verify Price and Add to Cart", "Verified the price and added item to the cart");
            test.pass("Verified the item price and added to cart.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Add_To_Cart"));

            // Step 5: View Cart
            viewCartPage.clickCartIcon();
            test = extent.createTest("View Cart", "Clicked on cart icon to view items in cart");
            test.pass("Navigated to cart successfully.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "View_Cart"));

            // Write back to Excel
            excel.setCellData(1, 3, "Successfully searched flower and added to cart!", excelFilePath);
        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
