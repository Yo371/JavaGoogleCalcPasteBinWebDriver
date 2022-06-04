package googlecloud;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pastebin.CustomCondition;

import java.util.concurrent.TimeUnit;

public class HomeGoogleCloudPage {
    private final WebDriver driver;
    private final String URL_HOME = "https://cloud.google.com/";

    @FindBy(css = "div.devsite-search-container")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name=\"q\"]")
    private WebElement searchArea;

    public HomeGoogleCloudPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public ResultGooglePage searchByText(String text) {

        searchButton.click();
        CustomCondition.waitElementPresence(driver, By.xpath("//input[@name=\"q\"]"), 5);
        searchArea.sendKeys(text);
        searchArea.sendKeys(Keys.ENTER);

        CustomCondition.waitElementsPresence(driver, By.className("gs-title"), 20);
        return new ResultGooglePage(driver);
    }


    public HomeGoogleCloudPage open() {

        driver.get(URL_HOME);

        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

        return this;
    }

}
