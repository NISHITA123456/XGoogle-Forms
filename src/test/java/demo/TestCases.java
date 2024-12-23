package demo;

import org.openqa.selenium.By;
// import org.openqa.selenium.JavascriptExecutor;
// import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

// import javax.xml.xpath.XPath;

// import io.github.bonigarcia.wdm.WebDriverManager;
// import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation.
     * Follow `testCase01` `testCase02`... format or what is provided in
     * instructions
     */

    /*
     * Do not change the provided methods unless necessary, they will help in
     * automation and assessment
     */
    @BeforeTest
    public void startBrowser() {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log");

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @SuppressWarnings("deprecation")
    @Test
    public void testCase01() throws InterruptedException {

        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSep9LTMntH5YqIXa5nkiPKSs283kdwitBBhXWyZdAS-e4CxBQ/viewform");
                Thread.sleep(5000);

         // Fill in "Crio Learner" in the 1st text box
        driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys("Crio Learner");
        Thread.sleep(1000);

        // Write down "I want to be the best QA Engineer! <epoch_time>"
        long epochTime = Instant.now().getEpochSecond();
        driver.findElement(By.xpath("//textarea[contains(@class, 'tL9Q4c')]")).sendKeys("I want to be the best QA Engineer! " + epochTime);
        Thread.sleep(1000);

        // Enter your Automation Testing experience
        driver.findElement(By.xpath("//div[@role='radio' and @aria-label='0 - 2']")).click();
        Thread.sleep(1000);

        // Select Java, Selenium and TestNG
        List<String> checkBoxLabels = Arrays.asList("Java", "Selenium", "TestNG");
        for (String label : checkBoxLabels) {

            WebElement checkbox = driver.findElement(By.xpath("//label[.//span[contains(text(), '" + label + "')]]//div[@role='checkbox']"));
            if (!checkbox.getAttribute("aria-checked").equals("true")) {
                checkbox.click(); // Select the checkbox
                Thread.sleep(1000);
                System.out.println(label + " checkbox selected.");
            } else {
                System.out.println(label + " checkbox is already selected.");
            }
            Thread.sleep(1000);
        }
        // Dropdown Selection

        WebElement selectDropDown = driver.findElement(By.xpath("//div[@role='listbox' and @jsname='W85ice']"));
        selectDropDown.click();
        Thread.sleep(1000);

        // String optionText = "Ms";
        WebElement option = driver.findElement(By.xpath("//*[@id=\"mG61Hd\"]/div[2]/div/div[2]/div[5]/div/div/div[2]/div/div[2]/div[3]"));
            option.click();

        Thread.sleep(1000);

      // Provide the current date minus 7 days
        LocalDateTime date = LocalDateTime.now().minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String formattedDate = date.format(formatter);
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(formattedDate);
        Thread.sleep(1000);

        // Provide the time 07:30
        WebElement hourElement = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        hourElement.clear();
        hourElement.sendKeys("07");

        WebElement minuteElement = driver.findElement(By.xpath("//input[@aria-label='Minute']"));
        minuteElement.clear();
        minuteElement.sendKeys("30");
        Thread.sleep(1000);

        // Submit the form
        WebElement submitButton = driver.findElement(By.xpath("//span[text()='Submit']"));
        submitButton.click();
        Thread.sleep(1000);
        
         // Print the success message
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
         String successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Thanks for your response, Automation Wizard!']"))).getText();
         System.out.println(successMessage);       
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();

    }
}
