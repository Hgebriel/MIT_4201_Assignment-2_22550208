package Test_Cases;

import Base.TestSetup;
import Pages.HomePage;
import Pages.SignInPage;
import Pages.ProfilePage;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProfileTest extends TestSetup {

    @BeforeTest
    public void setup() {
        setUpBrowser();  // Setup browser using the TestSetup base class
    }

    @Test
    public void testProfileFavourite() {
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = new SignInPage(driver);
        ProfilePage profilePage = new ProfilePage(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx";
        String sheetName = "Data";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        // Read data from Excel
        String email = excel.getCellData(5, 3);  // Example: Read email from row 5, column 3
        String password = excel.getCellData(5, 5);  // Example: Read password from row 5, column 5

        try {
            // Step 1: Click the account dropdown
            homePage.clickAccountDropdown();
            test = extent.createTest("Click Account Dropdown", "Clicked the account dropdown");
            test.pass("Successfully clicked the account dropdown.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Account_Dropdown"));

            // Step 2: Click the profile button
            homePage.clickProfileButton();
            test = extent.createTest("Click Profile Button", "Clicked the profile button");
            test.pass("Successfully clicked the profile button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Profile_Button"));

            // Step 3: Fill email and password from Excel data
            signInPage.enterEmail(email);
            signInPage.enterPassword(password);
            test = extent.createTest("Enter Login Credentials", "Entered email and password for login");
            test.pass("Successfully entered login credentials.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Login_Credentials"));

            // Step 4: Click the SignIn button
            signInPage.clickSignIn();
            test = extent.createTest("Click SignIn Button", "Clicked the SignIn button");
            test.pass("Successfully clicked the SignIn button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "SignIn_Button"));

            // Step 5: Click the favourite button on the ProfilePage
            profilePage.clickFavouriteButton();
            test = extent.createTest("Click Favourite Button", "Clicked the favourite button on the profile page");
            test.pass("Successfully clicked the favourite button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Favourite_Button"));

            // Verification: Check if the URL contains 'favourites'
            if (driver.getCurrentUrl().contains("favourites")) {
                test.pass("Successfully navigated to the favourites page.");
            } else {
                test.fail("Failed to navigate to the favourites page.");
            }

            // Write back to Excel
            excel.setCellData(5, 6, "Successfully logged in and view profile", excelFilePath);

        } catch (Exception e) {
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            throw e;
        } finally {
            excel.closeWorkbook();
        }
    }
}
