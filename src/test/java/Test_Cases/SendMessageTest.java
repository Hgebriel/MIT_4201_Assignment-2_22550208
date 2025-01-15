package Test_Cases;

import Base.TestSetup;
import Pages.HomePage;
import Pages.ChatbotPopUp;
import Utils.ScreenShotHandler;
import Utils.ExcelManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class SendMessageTest extends TestSetup {

    @BeforeTest
    public void setup() {
        // Initialize the browser setup
        setUpBrowser();
    }

    @Test
    public void testSendMessage() {
        // Initialize ExtentTest before starting the test
        test = extent.createTest("Send Message Test", "Test to send a message through the chatbot.");

        HomePage homePage = new HomePage(driver);
        ChatbotPopUp chatbotPopUp = new ChatbotPopUp(driver);

        // Excel Initialization
        String excelFilePath = "src/test/resources/Testdata/Testdata.xlsx";
        String sheetName = "Data";
        ExcelManager excel = new ExcelManager(excelFilePath, sheetName);

        try {
            // Step 1: Click on Chat button
            homePage.clickChatButton();
            test.pass("Successfully clicked on 'Chat' button.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Open_Chat"));

            // Step 2: Send message using data from Excel
            chatbotPopUp.sendMessage(excelFilePath);
            test.pass("Successfully sent the message and started the chat.")
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Send_Message"));

            // Write success message to Excel
            excel.setCellData(6, 2, "Successfully started the chat!", excelFilePath);

        } catch (Exception e) {
            // If an error occurs, report it
            test.fail("Test failed due to: " + e.getMessage())
                    .addScreenCaptureFromPath(ScreenShotHandler.takeScreenshot(driver, "Test_Failure"));
            try {
                throw e;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } finally {
            excel.closeWorkbook();
        }
    }
}
