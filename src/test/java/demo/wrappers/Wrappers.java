package demo.wrappers;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
    */
    ChromeDriver driver;

    public Wrappers(ChromeDriver driver) {
        this.driver = driver;
    }
     public void clickElement(By locator) {
            driver.findElement(locator).click();
        }
    
        public void enterText(By locator, String text) {
            driver.findElement(locator).sendKeys(text);
        }
    
        public void selectRadioButton(By locator) {
            clickElement(locator);
        }
    
        public void selectCheckBox(By locator) {
            clickElement(locator);
        }
    
        public void selectDropdownOption(By locator, String visibleText) {
            Select dropdown = new Select(driver.findElement(locator));
            dropdown.selectByVisibleText(visibleText);
        }
}
    
    