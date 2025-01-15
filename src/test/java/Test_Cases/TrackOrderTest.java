package Test_Cases;

import Base.TestSetup;
import Pages.*;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TrackOrderTest extends TestSetup {

    @BeforeTest
    public void setup() {
        setUpBrowser();
    }

    @Test
    public void trackOrderWithInvalidNumber() {
        HomePage homePage = new HomePage(driver);
        OrderStatusPage orderStatusPage = new OrderStatusPage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx";
        String sheetName = "Data";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        // Read data
        String invalidTrackingNumber = excel.getCellData(2, 0); // Tracking number from Excel (row 3, column 1)

        try {
            // Step 1: Click on Track Order
            homePage.clickTrackOrder();
            test = extent.createTest("Click on Track Order", "Navigated to Track Order page");
            test.pass("Successfully clicked on Track Order button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Track_Order_Clicked"));

            // Step 2: Enter invalid tracking number
            orderStatusPage.enterTrackingNumber(invalidTrackingNumber);
            test = extent.createTest("Enter Invalid Tracking Number", "Entered an invalid tracking number: " + invalidTrackingNumber);
            test.pass("Successfully entered the tracking number.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Enter_Tracking_Number"));

            // Step 3: Click on Submit button
            orderStatusPage.clickSubmit();
            test = extent.createTest("Submit Tracking Number", "Clicked on submit to track the order");
            test.pass("Successfully clicked on submit.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Submit_Tracking_Number"));

            // Write back to Excel (if necessary)
            excel.setCellData(3, 1, "Tracking number entered and submit clicked.", excelFilePath);

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
