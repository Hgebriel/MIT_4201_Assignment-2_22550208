package Test_Cases;

import Base.TestSetup;
import Pages.CartPage;
import Pages.HomePage;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CartTest extends TestSetup {

    @BeforeTest
    public void setup() {
        setUpBrowser(); // Set up browser configuration
    }

    @Test
    public void testCartNavigation() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx";
        String sheetName = "CartTest";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        try {
            // Step 1: Click on the Cart icon
            homePage.clickCartIcon();
            test = extent.createTest("Navigate to Cart", "Clicked on the Cart icon");
            test.pass("Successfully navigated to the Cart page.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Cart_Icon_Click"));

            // Step 2: Click on the Start Shopping button
            cartPage.clickStartShoppingButton();
            test = extent.createTest("Start Shopping", "Clicked on the Start Shopping button");
            test.pass("Successfully clicked on the Start Shopping button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Start_Shopping"));

            // Write back to Excel
            excel.setCellData(1, 2, "Successfully navigated to Cart and clicked Start Shopping!", excelFilePath);

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
