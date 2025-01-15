package Test_Cases;

import Base.TestSetup;
import Pages.HomePage;
import Pages.SignInPage;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;

public class SignInTest extends TestSetup {

    private ExtentTest test;

    @BeforeTest
    public void setup() {
        setUpBrowser();
        assert driver != null : "WebDriver initialization failed!";
    }

    @Test
    public void signInTest() {
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = new SignInPage(driver);

        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx"; // Update with actual path
        String sheetName = "Data";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        // Read test data from Excel
        String email = excel.getCellData(5, 3); // Row 5, Column 3
        String password = excel.getCellData(5, 5); // Row 5, Column 5

        try {
            // Step 1: Navigate to SignIn Page
            homePage.clickSignIn();
            test = extent.createTest("Navigate to SignIn Page", "Clicking SignIn button on homepage");
            test.pass("SignIn button clicked successfully.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "SignIn_Navigate"));

            // Step 2: Enter SignIn Details
            signInPage.enterEmail(email);
            signInPage.enterPassword(password);
            signInPage.clickSignIn();
            test = extent.createTest("SignIn with Credentials", "Signing in with provided email and password");
            test.pass("SignIn form submitted successfully.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "SignIn_Form_Submit"));

            // Step 3: Verify SignIn Success
            if (signInPage.verifySignInSuccess()) {
                test.pass("SignIn successful.")
                        .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "SignIn_Success"));

                // Write success message to Excel
                excel.setCellData(9, 3, "SignIn Success!", excelFilePath); // Row 9, Column 3
            } else {
                test.fail("SignIn failed.")
                        .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "SignIn_Failed"));
                throw new Exception("SignIn verification failed!");
            }

        } catch (Exception e) {
            if (test != null) {
                test.fail("Test failed due to: " + e.getMessage())
                        .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            }

        } finally {
            excel.closeWorkbook();
        }
    }
}
