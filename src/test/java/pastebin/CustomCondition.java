package pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomCondition {
    public static ExpectedCondition<Boolean> jsCompleted() {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery " +
                        "!= null) && (jQuery.active == 0); ");
            }
        };
    }

    public static ExpectedCondition<Boolean> waitForEmail(WebElement webElement) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                return webElement.getText().equals("1");
            }
        };
    }

    public static void waitElementPresence(WebDriver driver, By by, int sec) {
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void waitElementsPresence(WebDriver driver, By by, int sec) {
        new WebDriverWait(driver, sec)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }
}
