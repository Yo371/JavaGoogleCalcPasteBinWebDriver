package googlecloud;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultGooglePage {
    private final WebDriver driver;

    @FindBy(xpath = "//a[@class=\"gs-title\"]/b[contains(text(),\"Calculator\")]")
    private WebElement linkCalculator;

    public ResultGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CalculatorGooglePage chooseCalculatorLink() {
        linkCalculator.click();
        return new CalculatorGooglePage(driver);
    }
}
