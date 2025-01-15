package Pages;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ChatbotPopUp {
    WebDriver driver;

    // Locators for the input fields and buttons
    @FindBy(xpath = "//*[@id=\"1--input")
    WebElement nameInput;

    @FindBy(xpath = "//*[@id=\"2--input\"]")
    WebElement emailInput;

    @FindBy(xpath = "//*[@id=\"4--input\"]")
    WebElement messageInput;

    @FindBy(xpath = "//*[@id=\"Embed\"]/div/div/div/div/div/div/form/footer/div/button")
    WebElement startChatButton;

    // Constructor to initialize WebDriver and page elements
    public ChatbotPopUp(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Method to send a message using data from the Testdata Excel sheet.
     */
    public void sendMessage(String excelFilePath) throws IOException {
        // Load the Excel sheet
        FileInputStream file = new FileInputStream(new File(excelFilePath));
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Get data for name, email, and message
        String name = sheet.getRow(5).getCell(1).getStringCellValue();
        String email = sheet.getRow(5).getCell(3).getStringCellValue();
        String message = sheet.getRow(7).getCell(1).getStringCellValue();

        // Fill the form with extracted data
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        messageInput.sendKeys(message);

        // Click the "Start Chat" button
        startChatButton.click();

        // Update Excel sheet with the confirmation message
        sheet.getRow(7).getCell(3).setCellValue("Successfully started the chat!");
        workbook.close();
    }
}
