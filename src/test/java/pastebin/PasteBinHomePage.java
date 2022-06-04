package pastebin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PasteBinHomePage {
    private final WebDriver driver;
    private static final String URL_HOME = "https://pastebin.com/";

    @FindBy(id = "postform-text")
    private WebElement textArea;

    @FindBy(id = "select2-postform-expiration-container")
    private WebElement expirationDroppedMenu;

    @FindBy(xpath = "//li[contains(text(), \"10 Minutes\")]")
    private WebElement _10MinOption;

    @FindBy(xpath = "//li[contains(text(), 'Bash')]")
    private WebElement bashOption;

    @FindBy(id = "postform-name")
    private WebElement titleInputArea;

    @FindBy(xpath = "//*[@id='select2-postform-format-container']")
    private WebElement syntaxDroppedMenu;

    @FindBy(xpath = "//button[text()='Create New Paste']")
    private WebElement createPasteButton;


    public PasteBinHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PasteBinHomePage open() {
        driver.get(URL_HOME);
        new WebDriverWait(driver, 10)
                .until(CustomCondition.jsCompleted());
        return this;
    }

    public PasteBinHomePage inputToTextArea(String text) {
        textArea.sendKeys(text);
        return this;
    }

    public PasteBinHomePage chooseExpiration10Min() {
        expirationDroppedMenu.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(_10MinOption));
        _10MinOption.click();
        return this;
    }

    public PasteBinHomePage chooseBashSyntaxHighlight() {
        syntaxDroppedMenu.click();
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOf(bashOption));
        bashOption.click();
        return this;
    }

    public PasteBinHomePage inputToTitleArea(String title) {
        titleInputArea.sendKeys(title);
        return this;
    }

    public CreatedPastPage createPaste() {
        createPasteButton.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions
                .urlMatches("https://pastebin.com/[A-Za-z0-9]+"));
        return new CreatedPastPage(driver);
    }

}
