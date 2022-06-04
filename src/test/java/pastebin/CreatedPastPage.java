package pastebin;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CreatedPastPage {
    private final WebDriver driver;

    @FindBy(xpath = "//span[contains(text(), 'git')]")
    public List<WebElement> listOfGitSpan;
    @FindBy(css = ".textarea")
    private WebElement textRawPasteData;

    public CreatedPastPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getTextFromRawPasteData() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".textarea")));

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", textRawPasteData);

        return textRawPasteData.getText();
    }

    public List<String> getListOfFontColorsOfGitText() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                        By.xpath("//span[contains(text(), 'git')]")
                ));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", listOfGitSpan.stream().findFirst().get());

        List<String> colors = new ArrayList<>();
        listOfGitSpan.forEach(e -> colors.add(e.getCssValue("color")));
        return colors;
    }
}
