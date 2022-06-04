package googlecloud;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pastebin.CustomCondition;

import java.util.List;

public class CalculatorGooglePage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id='cloud-site']/devsite-iframe/iframe")
    private WebElement iframeCloud;

    @FindBy(xpath = "//*[@id='myFrame']")
    private WebElement iframeInsideCloud;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.quantity']")
    private WebElement instancesInput;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.series']")
    private WebElement selectSeries;

    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    private WebElement optionMachineType_n1st8;

    @FindBy(xpath = "//*[contains(text(), 'N1')]")
    private WebElement n1;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.instance']             ")
    private WebElement selectMachineType;


    @FindBy(xpath = "//md-checkbox[@ng-model='listingCtrl.computeServer.addGPUs']")
    private WebElement checkboxGPU;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuCount']")
    private WebElement selectGpuCount;

    @FindBy(xpath = "//*[contains(@ng-repeat, 'GpuNumber')]/following-sibling::*[@value='1']")
    private WebElement optionGpu1;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.gpuType']")
    private WebElement selectGpuType;

    @FindBy(xpath = "//div[contains(text(), 'NVIDIA Tesla V100')]")
    private WebElement optionTeslaV100;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.ssd']")
    private WebElement selectSSD;

    @FindBy(xpath = "//*[contains(@ng-repeat, 'dynamicSsd')]/following-sibling::*[@value='2']")
    private WebElement optionSSD2x375GB;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.location']")
    private WebElement selectLocation;

    @FindBy(xpath = "(//*[contains(@ng-repeat, 'fullRegion')]/following-sibling::*[@value='europe-west3'])[2]")
    private WebElement optionFrankfurt;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.cud']")
    private WebElement selectCommittedUsage;

    @FindBy(xpath = "(//*[contains(text(), '1 Year')])[2]")
    private WebElement option1year;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.nodesCount']")
    private WebElement selectNodesCount;

    @FindBy(xpath = "//button[@aria-label=\"Add to Estimate\"]")
    private List<WebElement> buttonsEstimateList;

    @FindBy(xpath = "//button[@aria-label=\"Add to Estimate\"]")
    private WebElement AddToEstimateButton;

    @FindBy(xpath = "//div[contains(text(), 'VM class')]")
    private WebElement resultVM;

    @FindBy(xpath = "//div[contains(text(), 'Instance type')]")
    private WebElement resultInstanceType;

    @FindBy(xpath = "//div[contains(text(), 'Region')]")
    private WebElement resultRegion;

    @FindBy(xpath = "//b[contains(text(), 'Estimated Component Cost:')]")
    private WebElement resultCostMonth;




    public CalculatorGooglePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        CustomCondition.waitElementPresence(driver, By.xpath("//*[@id='cloud-site']/devsite-iframe/iframe"), 15);
        switchFrame();
    }

    public CalculatorGooglePage switchFrame() {
        driver.switchTo().frame(iframeCloud).switchTo().frame(iframeInsideCloud);
        return this;
    }

    public CalculatorGooglePage inputInstances(int instances) {
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(instancesInput));
        instancesInput.sendKeys("" + instances);
        return this;
    }

    public CalculatorGooglePage selectN1Standart8_MachineType() {
        selectSeries.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(n1));
        n1.click();

        selectMachineType.click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOf(optionMachineType_n1st8));
        optionMachineType_n1st8.click();
        return this;
    }

    public CalculatorGooglePage addGPU4NvidiaV100() {
        checkboxGPU.click();

        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(selectGpuCount));

        selectGpuType.click();
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250)", "");
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionTeslaV100));
        optionTeslaV100.click();

        selectGpuCount.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionGpu1));
        optionGpu1.click();


        return this;
    }

    public CalculatorGooglePage addSsd375GB() {
        selectSSD.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionSSD2x375GB));
        optionSSD2x375GB.click();
        return this;
    }

    public CalculatorGooglePage selectFrankfurtLocation() {
        selectLocation.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(optionFrankfurt));
        optionFrankfurt.click();
        return this;
    }

    public CalculatorGooglePage selectCommittedUsage_1Year() {
        selectCommittedUsage.click();
        new WebDriverWait(driver, 4)
                .until(ExpectedConditions.visibilityOf(option1year));
        option1year.click();
        return this;
    }


    public CalculatorGooglePage estimate() {
        AddToEstimateButton.click();
        return this;
    }


    public String getResultInstanceTypeText(){
        return resultInstanceType.getText();
    }

    public String getResultRegion() {
        return resultRegion.getText().split(":")[1].trim();
    }

    public String getResultCostMonth() {
        return resultCostMonth.getText();
    }

}
