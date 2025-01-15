package Test_Cases;

import Base.TestSetup;
import Pages.HomePage;
import Pages.InternationalDeliveryPage;
import Utils.ScreenShotHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InternationalOrderTest extends TestSetup {

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void testInternationalOrder() {
        // Initialize Pages
        HomePage homePage = new HomePage(driver);
        InternationalDeliveryPage internationalPage = new InternationalDeliveryPage(driver);

        try {
            // Step 1: Click on "International" Button
            homePage.clickInternationalButton();
            test = extent.createTest("Navigate to International Page", "Clicked on the 'International' button");
            test.pass("Successfully navigated to International Delivery Page.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Navigate_International_Page"));

            // Step 2: Click on "View Store" Button
            internationalPage.clickViewStoreButton();
            test = extent.createTest("View Store", "Clicked on the 'View Store' button");
            test.pass("Successfully clicked on the 'View Store' button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "View_Store"));

            // Step 3: Click on an Item
            internationalPage.clickItem();
            test = extent.createTest("Select Item", "Clicked on the item");
            test.pass("Successfully selected the item.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Select_Item"));

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            throw e;
        }
    }
}
