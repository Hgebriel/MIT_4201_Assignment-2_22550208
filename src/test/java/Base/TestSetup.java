package Base;

import Utils.ScreenShotHandler;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class TestSetup {

        protected WebDriver driver;
        protected static ExtentReports extent;
        protected static ExtentTest test;
        private String reportFileName;

        @BeforeSuite
        public void setUp() {
            // Create a unique file name for the Extent Report
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            reportFileName = "ExtentReport_" + timestamp + ".html"; // Default report name

            // Set up ExtentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);


        }

        /**
         * Set a custom name for the Extent Report.
         * @param customName The custom name for the report.
         */
        public void setReportName(String customName) {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            reportFileName = customName + "_" + timestamp + ".html";

            // Reinitialize ExtentSparkReporter with the new name
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("./reports/" + reportFileName);
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div/div[2]/div/div/div[7]/button[2]")));


        }

        /**
         * Start a test in Extent Report with a given name.
         *
         * @param testName Name of the test case.
         */
        public void startTest(String testName) {
            test = extent.createTest(testName);
        }

        @BeforeClass
        public void setUpBrowser() {

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.get("https://www.wishque.com/");
        }

        @AfterMethod
        public void captureScreenshotOnFailure(ITestResult result) {
            if (ITestResult.FAILURE == result.getStatus()) {
                ScreenShotHandler.takeScreenshot(driver, result.getName());
            }
        }

        @AfterClass
        public void tearDown() {
            if (driver != null) {
                driver.close();
            }
        }

        @AfterSuite
        public void tearDownReport() {
            extent.flush();

        }

    }


